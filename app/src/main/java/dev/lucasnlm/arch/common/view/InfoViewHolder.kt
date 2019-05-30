package dev.lucasnlm.arch.common.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.common.model.Info
import dev.lucasnlm.arch.common.model.NamedInfo
import dev.lucasnlm.arch.common.model.TagInfo

class InfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val name: TextView = itemView.findViewById(R.id.info_name)
    private val value: TextView? = itemView.findViewById(R.id.info_value)

    fun bind(info: Info) = when (info) {
        is TagInfo -> {
            name.text = info.name
        }
        is NamedInfo -> {
            name.text = info.name
            value?.text = info.value
        }
        else -> throw IllegalArgumentException("Illegal argument $info")
    }
}
