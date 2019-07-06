package dev.lucasnlm.arch.main.presenter

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import dev.lucasnlm.arch.main.interactor.MainActivityInteractor
import dev.lucasnlm.arch.main.view.MainActivityView
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt

class MainActivityPresenterTest {

    private val mockView: MainActivityView = mock()
    private val mainActivityInteractor: MainActivityInteractor = mock()

    private fun givenMainActivityPresenter() = MainActivityPresenter(mainActivityInteractor).apply {
        this.onAttach(mockView)
    }

    @Test
    fun testOnCreate() {
        givenMainActivityPresenter().onCreate()
        verify(mockView).setOnNavigationItemSelectedListener(any())
        verify(mockView).setTitle(anyInt())
        verify(mockView).replaceFragment(any())
    }

    @Test
    fun testOnDestroy() {
        val presenter = givenMainActivityPresenter()
        presenter.onCreate()
        verify(mockView).setOnNavigationItemSelectedListener(any())
        presenter.onDestroy()
        verify(mockView).setOnNavigationItemSelectedListener(null)
    }
}
