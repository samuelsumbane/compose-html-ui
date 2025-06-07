package dev.composehtmlui.components.inputs

import androidx.compose.runtime.Composable
import dev.composehtmlui.C
import dev.composehtmlui.style.AppColors
import dev.composehtmlui.style.LocalTheme
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.builders.InputAttrsScope
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.attributes.size
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.cursor
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLLabelElement


@Composable
fun C.checkbox(
    checked: Boolean,
    enabled: Boolean = true,
    label: String? = null,
    labelAttrs: (AttrsScope<HTMLLabelElement>.() -> Unit)? = null,
    inputAttrs: (InputAttrsScope<Boolean>.() -> Unit)? = null,
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
                color(theme.primaryTextColor)
            }
            labelAttrs?.invoke(this)
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
                inputAttrs?.invoke(this)
            }
        )
        if (label != null && label.isNotBlank()) {
            Text(label)
        }
    }
}
