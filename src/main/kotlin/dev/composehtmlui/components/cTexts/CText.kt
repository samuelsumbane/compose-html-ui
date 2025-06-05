package dev.composehtmlui.components.cTexts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.composehtmlui.C
import dev.composehtmlui.style.LocalTheme
import dev.composehtmlui.core.tokens.Spacing
import dev.composehtmlui.style.Theme
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.H6
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLHeadingElement
import org.w3c.dom.HTMLParagraphElement
import org.w3c.dom.HTMLSpanElement


@Composable
fun C.span(
    text: String,
    attrs: (AttrsScope<HTMLSpanElement>.() -> Unit)? = null,
) {
    val theme = LocalTheme.current
    val textStyleSheet = remember(theme) { TextStyle(theme) }
    Style(textStyleSheet)
    Span(
        attrs = {
            classes(textStyleSheet.spanStyle)
            attrs?.invoke(this)
        }
    ) {
        Text(text)
    }
}


@Composable
fun C.p(
    text: String,
    attrs: (AttrsScope<HTMLParagraphElement>.() -> Unit)? = null,
) {
    val theme = LocalTheme.current
    P(
        attrs = {
            style {
                property("color", theme.primaryTextColor)
            }
            attrs?.invoke(this)
        }
    ) {
        Text(text)
    }
}

@Composable
fun C.h1(
    text: String,
    attrs: (AttrsScope<HTMLHeadingElement>.() -> Unit)? = null,
) {
    val theme = LocalTheme.current
    Style(TextStyle(theme))
    H1 (attrs = {
        classes(TextStyle(theme).textStyle)
        attrs?.invoke(this)
    }) { Text(text) }
}

@Composable
fun C.h2(
    text: String,
    attrs: (AttrsScope<HTMLHeadingElement>.() -> Unit)? = null,
) {
    val theme = LocalTheme.current
    Style(TextStyle(theme))
    H2 (attrs = {
        classes(TextStyle(theme).textStyle)
        attrs?.invoke(this)
    }) { Text(text) }
}

@Composable
fun C.h3(
    text: String,
    attrs: (AttrsScope<HTMLHeadingElement>.() -> Unit)? = null,
) {
    val theme = LocalTheme.current
    Style(TextStyle(theme))
    H3 (attrs = {
        classes(TextStyle(theme).textStyle)
        attrs?.invoke(this)
    }) { Text(text) }
}

@Composable
fun C.h4(
    text: String,
    attrs: (AttrsScope<HTMLHeadingElement>.() -> Unit)? = null,
) {
    val theme = LocalTheme.current
    Style(TextStyle(theme))
    H4 (attrs = {
        classes(TextStyle(theme).textStyle)
        attrs?.invoke(this)
    }) { Text(text) }
}

@Composable
fun C.h5(
    text: String,
    attrs: (AttrsScope<HTMLHeadingElement>.() -> Unit)? = null,
) {
    val theme = LocalTheme.current
    Style(TextStyle(theme))
    H5 (attrs = {
        classes(TextStyle(theme).textStyle)
        attrs?.invoke(this)
    }) { Text(text) }
}

@Composable
fun C.h6(
    text: String,
    attrs: (AttrsScope<HTMLHeadingElement>.() -> Unit)? = null,
) {
    val theme = LocalTheme.current
    Style(TextStyle(theme))
    H6 (attrs = {
        classes(TextStyle(theme).textStyle)
        attrs?.invoke(this)
    }) { Text(text) }
}


class TextStyle(theme: Theme) : StyleSheet() {
    val textStyle by style {
        color(theme.primaryTextColor)
        padding(Spacing.MD)
    }

    val spanStyle by style {
        property("color", theme.errorColor)
        fontSize(13.px)
        fontWeight(400)
    }
}
