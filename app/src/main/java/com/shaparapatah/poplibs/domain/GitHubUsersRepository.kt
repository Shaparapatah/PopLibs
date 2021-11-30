package com.shaparapatah.poplibs.domain

import com.shaparapatah.poplibs.model.GithubUserModel

class GitHubUsersRepository {

    private val users = listOf(
        GithubUserModel("user1"),
        GithubUserModel("user2"),
        GithubUserModel("user3"),
        GithubUserModel("user4"),
        GithubUserModel("user5"),
        GithubUserModel("user6")
    )


    fun getUsers(): List<GithubUserModel> {
        return users
    }
}