package com.example.papparazitest

import android.content.res.Resources
import android.os.Bundle
import android.view.accessibility.AccessibilityNodeInfo
import android.view.accessibility.AccessibilityNodeProvider
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting

/**
 * Wrapper for NumberPicker node provider with custom accessibility announcements
 *
 * @param resources Resources for strings
 * @param contentDescription Text to announce when focusing on center of number picker
 * @param topButtonContentDescription Text to announce when focusing on top button of number picker
 * @param bottomButtonContentDescription Text to announce when focusing on bottom button of number picker
 * @param provider Provider of NumberPicker that this class will wrap
 */
class NumberPickerNodeProvider(
    private val resources: Resources,
    @StringRes private val contentDescription: Int,
    @StringRes private val topButtonContentDescription: Int,
    @StringRes private val bottomButtonContentDescription: Int,
    val provider: AccessibilityNodeProvider
) : AccessibilityNodeProvider() {

    // This constants are matching default NumberPicker
    companion object {
        @VisibleForTesting
        internal const val VIRTUAL_VIEW_ID_BOTTOM_BUTTON = 1 // VIRTUAL_VIEW_ID_INCREMENT

        @VisibleForTesting
        internal const val VIRTUAL_VIEW_ID_CENTER = 2 // VIRTUAL_VIEW_ID_INPUT

        @VisibleForTesting
        internal const val VIRTUAL_VIEW_ID_TOP_BUTTON = 3 // VIRTUAL_VIEW_ID_DECREMENT
    }

    override fun createAccessibilityNodeInfo(virtualViewId: Int): AccessibilityNodeInfo? {
        val nodeInfo = provider.createAccessibilityNodeInfo(virtualViewId) ?: return null
        when (virtualViewId) {
            VIRTUAL_VIEW_ID_TOP_BUTTON -> {
                nodeInfo.text = resources.getString(topButtonContentDescription, nodeInfo.text)
            }
            VIRTUAL_VIEW_ID_CENTER -> {
                nodeInfo.text = resources.getString(contentDescription)
            }
            VIRTUAL_VIEW_ID_BOTTOM_BUTTON -> {
                nodeInfo.text = resources.getString(bottomButtonContentDescription, nodeInfo.text)
            }
        }
        return nodeInfo
    }

    override fun performAction(virtualViewId: Int, action: Int, arguments: Bundle?): Boolean =
        provider.performAction(virtualViewId, action, arguments)

    override fun findAccessibilityNodeInfosByText(
        text: String?,
        virtualViewId: Int,
    ): MutableList<AccessibilityNodeInfo>? = provider.findAccessibilityNodeInfosByText(text, virtualViewId)

    override fun findFocus(focus: Int): AccessibilityNodeInfo? = provider.findFocus(focus)
}
