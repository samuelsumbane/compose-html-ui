package dev.composehtmlui.components.navigation

import androidx.compose.runtime.Composable
import dev.composehtmlui.C
import dev.composehtmlui.style.LocalTheme
import dev.composehtmlui.core.tokens.BorderRadius
import dev.composehtmlui.core.tokens.Spacing
import dev.composehtmlui.layout.column
import dev.composehtmlui.layout.div
import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun C.sidebar(
    width: CSSNumeric? = 80.px,
    height: CSSNumeric? = 97.percent,
    style: (StyleScope.() -> Unit)? = null,
    header: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {},
    footer: @Composable () -> Unit = {},
) {
    val theme = LocalTheme.current
    C.column(
        width = width,
        height = height,
        style = {
            property("margin", "auto 0 auto 5px")
            property("transition", "all 0.5s")
            justifyContent(JustifyContent.SpaceBetween)
            borderRadius(BorderRadius.MD)
            backgroundColor(theme.sidebarBackground)
            style?.invoke(this)
        }
    ) {
        C.div(width = 100.percent) { header() }

        C.column(
            width = 100.percent,
            style = {
                padding(0.px)
                gap(Spacing.MD)
            }
        ) {
            content()
        }

        C.div(width = 100.percent) { footer() }
    }
}
