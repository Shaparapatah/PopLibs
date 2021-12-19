package com.shaparapatah.poplibs.domain

import com.shaparapatah.poplibs.model.GithubRepoModel
import com.shaparapatah.poplibs.model.GithubRepoOwner
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.remote.RetrofitService
import com.shaparapatah.poplibs.remote.connectivity.NetworkStatus
import com.shaparapatah.poplibs.room.AppDataBase
import com.shaparapatah.poplibs.room.model.RoomGithubRepository
import io.reactivex.rxjava3.core.Single

class GithubRepoRepositoryImpl(
    private val retrofitService: RetrofitService,
    private val networkStatus: NetworkStatus,
    private val db: AppDataBase
) : GitHubRepoRepository {
    override fun getRepos(userModel: GithubUserModel): Single<List<GithubRepoModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getRepos(userModel.reposUrl)
                .flatMap { repos ->
                    Single.fromCallable {
                        val dbRepos = repos.map {
                            RoomGithubRepository(it.id, it.name, it.owner.id, it.forksCount)
                        }
                        db.repoDao.insert(dbRepos)
                        repos
                    }
                }
        } else {
            Single.fromCallable {
                db.repoDao.getByUserId(userModel.id)
                    .map { GithubRepoModel(it.id, it.name, GithubRepoOwner(it.id), it.forkCount) }
            }
        }
    }

    override fun onClickedRepos(repoModel: GithubRepoModel): Single<List<GithubRepoModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getRepos(repoModel.id)
                .flatMap { repos ->
                    Single.fromCallable {
                        val clickedRepos = repos.map {
                            RoomGithubRepository(it.id, it.name, it.owner.id, it.forksCount)
                        }
                        db.repoDao.insert(clickedRepos)
                        repos
                    }
                }
        } else {
            Single.fromCallable {
                db.repoDao.getByUserId(repoModel.name)
                    .map { GithubRepoModel(it.id, it.name, GithubRepoOwner(it.id), it.forkCount) }
            }
        }
    }
}
