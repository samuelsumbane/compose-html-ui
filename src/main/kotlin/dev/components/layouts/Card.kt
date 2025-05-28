package dev.components

import androidx.compose.runtime.Composable
import dev.components.CTexts.p
import dev.components.layouts.column
import dev.composables.LocalTheme
import dev.style.Theme
import dev.tokkens.BorderRadius
import dev.tokkens.FontSize
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.StyleScope
import org.w3c.dom.HTMLButtonElement

@Composable
fun C.card(
    title: String? = null,
    content: @Composable () -> Unit,
    footer: (@Composable (() -> Unit))? = null,
    width: String = "clamp(200px, 30vw, 400px)",
    height: String = "clamp(200px, 20vw, 600px)",
    attrs: (AttrsScope<HTMLButtonElement>.() -> Unit)? = null,
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
        }
    ) {
        if (title != null) {
            C.p(title, style = {
                property("font-size", FontSize.TITLE)
                if (style != null) style()
            }, attrs = {
                if (attrs != null) attrs()
            })
        }
        content()
        if (footer != null) footer()
    }
}
