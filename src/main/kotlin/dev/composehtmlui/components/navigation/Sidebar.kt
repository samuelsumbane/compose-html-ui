package dev.composehtmlui.components.navigation

import androidx.compose.runtime.Composable
import dev.composehtmlui.C
import dev.composehtmlui.style.LocalTheme
import dev.composehtmlui.core.tokens.BorderRadius
import dev.composehtmlui.core.tokens.Spacing
import dev.composehtmlui.layout.column
import dev.composehtmlui.layout.div
import org.jetbrains.compose.web.attributes.AttrsScope
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
import org.jetbrains.compose.web.css.width
import org.w3c.dom.HTMLDivElement

@Composable
fun C.sidebar(
    attrs: (AttrsScope<HTMLDivElement>.() -> Unit)? = null,
    header: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {},
    footer: @Composable () -> Unit = {},
) {
    val theme = LocalTheme.current
    C.column(
        attrs = {
            style {
                property("margin", "auto 0 auto 5px")
                property("transition", "all 0.5s")
                justifyContent(JustifyContent.SpaceBetween)
                borderRadius(BorderRadius.MD)
                backgroundColor(theme.sidebarBackground)
            }
            attrs?.invoke(this)
        }
    ) {
        C.div(
            attrs = {
                style {
                    width(100.percent)
                }
            }
        ) { header() }

        C.column(
            attrs = {
                style {
                    width(100.percent)
                    padding(0.px)
                    gap(Spacing.MD)
                }
            }
        ) {
            content()
        }

        C.div(
            attrs = {
                style {
                    width(100.percent)
                }
            }
        ) { footer() }
    }
}
