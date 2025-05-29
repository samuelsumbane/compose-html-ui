package com.samuelsumbane.composehtmlui.components.Inputs

import androidx.compose.runtime.Composable
import com.samuelsumbane.composehtmlui.dev.composables.LocalTheme
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.cursor
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLLabelElement


@Composable
fun checkbox(
    checked: Boolean,
    enabled: Boolean = true,
    label: String? = null,
    otherLabelAttrs: AttrsScope<HTMLLabelElement>.() -> Unit = {},
    otherLabelStyle: StyleScope.() -> Unit = {},
    otherInputStyle: StyleScope.() -> Unit = {},
    onCheckedChange: (Boolean) -> Unit,
) {
    val theme = LocalTheme.current

    Label(
        attrs = {
            style {
                display(DisplayStyle.Flex)
                alignItems(AlignItems.Center)
                gap(8.px)
                cursor(if (enabled) "pointer" else "not-allowed")
                opacity(if (enabled) 1.0 else 0.6)
                property("color", theme.primaryTextColor)
                otherLabelStyle()
            }
            otherLabelAttrs
        }
    ) {
        Input(
            type = InputType.Checkbox,
            attrs = {
                checked(checked)
                if (!enabled) { disabled() }
                onInput {
                    val isChecked = it.nativeEvent.target?.asDynamic()?.checked as? Boolean ?: false
                    onCheckedChange(isChecked)
                }
                style { otherInputStyle() }
            }
        )
        if (label != null && label.isNotBlank()) {
            Text(label)
        }
    }
}
