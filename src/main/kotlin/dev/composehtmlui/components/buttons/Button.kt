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
import dev.composehtmlui.core.tokens.darken
import dev.composehtmlui.core.tokens.withAlpha
import dev.composehtmlui.style.LocalTheme
import dev.composehtmlui.layout.div
import dev.composehtmlui.style.AppColors
import dev.composehtmlui.style.Theme
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.cursor
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.maxHeight
import org.jetbrains.compose.web.css.minHeight
import org.jetbrains.compose.web.css.minWidth
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.textDecoration
import org.jetbrains.compose.web.css.width

@Composable
fun C.primaryButton(
    text: String,
    attrs: (AttrsScope<HTMLButtonElement>.() -> Unit)? = null,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    icon: (@Composable (() -> Unit))? = null,
    showOnly: ShowButtonContent = ShowButtonContent.TEXT,
    onClick: () -> Unit = {}
) {
    basicButton(
        enabled = enabled,
        isLoading,
        attrs = attrs,
        buttonVariantType = ButtonVariantType.PRIMARY,
        onClick = onClick
    ) {
        buttonContentContainer(text, isLoading, showOnly, icon)
    }
}

@Composable
fun C.warningButton(
    text: String,
    attrs: (AttrsScope<HTMLButtonElement>.() -> Unit)? = null,
    enabled: Boolean = true,
    loading: Boolean = false,
    icon: (@Composable (() -> Unit))? = null,
    showOnly: ShowButtonContent = ShowButtonContent.TEXT,
    onClick: () -> Unit = {}
) {
    basicButton(
        enabled = enabled,
        attrs = attrs,
        buttonVariantType = ButtonVariantType.WARNING,
        onClick = onClick
    ) {
        buttonContentContainer(text, loading, showOnly, icon)
    }
}

@Composable
fun C.sidebarButton(
    text: String,
    active: Boolean = false,
    attrs: (AttrsScope<HTMLButtonElement>.() -> Unit)? = null,
    icon: @Composable () -> Unit = {},
    showOnly: ShowButtonContent = ShowButtonContent.BOTH,
    onClick: () -> Unit = {}
) {
    val theme = LocalTheme.current
    if (active) Style(ButtonStylesheet(theme))

    basicButton(
        enabled = true,
        isLoading = false,
        attrs = {
            if (active) classes(ButtonStylesheet(theme).activeSideBarButton)
            style { width(100.percent) }
            attrs?.invoke(this)
        },
        ButtonVariantType.SIDEBAR,
        onClick = { onClick() }
    ) {
        buttonContentContainer(text, isLoading = false, showOnly, icon)
    }
}

@Composable
fun C.outlineButton(
    text: String,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    attrs: (AttrsScope<HTMLButtonElement>.() -> Unit)? = null,
    icon: (@Composable (() -> Unit))? = null,
    showOnly: ShowButtonContent = ShowButtonContent.TEXT,
    onClick: () -> Unit,
) {
    basicButton(
        enabled,
        isLoading,
        attrs = attrs,
        ButtonVariantType.OUTLINE,
        onClick = { onClick() }
    ) {
        buttonContentContainer(text, isLoading, showOnly, icon)
    }
}

@Composable
fun C.iconButton(
    enabled: Boolean = true,
    isLoading: Boolean = false,
    attrs: (AttrsScope<HTMLButtonElement>.() -> Unit)? = null,
    icon: (@Composable (() -> Unit))? = null,
    onClick: () -> Unit,
) {
    basicButton(
        enabled,
        isLoading,
        attrs = {
            style {
                width(34.px)
                minHeight(34.px)
                maxHeight(34.px)
                borderRadius(50.percent)
            }
            attrs?.invoke(this)
        },
        ButtonVariantType.ICON,
        onClick = { onClick() }
    ) {
        icon?.invoke()
    }
}

