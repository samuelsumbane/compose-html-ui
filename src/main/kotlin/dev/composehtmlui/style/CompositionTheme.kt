package dev.composehtmlui.style

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

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