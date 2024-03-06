package com.example.papparazitest

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import org.junit.Rule
import org.junit.Test
import java.util.logging.LogManager

class MainScreenScreenshotTest {
    @get:Rule
    val paparazzi = Paparazzi(
        theme = "",
        renderingMode = SessionParams.RenderingMode.SHRINK
    )

    @Test
    fun `test this main screen`() {
        paparazzi.snapshot {
            CompositionLocalProvider(LocalInspectionMode provides true) {
                GreetingPreview()
            }

        }
    }
}