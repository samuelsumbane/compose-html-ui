package com.samuelsumbane.composehtmlui.components.navigation

import androidx.compose.runtime.Composable
import com.samuelsumbane.composehtmlui.components.C
import com.samuelsumbane.composehtmlui.layout.JustifyContent
import com.samuelsumbane.composehtmlui.layout.column
import com.samuelsumbane.composehtmlui.layout.div
import com.samuelsumbane.composehtmlui.dev.composables.LocalTheme
import com.samuelsumbane.composehtmlui.core.tokkens.BorderRadius
import com.samuelsumbane.composehtmlui.core.tokkens.Spacing
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
