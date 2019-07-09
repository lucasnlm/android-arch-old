package dev.lucasnlm.arch.helper

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.ViewAction
import org.hamcrest.Matcher

private class WaitFor(private val millis: Long): ViewAction {

    override fun getDescription(): String = "Wait for $millis milliseconds."

    override fun getConstraints(): Matcher<View> = isRoot()

    override fun perform(uiController: UiController?, view: View?) {
        uiController?.loopMainThreadForAtLeast(millis)
    }
}

fun waitFor(millis: Long) = onView(isRoot()).perform( WaitFor(millis))
