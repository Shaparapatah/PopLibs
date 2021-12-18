package com.shaparapatah.poplibs.model

import com.google.gson.annotations.Expose
import java.io.Serializable


data class GithubUserModel(
    @Expose
    val id: String,

    @Expose
    val login: String,

    @Expose
    val avatarUrl: String,

    @Expose
    val reposUrl: String

) : Serializable
