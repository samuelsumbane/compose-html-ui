package dev.composehtmlui.components.feedback
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.composehtmlui.C
import dev.composehtmlui.components.buttons.outlineButton
import dev.composehtmlui.components.cTexts.h2
import dev.composehtmlui.components.cTexts.p
import dev.composehtmlui.style.LocalTheme
import dev.composehtmlui.components.buttons.primaryButton
import dev.composehtmlui.components.buttons.warningButton
import dev.composehtmlui.core.tokens.BorderRadius
import dev.composehtmlui.core.tokens.Spacing
import dev.composehtmlui.layout.column
import dev.composehtmlui.layout.div
import dev.composehtmlui.layout.row
import dev.composehtmlui.style.AppColors
import dev.composehtmlui.style.icons.Icon
import dev.composehtmlui.style.icons.iconSvg
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width


/**
 * Alert and timer composable has timer functionality to auto-close.
 * and this uses coroutines. If you wish to use timer consider install
 * kotlinx-coroutines plugin if you didn't yet.
 *
 * Another way is to implement your own timer code(auto-close) or removing it.
 */

@Composable
fun C.okayAlert(
    icon: AlertIcon = AlertIcon.INFO,
    title: String = "",
    message: String = "",
    visible: Boolean = false,
    position: AlertPosition = AlertPosition.CENTER,
    onAcceptClick: () -> Unit = {}
) {
    if (visible) {
        alert(icon, title, message, position) {
            onAcceptClick()
        }
    }
}

@Composable
fun C.timerAlert(
    icon: AlertIcon = AlertIcon.SUCCESS,
    title: String = "",
    message: String = "",
    visible: Boolean = false,
    position: AlertPosition = AlertPosition.TOPRIGHT,
    timer: Long = 3000L, // 3 seconds  ---->>
) {
    if (visible) {
        alert(icon, title, message, position, timer)
    }
}

@Composable
fun C.dangerAlert(
    icon: AlertIcon = AlertIcon.WARNING,
    title: String = "",
    message: String = "",
    visible: Boolean = false,
    position: AlertPosition = AlertPosition.TOPRIGHT,
    onCancelClick: (() -> Unit)? = null,
    onAcceptClick: () -> Unit = {}
) {
    if (visible) {
        alert(icon, title, message, position, 0L,
            dangerMode = true, onCancelClick = { onCancelClick?.invoke() }) {
            onAcceptClick()
        }
    }
}


@Composable
fun alert(
    icon: AlertIcon,
    title: String = "",
    message: String = "",
    position: AlertPosition = AlertPosition.CENTER,
    timer: Long = 0L,
    dangerMode: Boolean = false,
    onCancelClick: (() -> Unit)? = null,
    onAcceptClick: () -> Unit = {}
) {
    val theme = LocalTheme.current
    val coroutineScope = rememberCoroutineScope()
    var internalVisibilityState by remember { mutableStateOf(true) }

    C.div(
        attrs = {
            style {
                property("position", "absolute")
                property("left", "0")
                property("top", "0")
                property("width", "100vw")
                property("height", "100vh")
                property("z-index", "999")
                property("background-color", "rgba(0, 0, 0, 0.6)")
                if (position == AlertPosition.CENTER) {
                    display(DisplayStyle.Flex)
                    justifyContent(JustifyContent.Center)
                    alignItems(AlignItems.Center)
                }
//                property("background", "red")
                property("display", "${ if (internalVisibilityState) "flex" else "none"} ")
            }
        }
    ) {
        C.column(
            attrs = {
                style {
                    property("width", "clamp(350px, 30vw, 500px")
//                    height(300.px)
                    padding(Spacing.MD)
                    backgroundColor(theme.background)
                    property("border-radius", BorderRadius.XL)
                    property("position", "absolute")
                    property("display", "flex")
                    property("margin", "auto")
                    justifyContent(JustifyContent.SpaceBetween)
                    alignItems(AlignItems.Center)

                    fun rightRulePosition() = property("right", "10px")
                    when (position) {
                        AlertPosition.TOPRIGHT -> {
                            property("top", "10px")
                            rightRulePosition()
                        }
                        AlertPosition.BOTTOMRIGHT -> {
                            property("bottom", "10px")
                            rightRulePosition()
                        }
                        else -> {}
                    }
                }
            }
        ) {

            C.row(attrs = { classes("centerHorizontally") }) {
                C.iconSvg(
                    icon = when (icon) {
                        AlertIcon.ERROR -> Icon.DASH
                        AlertIcon.INFO -> Icon.INFO
                        AlertIcon.SUCCESS -> Icon.CHECK_CIRCLE
                        AlertIcon.WARNING -> Icon.PAPERCLIP
                    }, 40.px, fillColor = when (icon) {
                        AlertIcon.ERROR -> AppColors.red500
                        AlertIcon.INFO -> AppColors.blue300
                        AlertIcon.SUCCESS -> AppColors.green500
                        AlertIcon.WARNING -> AppColors.orange500
                    }
                )
            }

            if (title.isNotBlank()) { C.h2(title) }
            if (message.isNotBlank()) { C.p(message) }

            C.row(
                attrs = {
                    style {
                        width(100.percent)
                        justifyContent(JustifyContent.SpaceAround)
                    }
                }
            ) {
                if (dangerMode) {
                    C.outlineButton("Cancel") {
                        onCancelClick?.invoke()
                        internalVisibilityState = !internalVisibilityState
                    }
                    C.warningButton("OK") {
                        onAcceptClick()
                        internalVisibilityState = !internalVisibilityState
                    }
                } else {
                    if (timer != 0L) {
                        coroutineScope.launch {
                            delay(timer)
                            internalVisibilityState = !internalVisibilityState
                        }
                    } else {
                        C.primaryButton("OK") {
                            onAcceptClick()
                            internalVisibilityState = !internalVisibilityState
                        }
                    }
                }
            }
        }
    }
}

enum class AlertPosition() {
    CENTER(),
    TOPRIGHT(),
    BOTTOMRIGHT()
}

//enum class AlertIcon(val iconName: String) {
enum class AlertIcon() {
//    INFO("info"),
//    SUCCESS("success"),
//    ERROR("error"),
//    WARNING("warning")
    INFO(),
    SUCCESS(),
    ERROR(),
    WARNING()
}