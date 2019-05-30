package dev.lucasnlm.arch.common.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.common.model.Info
import dev.lucasnlm.arch.common.model.NamedInfo

class InfoAdapter: RecyclerView.Adapter<InfoViewHolder>() {

    var list: List<Info> = ArrayList()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            InfoViewType.NamedValue.ordinal -> inflater.inflate(R.layout.item_info, parent, false)
            else -> inflater.inflate(R.layout.item_tag, parent, false)
        }
        return InfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemViewType(position: Int): Int = when (list[position]) {
        is NamedInfo -> InfoViewType.NamedValue.ordinal
        else -> InfoViewType.TaggedValue.ordinal
    }

    override fun getItemId(position: Int): Long = list[position].id

    override fun getItemCount(): Int = list.size
}
