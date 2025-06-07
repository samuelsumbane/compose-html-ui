package dev.composehtmlui.layout

import androidx.compose.runtime.Composable
import dev.composehtmlui.C
import dev.composehtmlui.core.tokens.Spacing
import dev.composehtmlui.style.AppColors
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.CSSLengthValue
import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.alignSelf
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement


@Composable
fun C.div(
    attrs: (AttrsScope<HTMLDivElement>.() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Div(
        attrs = {
            attrs?.invoke(this)
        }
    ) { content() }
}

@Composable
fun C.row(
    attrs: (AttrsScope<HTMLDivElement>.() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Style(DivStyle())
    C.div(
        attrs = {
            classes(DivStyle().rowStyle)
            attrs?.invoke(this)
        },
    ) { content() }
}

/**
 * Column extends from 'row' function and
 * adding 'flex-direction: colum' css rule
 */

@Composable
fun C.column(
    attrs: (AttrsScope<HTMLDivElement>.() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Style(DivStyle())
    C.row(
        attrs = {
            classes(DivStyle().columnStyle)
            attrs?.invoke(this)
            style {
                alignItems(AlignItems.Start)
            }
        },
    ) { content() }
}


class DivStyle : StyleSheet() {
    val rowStyle by style {
        property("display", "flex")
        padding(Spacing.SM)
        gap(Spacing.SM)
        alignItems(AlignItems.Center)
        justifyContent(JustifyContent.FlexStart)
    }

    val columnStyle by style {
        flexDirection(FlexDirection.Column)
    }
}