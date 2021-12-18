package com.shaparapatah.poplibs.domain

import com.shaparapatah.poplibs.model.GithubUserModel
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
    override fun getUsers(): Single<List<GithubUserModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        val roomUsers = users.map { user ->
                            RoomGithubUser(user.id, user.login, user.avatarUrl, user.reposUrl)
                        }
                        db.userDao.insert(roomUsers)
                        users
                    }
                }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map { roomModel ->
                    GithubUserModel(
                        roomModel.id,
                        roomModel.login,
                        roomModel.avatarUrl,
                        roomModel.reposUrl
                    )
                }
            }
        }
    }


}