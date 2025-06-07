package dev.composehtmlui.components.inputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.composehtmlui.C
import dev.composehtmlui.components.cTexts.label
import dev.composehtmlui.components.cTexts.p
import dev.composehtmlui.components.cTexts.span
import dev.composehtmlui.style.LocalTheme
import dev.composehtmlui.core.tokens.BorderRadius
import dev.composehtmlui.core.tokens.FontSize
import dev.composehtmlui.core.tokens.Spacing
import dev.composehtmlui.core.tokens.withAlpha
import dev.composehtmlui.layout.column
import dev.composehtmlui.style.AppColors
import dev.composehtmlui.style.Theme
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.attributes.maxLength
import org.jetbrains.compose.web.attributes.min
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.events.SyntheticInputEvent
import org.w3c.dom.HTMLInputElement


@Composable
fun <K> C.inputField(
    value: String,
    type: InputType<K>,
    placeholder: String? = null,
    maxLength: Int = 0,
    enabled: Boolean = true,
    labelText: String? = null,
    errorText: String? = null,
    onValueChange: (SyntheticInputEvent<K, HTMLInputElement>) -> Unit,
) {
    val theme = LocalTheme.current
    val inputStyle = remember(theme) { InputFieldStyle(theme) }
    Style(inputStyle)
    C.column(
        attrs = {
            classes(inputStyle.inputColumnContainer)
            style {
                padding(0.px)
                gap(3.px)
            }
        }
    ) {

        labelText?.let {
            C.label(it)
        }

        Input(
            type = type,
            attrs = {
                classes(inputStyle.inputFieldStyle)
                id("")
                value(value)
                if (placeholder != null && placeholder.isNotBlank()) {
                    placeholder(placeholder)
                }
                min("0")
                if (maxLength > 0) { maxLength(maxLength) }
                onInput { event -> onValueChange(event) }
                if (!enabled) disabled()
            }
        )

        errorText?.let {
            C.span(it)
        }
    }

}


class InputFieldStyle(theme: Theme) : StyleSheet() {
    val inputColumnContainer by style {
        width(100.percent)
    }

    val inputFieldStyle by style {
        backgroundColor(theme.inputBackground)
        width(100.percent)
        color(theme.inputText)
        padding(Spacing.SM)
        fontSize(FontSize.BODY)
        property("border", "none")
        property("border-radius", BorderRadius.MD)
        property("outline", "1px solid ${theme.primary.withAlpha(0.7)}")
    }
}

