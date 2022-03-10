package com.shaparapatah.poplibs.ui.users

import com.shaparapatah.poplibs.domain.GitHubUsersRepository
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UsersPresenterTest {

    private lateinit var presenter: UsersPresenter

    @Mock
    private lateinit var repository: GitHubUsersRepository

    @Mock
    private lateinit var view: UsersView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
     //   presenter = UsersPresenter()
    }

}