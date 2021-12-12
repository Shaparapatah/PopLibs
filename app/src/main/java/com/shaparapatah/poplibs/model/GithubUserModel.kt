package com.shaparapatah.poplibs.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GithubUserModel(

    @Expose
    @SerializedName("login")
    val login: String? = null,

    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String? = null
)
