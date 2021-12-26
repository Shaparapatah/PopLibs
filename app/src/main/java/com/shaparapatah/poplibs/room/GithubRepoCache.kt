package com.shaparapatah.poplibs.room

import com.shaparapatah.poplibs.model.GithubRepoModel
import com.shaparapatah.poplibs.model.GithubRepoOwner
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.room.model.RoomGithubRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GithubRepoCache @Inject constructor(
    private val db: AppDataBase
) {

    fun insertRepos(repos: List<GithubRepoModel>): Single<List<GithubRepoModel>> {
        val dbRepos = repos.map {
            RoomGithubRepository(it.id, it.name, it.owner.id, it.forksCount)
        }
        return db.repoDao.insert(dbRepos)
            .toSingle { repos }
    }

    fun getRepos(userModel: GithubUserModel): Single<List<GithubRepoModel>> {
        return db.repoDao.getByUserId(userModel.id)
            .map { list ->
                list.map { repo ->
                    GithubRepoModel(
                        repo.id,
                        repo.name,
                        GithubRepoOwner(repo.userId),
                        repo.forkCount
                    )
                }

            }
    }

    fun getClikedRepos(userModel: GithubRepoModel): Single<List<GithubRepoModel>> {
        return db.repoDao.getByUserId(userModel.id)
            .map { list ->
                list.map { repo ->
                    GithubRepoModel(
                        repo.id,
                        repo.name,
                        GithubRepoOwner(repo.userId),
                        repo.forkCount
                    )
                }

            }
    }
}
