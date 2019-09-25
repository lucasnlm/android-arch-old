package dev.lucasnlm.arch.phone.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.common.model.Info
import dev.lucasnlm.arch.common.view.InfoAdapter
import dev.lucasnlm.arch.common.view.setupList
import dev.lucasnlm.arch.phone.Contracts
import dev.lucasnlm.arch.phone.model.PhoneInfo

class PhoneInfoView : Contracts.View {
    private lateinit var displayList: RecyclerView

    override fun onViewCreated(view: View) {
        displayList = view.setupList(R.id.display_list)
    }

    override fun showInfo(phoneInfo: PhoneInfo) {
        val context = displayList.context

        with(displayList.adapter as InfoAdapter) {
            setListAndNotify(
                listOf(
                    Info.Named(
                        name = context.getString(R.string.dev_screen_width),
                        value = phoneInfo.displayInfo.width.toString()
                    ),
                    Info.Named(
                        name = context.getString(R.string.dev_screen_height),
                        value = phoneInfo.displayInfo.height.toString()
                    ),
                    Info.Named(
                        name = context.getString(R.string.dev_screen_dpi),
                        value = phoneInfo.displayInfo.dpi.toString()
                    ),
                    Info.Named(
                        name = context.getString(R.string.dev_screen_density),
                        value = phoneInfo.displayInfo.dpiLabel
                    )
                ).filter {
                    it.hasValue()
                }
            )
        }
    }
}
