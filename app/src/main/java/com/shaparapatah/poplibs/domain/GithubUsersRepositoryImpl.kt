package com.shaparapatah.poplibs.domain

import com.shaparapatah.poplibs.model.GithubUser
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.model.Repository
import com.shaparapatah.poplibs.remote.ApiHolder.retrofitService
import com.shaparapatah.poplibs.remote.RetrofitService
import com.shaparapatah.poplibs.remote.connectivity.NetworkStatus
import com.shaparapatah.poplibs.room.AppDataBase
import com.shaparapatah.poplibs.room.model.RoomGithubUser
import io.reactivex.rxjava3.core.Single

class GithubUsersRepositoryImpl(
    private val retrofitService: RetrofitService,
    private val networkStatus: NetworkStatus,
    private val db: AppDataBase
) : GitHubUsersRepository {


    override fun getUsers(): Single<List<GithubUser>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        val roomUsers = users.map { user ->
                            RoomGithubUser(user.login, user.avatarUrl, user.reposUrl, user.reposUrl)
                        }
                        db.userDao.insert(roomUsers)
                        users
                    }
                }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map { roomModel ->
                    GithubUser(
                        roomModel.login,
                        roomModel.id,
                        roomModel.avatarUrl,
                        roomModel.reposUrl,
                    )
                }
            }
        }
    }


    override fun getUserByLogin(login: String): Single<GithubUserModel> {
        return retrofitService.getUserByLogin(login)
    }

    override fun getUserRepos(
        login: String,
        type: String?,
        sort: String?,
        direction: String?,
        perPage: Int?,
        page: Int?
    ): Single<List<Repository>> {
        return retrofitService.getUserRepos(login, type, sort, direction, perPage, page)
    }
}