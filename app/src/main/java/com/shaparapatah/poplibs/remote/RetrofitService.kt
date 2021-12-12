package com.shaparapatah.poplibs.remote

import com.shaparapatah.poplibs.model.GithubUserModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUserModel>>


    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Single<GithubUserModel>
}