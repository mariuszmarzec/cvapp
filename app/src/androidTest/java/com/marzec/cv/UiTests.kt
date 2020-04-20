package com.marzec.cv

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.marzec.cv.api.CvApi
import com.marzec.cv.api.model.CvDto
import com.marzec.cv.ui.home.HomeActivity
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.SingleSource
import junit.framework.Assert.assertTrue
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Test
import java.util.concurrent.Callable

class UiTests {

    private lateinit var cvApi: CvApi

    @Before
    fun setUp() {
        val app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        val testAppComponent = (app as TestApp).component
        cvApi = testAppComponent.getCvApi()
    }

    @Test
    fun check_header_is_displayed() {
        val stubCvDto = stubCvDto(
            firstName = "first",
            lastName = "last",
            email = "email",
            phone = "phone",
            imageUrl = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"
        )

        whenever(cvApi.getCv()).thenReturn(Single.just(stubCvDto))

        ActivityScenario.launch(HomeActivity::class.java)
        onView(CoreMatchers.allOf(withText(R.string.cv), withId(R.id.cvHeader))).check(matches(isDisplayed()))
        onView(withId(R.id.fullName)).check(matches(isDisplayed()))
        onView(withId(R.id.email)).check(matches(isDisplayed()))
        onView(withId(R.id.phone)).check(matches(isDisplayed()))
    }

    @Test
    fun if_error_show_dialog_then_close_app() {
        whenever(cvApi.getCv()).thenReturn(Single.error(Throwable()))
        val scenario = ActivityScenario.launch(HomeActivity::class.java)

        onView(withText(R.string.error_dialog_title)).check(matches(isDisplayed()))
        onView(withText(R.string.error_dialog_message_unknown_error_try_again)).check(matches(isDisplayed()))
        onView(withText(R.string.error_dialog_retry)).check(matches(isDisplayed()))

        onView(withText(R.string.error_dialog_leave_app)).perform(ViewActions.click())

        assertTrue(scenario.state == Lifecycle.State.DESTROYED)
    }

    @Test
    fun if_dialog_error_show_click_retry_and_show_content() {
        val stubCvDto = stubCvDto(
            firstName = "first",
            lastName = "last",
            email = "email",
            phone = "phone",
            imageUrl = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"
        )

        whenever(cvApi.getCv()).thenReturn(Single.defer(object : Callable<SingleSource<CvDto>> {
            var hasBeenSubscribedTo = false
            override fun call(): SingleSource<CvDto> {
                if (!hasBeenSubscribedTo) {
                    hasBeenSubscribedTo = true
                    return Single.error(Exception())
                }
                return Single.just(stubCvDto)

            }
        }))

        ActivityScenario.launch(HomeActivity::class.java)

        onView(withText(R.string.error_dialog_title)).check(matches(isDisplayed()))
        onView(withText(R.string.error_dialog_message_unknown_error_try_again)).check(matches(isDisplayed()))
        onView(withText(R.string.error_dialog_retry)).check(matches(isDisplayed()))

        whenever(cvApi.getCv()).thenReturn(Single.just(stubCvDto))

        onView(withText(R.string.error_dialog_retry)).perform(ViewActions.click())

        onView(withText(R.string.section_header_experience)).check(matches(isDisplayed()))
        onView(withText(R.string.section_header_education)).check(matches(isDisplayed()))
        onView(withText(R.string.section_header_skills)).check(matches(isDisplayed()))

        verify(cvApi).getCv()

    }

}
