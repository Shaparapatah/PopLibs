package com.shaparapatah.poplibs.remote

import com.shaparapatah.poplibs.model.GithubRepoModel
import com.shaparapatah.poplibs.model.GithubUserModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUserModel>>


    @GET()
    fun getRepos(@Url url: String): Single<List<GithubRepoModel>>


}