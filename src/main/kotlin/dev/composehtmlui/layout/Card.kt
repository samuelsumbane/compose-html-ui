package com.samuelsumbane.composehtmlui.components

import androidx.compose.runtime.Composable
import dev.composehtmlui.C
import dev.composehtmlui.style.LocalTheme
import dev.composehtmlui.core.tokkens.BorderRadius
import dev.composehtmlui.layout.column
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.StyleScope
import org.w3c.dom.HTMLDivElement

@Composable
fun C.card(
    title: (@Composable (() -> Unit))? = null,
    content: @Composable () -> Unit,
    footer: (@Composable (() -> Unit))? = null,
    width: String = "clamp(200px, 30vw, 400px)",
    height: String = "clamp(200px, 20vw, 600px)",
    attrs: (AttrsScope<HTMLDivElement>.() -> Unit)? = null,
    style: (StyleScope.() -> Unit)? = null,
) {
    val theme = LocalTheme.current
    C.column(
        style = {
            property("border-radius", BorderRadius.XL)
            property("box-shadow", "0 0 10px 2px ${theme.primaryColor}")
            property("background", theme.layoutsBackground)
            property("width", width)
            property("height", height)
            style?.invoke(this)
        },
        attrs = { attrs?.invoke(this) }
    ) {
        title?.invoke()
        content()
        if (footer != null) footer()
    }
}
