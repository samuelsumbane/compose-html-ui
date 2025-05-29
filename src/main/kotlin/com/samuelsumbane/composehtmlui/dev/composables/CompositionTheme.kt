package com.samuelsumbane.composehtmlui.dev.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import com.samuelsumbane.composehtmlui.style.LightTheme
import com.samuelsumbane.composehtmlui.style.Theme

val LocalTheme = compositionLocalOf<Theme> { LightTheme }

@Composable
fun ComposeHtmlTheme(
    theme: Theme = LightTheme,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalTheme provides theme) {
        content()
    }
}