package com.shaparapatah.poplibs.room

import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.room.model.RoomGithubUser
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GithubUserCache @Inject constructor(
    private val db: AppDataBase,
) {

    fun insert(users: List<GithubUserModel>): Single<List<GithubUserModel>> {
        val roomUsers = users.map { user ->
            RoomGithubUser(user.id, user.login, user.avatarUrl, user.reposUrl)
        }
        return db.userDao.insert(roomUsers)
            .toSingle { users }
    }

    fun getUsers(): Single<List<GithubUserModel>> {
        return db.userDao.getAll().map { users ->
            users.map { roomModel ->
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

