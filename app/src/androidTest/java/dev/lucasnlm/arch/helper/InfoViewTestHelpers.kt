package dev.lucasnlm.arch.helper

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import dev.lucasnlm.arch.common.view.InfoViewHolder

fun onListInfo(@IdRes viewId: Int): ViewInteraction = Espresso.onView(ViewMatchers.withId(viewId))

fun String.isDisplayed(): ViewInteraction =
    Espresso.onView(ViewMatchers.withText(this)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

fun ViewInteraction.isInfoDisplayed(atPosition: Int, withLabel: String, value: String) {
    perform(RecyclerViewActions.scrollToPosition<InfoViewHolder>(0))
    check(ViewAssertions.matches(infoAtPosition(atPosition, withLabel, value)))
}

fun ViewInteraction.isInfoDisplayed(atPosition: Int, withLabel: String) {
    perform(RecyclerViewActions.scrollToPosition<InfoViewHolder>(0))
    check(ViewAssertions.matches(infoAtPosition(atPosition, withLabel)))
}

fun isInfoListDisplayed(@IdRes withId: Int, infoMap: Map<String, Any>) =
    with (onListInfo(withId)) {
        infoMap.toList().forEachIndexed { index, pair ->
            isInfoDisplayed(index, pair.first, pair.second.toString())
        }
    }

fun isInfoListDisplayed(@IdRes withId: Int, infoLabels: List<String>) =
    with (onListInfo(withId)) {
        infoLabels.forEachIndexed { index, label ->
            isInfoDisplayed(index, label)
        }
    }
