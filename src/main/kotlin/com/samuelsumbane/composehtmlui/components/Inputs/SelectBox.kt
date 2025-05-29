package com.samuelsumbane.composehtmlui.components.Inputs

import androidx.compose.runtime.Composable
import androidx.compose.web.attributes.SelectAttrsScope
import com.samuelsumbane.composehtmlui.dev.composables.LocalTheme
import com.samuelsumbane.composehtmlui.core.tokkens.BorderRadius
import com.samuelsumbane.composehtmlui.core.tokkens.FontSize
import com.samuelsumbane.composehtmlui.core.tokkens.Spacing
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.dom.ElementScope
import org.jetbrains.compose.web.dom.Select
import org.w3c.dom.HTMLSelectElement

@Composable
fun select(
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
                if (style != null) { style() }
            }
            if (attrs != null) { attrs() }

            onChange { onChange(it.value) }
        }
    ) {  if (content != null) { content() }  }
}
