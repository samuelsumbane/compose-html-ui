package com.samuelsumbane.composehtmlui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.composehtmlui.C
import dev.composehtmlui.style.LocalTheme
import dev.composehtmlui.core.tokens.BorderRadius
import dev.composehtmlui.core.tokens.darken
import dev.composehtmlui.core.tokens.withAlpha
import dev.composehtmlui.layout.column
import dev.composehtmlui.style.Theme
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.Transition
import org.jetbrains.compose.web.css.Transitions
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.transitions
import org.jetbrains.compose.web.css.width
import org.w3c.dom.HTMLDivElement

@Composable
fun C.card(
    title: (@Composable (() -> Unit))? = null,
    content: @Composable () -> Unit,
    footer: (@Composable (() -> Unit))? = null,
    attrs: (AttrsScope<HTMLDivElement>.() -> Unit)? = null,
) {
    val theme = LocalTheme.current
    val cardStyle = remember(theme) { CardStyle(theme) }

//    Style(CardStyle(theme))
    Style(cardStyle)
    C.column(
        attrs = {
            classes(cardStyle.card)
            attrs?.invoke(this)
        },
    ) {
        title?.invoke()
        content()
        if (footer != null) footer()
    }
}


class CardStyle(theme: Theme) : StyleSheet() {
    val card by style {
        property("border-radius", BorderRadius.XL)
        property("box-shadow", "0 0 10px 2px ${theme.surface.darken(30)}")
        backgroundColor(theme.surface)
        color(theme.onSurface)
        property("width", "clamp(200px, 30vw, 700px)")
        property("height", "clamp(200px, 20vw, 600px)")
        property("transition", "0.8s")
        self + hover style {
            backgroundColor(theme.surface.darken(10))
        }
    }
}