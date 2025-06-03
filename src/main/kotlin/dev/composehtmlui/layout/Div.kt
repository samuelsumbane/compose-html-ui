package dev.composehtmlui.layout

import androidx.compose.runtime.Composable
import dev.composehtmlui.C
import dev.composehtmlui.core.tokens.Spacing
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.CSSLengthValue
import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement


@Composable
fun C.div(
    width: CSSNumeric? = null,
    height: CSSNumeric? = null,
    attrs: (AttrsScope<HTMLDivElement>.() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Div(
        attrs = {
            style {
                width?.let { width(width)}
                height?.let { height(height) }
                alignItems(AlignItems.Start)
            }
            attrs?.invoke(this)
        }
    ) { content() }
}

@Composable
fun C.row(
    width: CSSNumeric? = null,
    height: CSSNumeric? = null,
    padding: CSSLengthValue? = Spacing.SM,
    attrs: (AttrsScope<HTMLDivElement>.() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Style(DivStyle())
    C.div(
        width = width,
        height = height,
        attrs = {
            classes(DivStyle().rowStyle)
            style {
                padding?.let { padding(padding) }
            }
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
    width: CSSNumeric? = null,
    height: CSSNumeric? = null,
    padding: CSSLengthValue? = Spacing.SM,
    attrs: (AttrsScope<HTMLDivElement>.() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Style(DivStyle())
    C.row(
        width = width,
        height = height,
        attrs = {
            classes(DivStyle().columnStyle)
            style {
                padding?.let { padding(padding) }
            }
            attrs?.invoke(this)
        },
    ) { content() }
}


class DivStyle : StyleSheet() {
    val rowStyle by style {
        property("display", "flex")
        justifyContent(JustifyContent.FlexStart)
    }

    val columnStyle by style {
        flexDirection(FlexDirection.Column)
        justifyContent(JustifyContent.FlexStart)
    }
}