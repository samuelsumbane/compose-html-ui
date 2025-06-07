package dev.composehtmlui.components.feedback
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.composehtmlui.C
import dev.composehtmlui.components.cTexts.h2
import dev.composehtmlui.components.cTexts.p
import dev.composehtmlui.style.LocalTheme
import dev.composehtmlui.components.buttons.primaryButton
import dev.composehtmlui.components.buttons.warningButton
import dev.composehtmlui.core.tokens.BorderRadius
import dev.composehtmlui.layout.column
import dev.composehtmlui.layout.div
import dev.composehtmlui.layout.row
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
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
    title: String = "",
    message: String = "",
    position: AlertPosition = AlertPosition.CENTER,
    onAcceptClick: () -> Unit = {}
) {
    alert(title, message, position) {
        onAcceptClick()
    }
}

@Composable
fun C.timerAlert(
    title: String = "",
    message: String = "",
    position: AlertPosition = AlertPosition.TOPRIGHT,
    timer: Long = 2000L, // 2 seconds  ---->>
) { alert(title, message, position, timer) }

@Composable
fun C.dangerAlert(
    title: String = "",
    message: String = "",
    position: AlertPosition = AlertPosition.CENTER,
    dangerMode: Boolean = true,
    onAcceptClick: () -> Unit = {}
) {
    alert(title, message, position, 0L, dangerMode) {
        onAcceptClick()
    }
}


@Composable
fun alert(
    title: String = "",
    message: String = "",
    position: AlertPosition = AlertPosition.CENTER,
    timer: Long = 0L,
    dangerMode: Boolean = false,
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
                property("z-index", "1000")
                property("background", "rgba(0, 0, 0, 0.8)")
                property("display", "${ if (internalVisibilityState) "flex" else "none"} ")
            }
        }
    ) {
        C.column(
            attrs = {
                style {
                    property("width", "min(90.vw, 400.px")
                    height(220.px)
                    backgroundColor(theme.background)
                    property("border-radius", BorderRadius.XL)
                    property("position", "relative")
                    property("display", "flex")
                    justifyContent(JustifyContent.SpaceBetween)

                    fun leftRulePosition() = property("left", "calc(100% - 410px)")
                    when(position) {
                        AlertPosition.TOPRIGHT -> {
                            property("top", "10px")
                            leftRulePosition()
                        }
                        AlertPosition.BOTTOMRIGHT -> {
                            property("bottom", "10px")
                            leftRulePosition()
                        }
                        else -> property("margin", "auto")
                    }
                }

            }
        ) {
            C.h2(title, attrs = {
                style {
                    property("margin", "0 auto")
                }
            })

            C.p(message, attrs = {
                style {
                    property("margin", "0 auto")
                }
            })

            C.row(
                attrs = {
                    style {
                        width(100.percent)
                    }
                }
            ) {
                if (dangerMode) {
                    C.primaryButton("Cancel") {
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