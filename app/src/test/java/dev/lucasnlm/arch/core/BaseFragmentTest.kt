package dev.lucasnlm.arch.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import dev.lucasnlm.arch.core.presenter.BasePresenter
import dev.lucasnlm.arch.core.view.BaseView
import org.junit.Test

class BaseFragmentTest {
    private open class GenericBaseView: BaseView {
        override fun onViewCreated(view: View) { }
    }

    private open class GenericPresenter: BasePresenter<GenericBaseView>()

    private class MockBaseFragment: BaseFragment<GenericBaseView, GenericPresenter>() {
        override val layoutRes: Int = 1
    }

    @Test
    fun testPresenterOnCreate() {
        val mockFragment = givenMockedFragment()
        mockFragment.onCreate(mock())

        verify(mockFragment.mvpPresenter, times(1)).onAttach(any())
    }

    @Test
    fun testPresenterOnViewCreated() {
        val mockFragment = givenMockedFragment()
        mockFragment.onViewCreated(mock(), mock())

        verify(mockFragment.mvpView, times(1)).onViewCreated(any())
        verify(mockFragment.mvpPresenter, times(1)).onCreate()
    }

    @Test
    fun testPresenterOnDestroy() {
        val mockFragment = givenMockedFragment()
        mockFragment.onDestroy()

        verify(mockFragment.mvpPresenter, times(1)).onDestroy()
    }

    private fun givenMockedFragment(): MockBaseFragment = MockBaseFragment().apply {
        this.mvpPresenter = mock()
        this.mvpView = mock()
    }
}
