package com.shaparapatah.poplibs.model

import com.google.gson.annotations.Expose

data class GithubUserModel(

    @Expose
    val login: String,

    @Expose
    val avatarUrl: String
)
