package dev.composehtmlui.components.inputs

import androidx.compose.runtime.Composable
import androidx.compose.web.attributes.SelectAttrsScope
import dev.composehtmlui.C
import dev.composehtmlui.style.LocalTheme
import dev.composehtmlui.core.tokens.BorderRadius
import dev.composehtmlui.core.tokens.FontSize
import dev.composehtmlui.core.tokens.Spacing
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.padding
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
                backgroundColor(theme.inputFieldBgColor)
                color(theme.inputFieldTextColor)
                padding(Spacing.SM)
                fontSize(FontSize.BODY)
                property("border-radius", BorderRadius.MD)
                property("border", "none")
                style?.invoke(this)
            }
            attrs?.invoke(this)
            onChange { onChange(it.value) }
        }
    ) {  if (content != null) { content() }  }
}
