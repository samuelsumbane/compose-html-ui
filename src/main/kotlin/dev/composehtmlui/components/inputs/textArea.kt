package dev.composehtmlui.components.inputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.composehtmlui.C
import dev.composehtmlui.components.cTexts.span
import dev.composehtmlui.layout.column
import dev.composehtmlui.style.LocalTheme
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.builders.TextAreaAttrsScope
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.TextArea
import org.jetbrains.compose.web.events.SyntheticInputEvent
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTextAreaElement

@Composable
fun C.textArea(
    value: String? = null,
    attrs: (TextAreaAttrsScope.() -> Unit)? = null,
    errorText: String? = null,
    onValueChange: (SyntheticInputEvent<String, HTMLTextAreaElement>) -> Unit,
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
        Style(inputStyle)
        TextArea(
            value = value,
            attrs = {
                onInput { event -> onValueChange(event) }
                classes(inputStyle.inputFieldStyle)
                attrs?.invoke(this)
            }
        )

        errorText?.let {
            C.span(it)
        }
    }
}