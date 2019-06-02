package dev.lucasnlm.arch.core

import android.view.View
import com.nhaarman.mockitokotlin2.mock
import dev.lucasnlm.arch.core.presenter.BasePresenter
import dev.lucasnlm.arch.core.view.BaseView
import org.junit.Assert.*
import org.junit.Test

class BasePresenterTest {
    private open class GenericBaseView: BaseView {
        override fun onViewCreated(view: View) { }
    }

    private open class GenericPresenter: BasePresenter<GenericBaseView>() {
        fun getView() = this.view
    }

    @Test
    fun testOnDestroy() {
        val genericPresenter = GenericPresenter()
        genericPresenter.onAttach(mock())
        assertNotNull(genericPresenter.getView())
        genericPresenter.onDestroy()
        assertNull(genericPresenter.getView())
    }
}
