package com.shaparapatah.poplibs.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import java.io.Serializable


data class GithubRepoModel(
    @Expose
    val id: String,

    @Expose
    val name: String,

    @Expose
    val owner: GithubRepoOwner,

    @Expose
    val forksCount: Int
) : Serializable


data class GithubRepoOwner(
    @Expose
    val id: String
) : Serializable



