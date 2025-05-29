package dev.composehtmlui.components.Inputs

import androidx.compose.runtime.Composable
import androidx.compose.web.attributes.SelectAttrsScope
import dev.composehtmlui.C
import dev.composehtmlui.style.LocalTheme
import dev.composehtmlui.core.tokkens.BorderRadius
import dev.composehtmlui.core.tokkens.FontSize
import dev.composehtmlui.core.tokkens.Spacing
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.dom.ElementScope
import org.jetbrains.compose.web.dom.Select
import org.w3c.dom.HTMLSelectElement

@Composable
fun C.select(
    onChange: (String?) -> Unit,
    attrs: (SelectAttrsScope.() -> Unit)? = null,
    style: (StyleScope.() -> Unit)? = null,
    enabled: Boolean = true,
    content: @Composable (ElementScope<HTMLSelectElement>.() -> Unit)? = null
) {
    val theme = LocalTheme.current

    Select(
        attrs = {
            if (!enabled) disabled()
            style {
                property("background", theme.inputFieldBgColor)
                property("color", theme.inputFieldTextColor)
                property("padding", Spacing.SM)
                property("border", "none")
                property("border-radius", BorderRadius.MD)
                property("font-size", FontSize.BODY)
                style?.invoke(this)
            }
            attrs?.invoke(this)
            onChange { onChange(it.value) }
        }
    ) {  if (content != null) { content() }  }
}
