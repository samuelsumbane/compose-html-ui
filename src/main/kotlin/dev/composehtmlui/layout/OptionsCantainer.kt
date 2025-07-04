package dev.composehtmlui.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.composehtmlui.C
import dev.composehtmlui.components.cTexts.p
import dev.composehtmlui.core.tokens.BorderRadius
import dev.composehtmlui.core.tokens.Spacing
import dev.composehtmlui.style.AppColors
import dev.composehtmlui.style.LocalTheme
import dev.composehtmlui.style.Theme
import dev.composehtmlui.style.icons.Icon
import dev.composehtmlui.style.icons.iconSvg
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.cursor
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLDivElement


@Composable
fun C.optionsContainer(
    attrs: (AttrsScope<HTMLDivElement>.() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val theme = LocalTheme.current
    val optionsContainerStyle = remember(theme) { OptionsContainerStylesheet(theme) }
    Style(optionsContainerStyle)
    C.div(
        attrs = {
            attrs?.invoke(this)
            classes(optionsContainerStyle.optionsContainer)
        }
    ) {
        content()
        // optionItem list
    }
}

@Composable
fun C.optionItem(
    text: String,
    icon: Icon? = null,
    todo: (() -> Unit)? = null
) {
    val theme = LocalTheme.current
    val optionsContainerStyle = remember(theme) { OptionsContainerStylesheet(theme) }
    Style(optionsContainerStyle)
    C.div(
        attrs = {
            classes(optionsContainerStyle.optionItem)
            onClick { todo?.invoke() }
        }
    ) {
        C.row(
            attrs = {
                style {
                    height(100.percent)
                    alignItems(AlignItems.Center)
                }
            }
        ) {
            P { Text(text) }
            icon?.let { C.iconSvg(icon = it, fillColor = theme.onSurface) }
        }
    }
}

class OptionsContainerStylesheet(theme: Theme) : StyleSheet() {
    val optionsContainer by style {
        width(160.px)
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        backgroundColor(theme.surface)
        borderRadius(BorderRadius.MD)
        padding(Spacing.SM)
        gap(6.px)
        property("z-index", "999")
        property("box-shadow", "0 0 7px rgba(0, 0, 0, .3)")
    }

    val optionItem by style {
        width(100.percent)
        height(30.px)
        backgroundColor(Color.transparent)
        color(theme.onSurface)
        borderRadius(BorderRadius.MD)
        property("cursor", "pointer")
        fontSize(13.px)
        self + hover style {
            backgroundColor(theme.primary)
            color(Color.white)
        }
    }

}