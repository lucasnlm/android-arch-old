package dev.lucasnlm.arch.common.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.common.model.Info

class InfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val name: TextView = itemView.findViewById(R.id.info_name)
    private val value: TextView? = itemView.findViewById(R.id.info_value)

    fun bind(info: Info) = when (info) {
        is Info.Tag -> {
            name.text = info.name
        }
        is Info.Named -> {
            name.text = info.name
            value?.text = info.value
        }
    }

    fun getName(): String = name.text.toString()

    fun getValue(): String? = value?.text.toString()
}
