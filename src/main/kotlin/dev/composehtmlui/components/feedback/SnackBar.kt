package dev.composehtmlui.components.feedback

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.composehtmlui.C
import dev.composehtmlui.components.cTexts.p
import dev.composehtmlui.components.buttons.primaryButton
import dev.composehtmlui.core.tokens.Spacing
import dev.composehtmlui.layout.row
import dev.composehtmlui.style.AppColors
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.maxWidth

@Composable
fun C.snackbar(
    message: String,
    duration: Long = 3000L,
    position: SnackbarPosition = SnackbarPosition.BOTTOMRIGHT,
    onDismiss: () -> Unit = {},
    actionLabel: String? = null,
    onActionClick: (() -> Unit)? = null,
) {
    var internalVisibilityState by remember { mutableStateOf(true) }

    LaunchedEffect(internalVisibilityState) {
        if (internalVisibilityState) {
            internalVisibilityState = true
            delay(duration)
            internalVisibilityState = false
            onDismiss()
        }
    }

    if (!internalVisibilityState) return

    C.row(
        attrs = {
            style {
                alignItems(AlignItems.Center)
                property("position", "fixed")
                property("z-index", "1000")
                property("background", "#323232")
                color(AppColors.white)
                property("padding", "12px 24px")
                property("border-radius", "4px")
                property("box-shadow", "0px 2px 6px rgba(0, 0, 0, 0.3)")
                gap(Spacing.MD)
                maxWidth("300px")
                fontSize(Spacing.MD)

                when (position) {
                    SnackbarPosition.BOTTOMRIGHT -> {
                        property("bottom", "24px")
                        property("right", "24px")
                    }
                    SnackbarPosition.BOTTOMLEFT -> {
                        property("bottom", "24px")
                        property("left", "24px")
                    }
                    SnackbarPosition.TOPCENTER -> {
                        property("top", "24px")
                        property("left", "50%")
                        property("transform", "translateX(-50%)")
                    }
                }
            }
        }
    ) {
        C.p(message)

        if (actionLabel != null && onActionClick != null) {
            C.primaryButton(actionLabel, attrs = {
                style {
                    property("background", "transparent")
                    property("border", "none")
                    property("cursor", "pointer")
                    property("font-weight", "bold")
                }

            }) {
                onActionClick()
                internalVisibilityState = false
                onDismiss()
            }
        }
    }
}

enum class SnackbarPosition {
    BOTTOMRIGHT, BOTTOMLEFT, TOPCENTER
}
