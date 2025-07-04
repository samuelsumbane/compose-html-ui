package dev.composehtmlui.components.inputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.web.attributes.SelectAttrsScope
import dev.composehtmlui.C
import dev.composehtmlui.components.cTexts.label
import dev.composehtmlui.components.cTexts.p
import dev.composehtmlui.components.cTexts.span
import dev.composehtmlui.style.LocalTheme
import dev.composehtmlui.core.tokens.BorderRadius
import dev.composehtmlui.core.tokens.FontSize
import dev.composehtmlui.core.tokens.Spacing
import dev.composehtmlui.layout.column
import dev.composehtmlui.style.AppColors
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.ElementScope
import org.jetbrains.compose.web.dom.Option
import org.jetbrains.compose.web.dom.Select
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLSelectElement

@Composable
fun C.select(
    onChange: (String?) -> Unit,
    attrs: (SelectAttrsScope.() -> Unit)? = null,
    containerAttrs: (AttrsScope<HTMLDivElement>.() -> Unit)? = null,
    labelText: String? = null,
    errorText: String? = null,
    enabled: Boolean = true,
    content: @Composable (ElementScope<HTMLSelectElement>.() -> Unit)? = null
) {
    val theme = LocalTheme.current
    val inputStyle = remember(theme) { InputFieldStyle(theme) }
    Style(inputStyle)
    C.column(
        attrs = {
            classes(inputStyle.inputColumnContainer)
            style {
//                alignItems(AlignItems.FlexStart)
                gap(3.px)
            }
            containerAttrs?.invoke(this)
        }
    ) {
        labelText?.let { C.label(it) }

        Select(
            attrs = {
                if (!enabled) disabled()
                style {
                    backgroundColor(theme.inputBackground)
                    color(theme.inputText)
                    padding(Spacing.SM)
                    fontSize(FontSize.BODY)
                    property("border-radius", BorderRadius.MD)
                    property("border", "none")
                }
                onChange { onChange(it.value) }
                attrs?.invoke(this)
            }
        ) {
            if (content != null) content()
        }

        errorText?.let { C.span(it) }
    }
}
