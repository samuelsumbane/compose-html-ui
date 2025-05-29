package com.samuelsumbane.composehtmlui.components.Inputs

import androidx.compose.runtime.Composable
import com.samuelsumbane.composehtmlui.dev.composables.LocalTheme
import com.samuelsumbane.composehtmlui.core.tokkens.BorderRadius
import com.samuelsumbane.composehtmlui.core.tokkens.FontSize
import com.samuelsumbane.composehtmlui.core.tokkens.Spacing
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.attributes.maxLength
import org.jetbrains.compose.web.attributes.min
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.events.SyntheticInputEvent
import org.w3c.dom.HTMLInputElement


@Composable
fun <K> inputField(
    value: String,
    type: InputType<K>,
    placeholder: String? = null,
    maxLength: Int = 0,
    enabled: Boolean = true,
    style: (StyleScope.() -> Unit)? = null,
    onValueChange: (SyntheticInputEvent<K, HTMLInputElement>) -> Unit,
) {
    val theme = LocalTheme.current
    Input(
        type = type,
        attrs = {
            classes("formTextInput")
            value(value)
            if (placeholder != null && placeholder.isNotBlank()) {
                placeholder(placeholder)
            }
            min("0")
            if (maxLength > 0) { maxLength(maxLength) }
            onInput { event -> onValueChange(event) }
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
        }
    )
}


