package com.example.papparazitest

import org.junit.Test

import org.junit.Assert.*


import android.content.res.Resources
import android.view.accessibility.AccessibilityNodeInfo
import android.view.accessibility.AccessibilityNodeProvider
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.anyVararg
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @get:Rule
    val paparazzi = Paparazzi(
        renderingMode = SessionParams.RenderingMode.SHRINK,
        showSystemUi = false
    )
    private val mockInfo: AccessibilityNodeInfo = mock()

    private val internalProvider: AccessibilityNodeProvider = mock()

    private val resources: Resources = mock {
        on { getString(RES_CONTENT_DESCRIPTION) } doReturn CONTENT_DESCRIPTION
        on { getString(eq(RES_TOP_BUTTON_DESCRIPTION), anyVararg()) } doReturn TOP_BUTTON_DESCRIPTION
        on { getString(eq(RES_BOTTOM_BUTTON_DESCRIPTION), anyVararg()) } doReturn BOTTOM_BUTTON_DESCRIPTION
    }

    companion object {
        private const val RES_CONTENT_DESCRIPTION = 0
        private const val RES_TOP_BUTTON_DESCRIPTION = 1
        private const val RES_BOTTOM_BUTTON_DESCRIPTION = 2
        private const val CONTENT_DESCRIPTION = "content_description"
        private const val TOP_BUTTON_DESCRIPTION = "top_button_description"
        private const val BOTTOM_BUTTON_DESCRIPTION = "bottom_button_description"
    }

    private val customNodeProvider = NumberPickerNodeProvider(
        resources = resources,
        contentDescription = RES_CONTENT_DESCRIPTION,
        topButtonContentDescription = RES_TOP_BUTTON_DESCRIPTION,
        bottomButtonContentDescription = RES_BOTTOM_BUTTON_DESCRIPTION,
        provider = internalProvider
    )

    @Test
    fun addition_isCorrect() {
        whenever(
            internalProvider.createAccessibilityNodeInfo(4)
        ) doReturn mockInfo

        val info = customNodeProvider.createAccessibilityNodeInfo(NumberPickerNodeProvider.VIRTUAL_VIEW_ID_TOP_BUTTON)
        verify(mockInfo).text = TOP_BUTTON_DESCRIPTION

        assertEquals(4, 2 + 2)
    }

}