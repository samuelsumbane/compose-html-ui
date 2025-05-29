package com.samuelsumbane.composehtmlui.components.generic

import androidx.compose.runtime.Composable
import com.samuelsumbane.composehtmlui.components.C
import com.samuelsumbane.composehtmlui.dev.composables.LocalTheme
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.dom.Img
import org.w3c.dom.HTMLImageElement

@Composable
fun C.icon(
    name: String = "apple",
    alt: String = "",
    attrs: (AttrsScope<HTMLImageElement>.() -> Unit)? = null,
    style: (StyleScope.() -> Unit)? = null,
) {

    val theme = LocalTheme.current
    Img(
        src = "icons/$name.svg",
        alt = alt,
        attrs = {
            attrs?.invoke(this)
            style {
                property("color", "purple")
                style?.invoke(this)
            }
        }
    )
}
