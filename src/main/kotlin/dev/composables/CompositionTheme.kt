package dev.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import dev.style.LightTheme
import dev.style.Theme

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