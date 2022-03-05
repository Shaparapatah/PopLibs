package com.shaparapatah.poplibs.model

import org.junit.Assert.*

import org.junit.Test

class GithubUserModelTest {

    @Test
    fun test() {
        val test = GithubUserModel("1", "MyLogin", "333", "www.ru")
        assertEquals(GithubUserModel("1", "MyLogin", "333", "www.ru"),
        test
            )
    }
}