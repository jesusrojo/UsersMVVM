package com.jesusrojo.usersmvvm.presentation.ui


import com.jesusrojo.usersmvvm.R
import com.jesusrojo.usersmvvm.utils.BaseUITest
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import org.junit.Test


class UsersActivityTest : BaseUITest(){

    @Test
    fun displayScreenTitle() {
        assertDisplayed("UsersActivity")
    }

    @Test
    fun displayLayoutInitial() {
        assertDisplayed(R.id.swipe_layout_container_items)
        assertDisplayed(R.id.recycler_view_items)
    }

    @Test
    fun hidesLoader() {
        assertNotDisplayed(R.id.progress_bar_items)
    }


//    @Test
//    fun displaysLoaderWhileFetchingDatas() {
//        IdlingRegistry.getInstance().unregister(idlingResource)
////        Thread.sleep(500)
//        assertDisplayed(R.id.progress_bar_items)
//    }

    override fun tearDownChild() {}//empty
}