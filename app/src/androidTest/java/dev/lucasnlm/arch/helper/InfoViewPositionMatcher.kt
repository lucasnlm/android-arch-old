package dev.lucasnlm.arch.helper

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import dev.lucasnlm.arch.common.view.InfoViewHolder
import org.hamcrest.Description
import org.hamcrest.Matcher

private class InfoViewPositionMatcher(val position: Int, val label: String, val value: String?): BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
    override fun describeTo(description: Description?) {
        description?.appendText("has item at position: $position, label: $label and value: $value")
    }

    override fun matchesSafely(item: RecyclerView?): Boolean {
        val viewHolder: InfoViewHolder? = item?.findViewHolderForAdapterPosition(position) as? InfoViewHolder
        return viewHolder?.let { it.getName() == label && it.getName() == value } ?: false
    }
}

private class AnyInfoViewPositionMatcher(val position: Int, val label: String): BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
    override fun describeTo(description: Description?) {
        description?.appendText("has item at position: $position, label: $label and any value")
    }

    override fun matchesSafely(item: RecyclerView?): Boolean {
        val viewHolder: InfoViewHolder? = item?.findViewHolderForAdapterPosition(position) as InfoViewHolder
        return viewHolder?.let { it.getName() == label } ?: false
    }
}

fun infoAtPosition(position: Int, label: String, value: String): Matcher<View> = InfoViewPositionMatcher(position, label, value)

fun infoAtPosition(position: Int, label: String): Matcher<View> = AnyInfoViewPositionMatcher(position, label)
