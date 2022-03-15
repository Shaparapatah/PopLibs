package com.shaparapatah.poplibs

import com.github.terrakok.cicerone.Router
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.shaparapatah.poplibs.di.scope.containers.UsersScopeContainer
import com.shaparapatah.poplibs.domain.GitHubUsersRepository
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.screens.AppScreens
import com.shaparapatah.poplibs.ui.users.UsersPresenter
import okhttp3.Request
import okio.Timeout
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserPresenterTest {
    private lateinit var presenter: UsersPresenter

    @Mock
    private lateinit var repository: GitHubUsersRepository

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var appScreens: AppScreens

    @Mock
    private lateinit var usersScopeContainer: UsersScopeContainer

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = UsersPresenter(router, repository, appScreens, usersScopeContainer)
    }

    @Test
    fun `testing callback GitHub`() {
        val response = Mockito.mock(Response::class.java) as Response<GithubUserModel?>
        repository = Mockito.mock(GitHubUsersRepository::class.java)

        val call = object : Call<GithubUserModel> {
            override fun clone(): Call<GithubUserModel> {
                TODO("Not yet implemented")
            }

            override fun execute(): Response<GithubUserModel> {
                TODO("Not yet implemented")
            }

            override fun enqueue(callback: Callback<GithubUserModel>) {
                callback.onResponse(this, response)
                callback.onFailure(this, Throwable())
            }

            override fun isExecuted(): Boolean {
                TODO("Not yet implemented")
            }

            override fun cancel() {
                TODO("Not yet implemented")
            }

            override fun isCanceled(): Boolean {
                TODO("Not yet implemented")
            }

            override fun request(): Request {
                TODO("Not yet implemented")
            }

            override fun timeout(): Timeout {
                TODO("Not yet implemented")
            }
        }
    }

    @Test
    fun `callback from server`() {
        val response = Mockito.mock(Response::class.java) as Response<GithubUserModel>

        `when`(response.isSuccessful).thenReturn(false)
        assertFalse(response.isSuccessful)
    }

    @Test
    fun `testing errors`(){
        val response = Mockito.mock(Response::class.java) as Response<GithubUserModel>
        `when`(response.isSuccessful).thenReturn(false)
        presenter.loadData()
        verify(repository, times(1)).getUsers()
        //todo постоянная ошибка при работе с презентером в котором не обьявленна View
    }

    @Test
    fun `response empty`() {
        val response = Mockito.mock(Response::class.java) as Response<GithubUserModel>
        `when`(response.body()).thenReturn(null)
      //  presenter.onUserClicked(response)
        assertNull(response.body())
    }
    //todo Я не понял, как возвращать результат. Я скастил response в GithubUserModel, который
    //todo мне нужен для работы. Но его не распознаёт...

    @Test
    fun `if response empty`() {
        val response = Mockito.mock(Response::class.java) as Response<GithubUserModel>
        `when`(response.isSuccessful).thenReturn(true)
        `when`(response.body()).thenReturn(null)

        verify(router)
    }

}