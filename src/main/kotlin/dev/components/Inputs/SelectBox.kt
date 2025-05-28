package dev.components.Inputs

import androidx.compose.runtime.Composable
import androidx.compose.web.attributes.SelectAttrsScope
import dev.composables.LocalTheme
import dev.tokkens.BorderRadius
import dev.tokkens.FontSize
import dev.tokkens.Spacing
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.attributes.maxLength
import org.jetbrains.compose.web.attributes.min
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.dom.ElementScope
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Select
import org.jetbrains.compose.web.events.SyntheticInputEvent
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLInputElement
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
