package dev.composehtmlui.components.buttons

import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.attributes.ButtonType
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.attributes.type
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLButtonElement
import androidx.compose.runtime.Composable
import dev.composehtmlui.C
import dev.composehtmlui.core.tokens.BorderRadius
import dev.composehtmlui.core.tokens.FontSize
import dev.composehtmlui.core.tokens.FontWeight
import dev.composehtmlui.core.tokens.Spacing
import dev.composehtmlui.style.LocalTheme
import dev.composehtmlui.layout.div
import dev.composehtmlui.style.Theme
import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.alignSelf
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.percent

/**
 * This file contains 4 composable buttons
 * They are primaryButton, outlineButton, textButton and sidebarButton
 */

@Composable
fun C.primaryButton(
    text: String,
    width: CSSNumeric? = null,
    height: CSSNumeric? = null,
    attrs: AttrsScope<HTMLButtonElement>.() -> Unit = {},
    style: (StyleScope.() -> Unit)? = null,
    enabled: Boolean = true,
    loading: Boolean = false,
    icon: (@Composable (() -> Unit))? = null,
    showOnly: ShowButtonContent = ShowButtonContent.BOTH,
    onClick: () -> Unit = {}
) {
    basicButton(
        width, height,
        enabled, style = { style?.invoke(this) },
        attrs = { attrs() },
        ButtonVariantType.PRIMARY,
        onClick = { onClick() }
    ) {
        buttonContentContainer(text, loading, showOnly, icon)
    }
}

@Composable
fun C.sidebarButton(
    text: String,
    height: CSSNumeric? = null,
    icon: @Composable () -> Unit = {},
    showOnly: ShowButtonContent = ShowButtonContent.BOTH,
    onClick: () -> Unit = {}
) {
    basicButton(
        width = 100.percent,
        height = height,
        enabled = true,
        style = null,
        attrs = null,
        ButtonVariantType.SIDEBAR,
        onClick = { onClick() }
    ) {
        buttonContentContainer(text, loading = false, showOnly, icon)
    }
}

@Composable
fun C.outlineButton(
    text: String,
    enabled: Boolean = true,
    loading: Boolean = false,
    width: CSSNumeric? = null,
    height: CSSNumeric? = null,
    attrs: AttrsScope<HTMLButtonElement>.() -> Unit = {},
    style: (StyleScope.() -> Unit)? = null,
    icon: (@Composable (() -> Unit))? = null,
    showOnly: ShowButtonContent = ShowButtonContent.BOTH,
    onClick: () -> Unit,
) {
    basicButton(
        width = width,
        height = height,
        enabled,
        style = { style?.invoke(this) },
        attrs = attrs,
        ButtonVariantType.OUTLINE,
        onClick = { onClick() }
    ) {
        buttonContentContainer(text, loading, showOnly, icon)
    }
}

@Composable
fun buttonContentContainer(
    text: String,
    loading: Boolean,
    showOnly: ShowButtonContent = ShowButtonContent.BOTH,
    icon: (@Composable (() -> Unit))? = null,
) {
    C.div(style = {
        property("display", "flex")
        property("align-items", "center")
        property("justify-content", "space-around")
    }) {
        if (loading) {
            Text("Loading...")
        } else {
            when (showOnly) {
                ShowButtonContent.ICON -> icon?.invoke()
                ShowButtonContent.TEXT -> Text(text)
                ShowButtonContent.BOTH -> {
                    icon?.invoke()
                    Text(text)
                }
            }
        }
    }
}

@Composable
fun C.textButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    basicButton(
        width = null, height = null, enabled,
        style = null, attrs = null,
        ButtonVariantType.TEXT,
        onClick = { onClick() }
    ) {
        Text(text)
    }
}

@Composable
fun basicButton(
    width: CSSNumeric? = null,
    height: CSSNumeric? = null,
    enabled: Boolean = true,
    style: (StyleScope.(theme: Theme) -> Unit)? = null,
    attrs: (AttrsScope<HTMLButtonElement>.() -> Unit)? = null,
    buttonVariantType: ButtonVariantType,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val theme = LocalTheme.current
    Button(
        attrs = {
            type(ButtonType.Button)
            onClick { onClick() }
            if (!enabled) disabled()
            style {
                color(theme.primaryColor)
                property("border", "none")
                property("border-radius", BorderRadius.MD)
                property("padding", "${Spacing.SM} ${Spacing.MD}")
                property("cursor", if (enabled) "pointer" else "not-allowed")
                property("font-size", FontSize.BODY)
                fontWeight(FontWeight.BOLD)

                if (width != null) property("width", width)
                if (height != null) property("height", height)

                when (buttonVariantType) {
                    ButtonVariantType.PRIMARY -> {
                        property("background-color", if (enabled) theme.primaryColor else theme.disabledColor)
                        property("color", "white")
                        fontWeight("bold")
                    }
                    ButtonVariantType.OUTLINE -> {
                        property("background-color", "transparent")
                        color(theme.primaryColor)
                        property("border", "1px solid ${theme.primaryColor}")
                        fontWeight("bold")
                    }
                    ButtonVariantType.TEXT -> {
                        property("background-color", "transparent")
                        color(theme.linkColor)
                        property("font-weight", "normal")
                    }
                    ButtonVariantType.SIDEBAR -> {
                        property("background-color", "transparent")
                        color(theme.sidebarColor)
                    }
                }
                style?.invoke(this, theme)
            }
            if (attrs != null) { attrs() }
        }
    ) { content() }
}

enum class ShowButtonContent {
    ICON, TEXT, BOTH
}

enum class ButtonVariantType {
    PRIMARY, OUTLINE, TEXT, SIDEBAR
}
