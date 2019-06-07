package dev.lucasnlm.arch.common.view

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun View.setupList(@IdRes resId: Int): RecyclerView = findViewById<RecyclerView>(resId).apply {
    adapter = InfoAdapter()
    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
}
