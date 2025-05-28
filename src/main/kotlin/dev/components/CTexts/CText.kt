package dev.components.CTexts

import androidx.compose.runtime.Composable
import dev.components.C
import dev.composables.LocalTheme
import dev.style.Theme
import dev.tokkens.Spacing
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.H6
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLButtonElement

@Composable
fun C.p(
    text: String,
    style: (StyleScope.(theme: Theme) -> Unit)? = null,
    attrs: (AttrsScope<HTMLButtonElement>.() -> Unit)? = null,

) {
    val theme = LocalTheme.current
    P(
        attrs = {
            style {
                property("color", theme.primaryTextColor)
                if (style != null) style(theme)
            }
        }
    ) {
        Text(text)
    }
}

@Composable
fun C.h2(text: String, style: (StyleScope.(theme: Theme) -> Unit)? = null) {
    val theme = LocalTheme.current

    H2 (attrs = {
        style {
            property("color", theme.primaryTextColor)
            property("padding", Spacing.MD)
            if (style != null) style(theme)
        }
    }) { Text(text) }
}

@Composable
fun C.h3(text: String, style: (StyleScope.(theme: Theme) -> Unit)? = null) {
    val theme = LocalTheme.current

    H3 (attrs = {
        style {
            property("color", theme.primaryTextColor)
            property("padding", Spacing.MD)
            if (style != null) style(theme)
        }
    }) { Text(text) }
}

@Composable
fun C.h4(text: String, style: (StyleScope.(theme: Theme) -> Unit)? = null) {
    val theme = LocalTheme.current

    H4 (attrs = {
        style {
            property("color", theme.primaryTextColor)
            property("padding", Spacing.MD)
            if (style != null) style(theme)
        }
    }) { Text(text) }
}

@Composable
fun C.h5(text: String, style: (StyleScope.(theme: Theme) -> Unit)? = null) {
    val theme = LocalTheme.current

    H5 (attrs = {
        style {
            property("color", theme.primaryTextColor)
            property("padding", Spacing.MD)
            if (style != null) style(theme)
        }
    }) { Text(text) }
}

@Composable
fun C.h6(text: String, style: (StyleScope.(theme: Theme) -> Unit)? = null) {
    val theme = LocalTheme.current

    H6 (attrs = {
        style {
            property("color", theme.primaryTextColor)
            property("padding", Spacing.MD)
            if (style != null) style(theme)
        }
    }) { Text(text) }
}