@Composable
fun buttonContentContainer(
    text: String? = null,
    isLoading: Boolean,
    showOnly: ShowButtonContent = ShowButtonContent.TEXT,
    icon: (@Composable (() -> Unit))? = null,
) {
    C.div(
        attrs = {
            style {
                property("display", "flex")
                property("align-items", "center")
                property("justify-content", "space-around")
                minWidth(if (showOnly == ShowButtonContent.BOTH) 110.px else 30.px)
                property("margin", "auto")
            }
        }
    ) {
        if (isLoading) {
            Text("Loading...")
        } else {
            when (showOnly) {
                ShowButtonContent.ICON -> icon?.invoke()
                ShowButtonContent.TEXT -> {
                    text?.let { Text(text) }
                }
                ShowButtonContent.BOTH -> {
                    icon?.invoke()
                    text?.let { Text(text) }
                }
            }
        }
    }
}

@Composable
fun C.textButton(
    text: String,
    loading: Boolean = false,
    loadingText: String = "Loading",
    enabled: Boolean = true,
    attrs: (AttrsScope<HTMLButtonElement>.() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    if (loading) {
        Text("$loadingText...")
    } else {
        basicButton(
            enabled,
            isLoading = loading,
            attrs = attrs,
            ButtonVariantType.TEXT,
            onClick = { onClick?.invoke() }
        ) {
            Text(text)
        }
    }
}

fun StyleScope.baseButtonStyle(theme: Theme) {
    backgroundColor(theme.primary)
    color(theme.buttonText)
    property("border", "none")
    padding(8.px, 8.px)
    borderRadius(6.px)
    fontWeight("bold")
}

class ButtonStylesheet(theme: Theme) : StyleSheet() {
    val primary by style {
        baseButtonStyle(theme)
        self + hover style {
            backgroundColor(theme.primary.darken(30))
        }
    }

    val outline by style {
        baseButtonStyle(theme)
        backgroundColor(Color.transparent)
        color(theme.onPrimary)
        border(1.px, LineStyle.Solid, theme.primary)
        self + hover style {
            backgroundColor(theme.primary.withAlpha(0.4))
        }
    }

    val icon by style {
        backgroundColor(Color.transparent)
        property("border", "none")
        self + hover style {
            backgroundColor(theme.primary.withAlpha(0.2))
        }
    }

    val text by style {
        backgroundColor(Color.transparent)
        color(theme.link)
        padding(8.px, 16.px)
        borderRadius(4.px)
        fontWeight("normal")
        cursor("pointer")
        property("border", "none")

        self + hover style {
            textDecoration("underline")
        }
    }

    val sidebar by style {
        backgroundColor(Color.transparent)
        color(theme.sidebarContent)
        padding(8.px, 8.px)
        borderRadius(4.px)
        fontWeight("normal")
        cursor("pointer")
        property("border", "none")

        self + hover style {
            color(AppColors.white)
            backgroundColor(theme.primary.darken(30))
        }
    }

    val activeSideBarButton by style {
        property("border-left", "6px solid ${theme.primary}")
    }

    val warning by style  {
        baseButtonStyle(theme)
        backgroundColor(theme.warning)
        color(theme.onPrimary)
        self + hover style {
            color(AppColors.white)
            backgroundColor(theme.primary.darken(30))
        }
    }

}

@Composable
fun basicButton(
    enabled: Boolean = true,
    isLoading: Boolean = false,
    attrs: (AttrsScope<HTMLButtonElement>.() -> Unit)? = null,
    buttonVariantType: ButtonVariantType,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val theme = LocalTheme.current
    val styles = ButtonStylesheet(theme)

    val buttonClass = when (buttonVariantType) {
        ButtonVariantType.PRIMARY -> styles.primary
        ButtonVariantType.OUTLINE -> styles.outline
        ButtonVariantType.TEXT -> styles.text
        ButtonVariantType.ICON -> styles.icon
        ButtonVariantType.SIDEBAR -> styles.sidebar
        ButtonVariantType.WARNING -> styles.warning
    }
    Style(ButtonStylesheet(theme))
    Button(
        attrs = {
            type(ButtonType.Button)
            onClick { onClick() }
            if (!enabled) disabled()
            if (isLoading) disabled()
            classes(buttonClass)
            attrs?.invoke(this)
        }
    ) {
        content()
    }
}


enum class ShowButtonContent {
    ICON, TEXT, BOTH
}

enum class ButtonVariantType {
    PRIMARY, OUTLINE, TEXT, ICON, SIDEBAR, WARNING
}
