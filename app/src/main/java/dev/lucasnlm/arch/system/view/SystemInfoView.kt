package dev.lucasnlm.arch.system.view

import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.common.model.NamedInfo
import dev.lucasnlm.arch.common.view.InfoAdapter
import dev.lucasnlm.arch.system.Contracts
import dev.lucasnlm.arch.system.model.SystemInfo

class SystemInfoView: Contracts.View {

    private lateinit var detailsList: RecyclerView

    override fun onViewCreated(view: View) {
        detailsList = view.findViewById<RecyclerView>(R.id.details_list).apply {
            adapter = InfoAdapter()
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun showInfo(systemInfo: SystemInfo) {
        val context = detailsList.context

        with (detailsList.adapter as InfoAdapter) {
            list = listOf(
                NamedInfo(
                    name = context.getString(R.string.sys_screen_android_name),
                    value = systemInfo.androidName
                ),
                NamedInfo(
                    name = context.getString(R.string.sys_screen_android_api),
                    value = systemInfo.androidApi.toString()
                )
            ).filter {
                it.value != null
            }
            notifyDataSetChanged()
        }
    }
}
