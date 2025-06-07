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
import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.cursor
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.minWidth
import org.jetbrains.compose.web.css.overflow
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.textDecoration
import org.jetbrains.compose.web.css.width

@Composable
fun C.primaryButton(
    text: String,
    attrs: AttrsScope<HTMLButtonElement>.() -> Unit = {},
    enabled: Boolean = true,
    loading: Boolean = false,
    icon: (@Composable (() -> Unit))? = null,
    showOnly: ShowButtonContent = ShowButtonContent.BOTH,
    onClick: () -> Unit = {}
) {
    basicButton(
        enabled = enabled,
        attrs = attrs,
        buttonVariantType = ButtonVariantType.PRIMARY,
        onClick = onClick
    ) {
        buttonContentContainer(text, loading, showOnly, icon)
    }
}

@Composable
fun C.warningButton(
    text: String,
    attrs: AttrsScope<HTMLButtonElement>.() -> Unit = {},
    enabled: Boolean = true,
    loading: Boolean = false,
    icon: (@Composable (() -> Unit))? = null,
    showOnly: ShowButtonContent = ShowButtonContent.BOTH,
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
    icon: @Composable () -> Unit = {},
    showOnly: ShowButtonContent = ShowButtonContent.BOTH,
    onClick: () -> Unit = {}
) {
    basicButton(
        enabled = true,
        attrs = {
            style {
                width(100.percent)
            }
        },
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
    attrs: AttrsScope<HTMLButtonElement>.() -> Unit = {},
    icon: (@Composable (() -> Unit))? = null,
    showOnly: ShowButtonContent = ShowButtonContent.BOTH,
    onClick: () -> Unit,
) {
    basicButton(
        enabled,
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
    C.div(
        attrs = {
            style {
                property("display", "flex")
                property("align-items", "center")
                property("justify-content", "space-around")
                height(30.px)
                minWidth(if (showOnly == ShowButtonContent.BOTH) 120.px else 30.px)
            }
        }
    ) {
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
        enabled,
        attrs = null,
        ButtonVariantType.TEXT,
        onClick = { onClick() }
    ) {
        Text(text)
    }
}

fun StyleScope.baseButtonStyle(theme: Theme) {
    backgroundColor(theme.primaryColor)
    color(theme.primaryTextColor)
    property("border", "none")
    padding(4.px, 4.px)
    borderRadius(6.px)
    fontWeight("bold")
    cursor("pointer")
}

class ButtonStylesheet(theme: Theme) : StyleSheet() {
    val primary by style {
        baseButtonStyle(theme)
        self + hover style {
            color(AppColors.white)
            backgroundColor(theme.primaryColor.darken(30))
        }
    }

    val outline by style {
        baseButtonStyle(theme)
        backgroundColor(Color.transparent)
        border(1.px, LineStyle.Solid, theme.primaryColor)
        self + hover style {
            backgroundColor(theme.primaryColor.withAlpha(0.4))
        }
    }

    val text by style {
        backgroundColor(Color.transparent)
        color(theme.linkColor)
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
        color(theme.sidebarColor)
        padding(8.px, 8.px)
        borderRadius(4.px)
        fontWeight("normal")
        cursor("pointer")
        property("border", "none")

        self + hover style {
            color(AppColors.white)
            backgroundColor(theme.primaryColor.darken(30))
        }
    }

    val warning by style  {
        baseButtonStyle(theme)
        backgroundColor(theme.warningColor)
        color(theme.primaryTextColor)
        self + hover style {
            color(AppColors.white)
            backgroundColor(theme.primaryColor.darken(30))
        }
    }

}

@Composable
fun basicButton(
    enabled: Boolean = true,
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
        ButtonVariantType.SIDEBAR -> styles.sidebar
        ButtonVariantType.WARNING -> styles.warning
    }
    Style(ButtonStylesheet(theme))
    Button(
        attrs = {
            type(ButtonType.Button)
            onClick { onClick() }
            if (!enabled) disabled()
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
    PRIMARY, OUTLINE, TEXT, SIDEBAR, WARNING
}
