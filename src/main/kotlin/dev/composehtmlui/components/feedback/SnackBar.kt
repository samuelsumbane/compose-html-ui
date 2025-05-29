package dev.composehtmlui.components.feedback

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import dev.composehtmlui.C
import dev.composehtmlui.components.CTexts.p
import dev.composehtmlui.components.buttons.primaryButton
import dev.composehtmlui.layout.div
import kotlinx.coroutines.delay

@Composable
fun C.snackbar(
    message: String,
    duration: Long = 3000L,
    position: SnackbarPosition = SnackbarPosition.BOTTOMRIGHT,
    onDismiss: () -> Unit = {},
    actionLabel: String? = null,
    onActionClick: (() -> Unit)? = null,
) {
    val coroutineScope = rememberCoroutineScope()
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

    C.div(
        style = {
            property("position", "fixed")
            property("z-index", "1000")
            property("background", "#323232") // cor escura típica
            property("color", "white")
            property("padding", "12px 24px")
            property("border-radius", "4px")
            property("box-shadow", "0px 2px 6px rgba(0, 0, 0, 0.3)")
            property("display", "flex")
            property("align-items", "center")
            property("gap", "16px")
            property("max-width", "300px")
            property("font-size", "14px")

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
    ) {
        C.p(message)

        if (actionLabel != null && onActionClick != null) {
            C.primaryButton(actionLabel, style = {
                property("background", "transparent")
                property("border", "none")
                property("color", "#BB86FC")
                property("cursor", "pointer")
                property("font-weight", "bold")
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
