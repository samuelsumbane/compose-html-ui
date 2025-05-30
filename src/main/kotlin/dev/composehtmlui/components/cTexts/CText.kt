package dev.composehtmlui.components.cTexts

import androidx.compose.runtime.Composable
import dev.composehtmlui.C
import dev.composehtmlui.style.LocalTheme
import dev.composehtmlui.core.tokens.Spacing
import dev.composehtmlui.style.Theme
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.H6
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLHeadingElement
import org.w3c.dom.HTMLParagraphElement



@Composable
fun C.p(
    text: String,
    style: (StyleScope.(theme: Theme) -> Unit)? = null,
    attrs: (AttrsScope<HTMLParagraphElement>.() -> Unit)? = null,
) {
    val theme = LocalTheme.current
    P(
        attrs = {
            style {
                property("color", theme.primaryTextColor)
                style?.invoke(this, theme)
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
    style: (StyleScope.(theme: Theme) -> Unit)? = null,
    attrs: (AttrsScope<HTMLHeadingElement>.() -> Unit)? = null,
) {
    val theme = LocalTheme.current

    H1 (attrs = {
        classes(HeadingStyle(theme).textStyle)
        style { style?.invoke(this, theme) }
        attrs?.invoke(this)
    }) { Text(text) }
}

@Composable
fun C.h2(
    text: String,
    style: (StyleScope.(theme: Theme) -> Unit)? = null,
    attrs: (AttrsScope<HTMLHeadingElement>.() -> Unit)? = null,
) {
    val theme = LocalTheme.current

    H2 (attrs = {
        classes(HeadingStyle(theme).textStyle)
        style { style?.invoke(this, theme) }
        attrs?.invoke(this)
    }) { Text(text) }
}

@Composable
fun C.h3(
    text: String,
    style: (StyleScope.(theme: Theme) -> Unit)? = null,
    attrs: (AttrsScope<HTMLHeadingElement>.() -> Unit)? = null,
) {
    val theme = LocalTheme.current

    H3 (attrs = {
        classes(HeadingStyle(theme).textStyle)
        style { style?.invoke(this, theme) }
        attrs?.invoke(this)
    }) { Text(text) }
}

@Composable
fun C.h4(
    text: String,
    style: (StyleScope.(theme: Theme) -> Unit)? = null,
    attrs: (AttrsScope<HTMLHeadingElement>.() -> Unit)? = null,
) {
    val theme = LocalTheme.current

    H4 (attrs = {
        classes(HeadingStyle(theme).textStyle)
        style { style?.invoke(this, theme) }
        attrs?.invoke(this)
    }) { Text(text) }
}

@Composable
fun C.h5(
    text: String,
    style: (StyleScope.(theme: Theme) -> Unit)? = null,
    attrs: (AttrsScope<HTMLHeadingElement>.() -> Unit)? = null,
) {
    val theme = LocalTheme.current

    H5 (attrs = {
        classes(HeadingStyle(theme).textStyle)
        style { style?.invoke(this, theme) }
        attrs?.invoke(this)
    }) { Text(text) }
}

@Composable
fun C.h6(
    text: String,
    style: (StyleScope.(theme: Theme) -> Unit)? = null,
    attrs: (AttrsScope<HTMLHeadingElement>.() -> Unit)? = null,
) {
    val theme = LocalTheme.current

    H6 (attrs = {
        classes(HeadingStyle(theme).textStyle)
        style { style?.invoke(this, theme) }
        attrs?.invoke(this)
    }) { Text(text) }
}


class HeadingStyle(theme: Theme) : StyleSheet() {
    val textStyle by style {
        color(theme.primaryTextColor)
        padding(Spacing.MD)
    }
}
