package com.shaparapatah.poplibs

import com.nhaarman.mockito_kotlin.verify
import com.shaparapatah.poplibs.domain.GithubUsersRepositoryImpl
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.remote.RetrofitService
import com.shaparapatah.poplibs.remote.connectivity.NetworkStatus
import com.shaparapatah.poplibs.room.GithubUserCache
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import retrofit2.Call

class GitHubRepositoryTest {

    private lateinit var repositoryImpl: GithubUsersRepositoryImpl

    @Mock
    private lateinit var retrofitService: RetrofitService

    @Mock
    private lateinit var networkStatus: NetworkStatus

    @Mock
    private lateinit var userCache: GithubUserCache

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repositoryImpl = GithubUsersRepositoryImpl(retrofitService, networkStatus, userCache)
    }

    @Test
    fun `test`() {
        val someUrl = "someUrl.com"
        val call = Mockito.mock(Single::class.java) as Single<List<GithubUserModel>>
        `when`(retrofitService.getUsers()).thenReturn(call)
        repositoryImpl.getUsers()
        verify(retrofitService, times(1)).getRepos(someUrl)
    }




}