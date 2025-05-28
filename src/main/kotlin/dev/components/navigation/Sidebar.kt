package dev.components.navigation

import androidx.compose.runtime.Composable
import dev.components.C
import dev.components.layouts.JustifyContent
import dev.components.layouts.column
import dev.components.layouts.div
import dev.composables.LocalTheme
import dev.tokkens.BorderRadius
import dev.tokkens.Spacing
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.gap

@Composable
fun C.sidebar(
    width: String = "80px",
    height: String = "97%",
    header: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {},
    footer: @Composable () -> Unit = {},
) {
    val theme = LocalTheme.current
    C.column(
        justifyContent = JustifyContent.SPACEBETWEEN,
        width = width,
        height = height,
        style = {
            property("margin", "auto 0 auto 5px")
            property("transition", "all 0.5s")
            borderRadius(BorderRadius.MD)
            property("background",theme.sidebarBackground)
        }
    ) {
        C.div(width = "100%") { header() }

        C.column(
            width = "100%",
            style = {
                property("padding", "0")
                gap(Spacing.MD)
            }
        ) {
            content()
        }

        C.div(width = "100%") { footer() }
    }
}
