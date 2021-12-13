package com.shaparapatah.poplibs.remote

import com.shaparapatah.poplibs.model.GithubUser
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.model.Repository
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>


    @GET("users/{username}")
    fun getUserByLogin(@Path("username") login: String): Single<GithubUserModel>

    @GET("/user/{username}/repos")
    fun getUserRepos(
        @Path("username") login: String,
        @Query("type") type: String?,
        @Query("sort") sort: String?,
        @Query("direction") direction: String?,
        @Query("per_page") perPage: Int?,
        @Query("page") page: Int?
    ): Single<List<Repository>>
}