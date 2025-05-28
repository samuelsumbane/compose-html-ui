package dev.components.feedback

import androidx.compose.runtime.Composable
import dev.components.C
import dev.components.layouts.div
import dev.composables.LocalTheme
import dev.tokkens.BorderRadius
import dev.tokkens.Spacing
import org.jetbrains.compose.web.css.StyleScope

@Composable
fun C.modal(
    onDismissRequest: () -> Unit,
    width: String = "clamp(200px, 30vw, 700px)",
    height: String = "clamp(200px, 20vw, 600px)",
    position: HorizontalAlignment = HorizontalAlignment.CENTER,
    style: (StyleScope.() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val theme = LocalTheme.current
    C.div(
        attrs = {
            attr("role", "dialog")
            attr("aria-modal", "true")
            onClick { onDismissRequest() } // fechar ao clicar fora
        },
        style = {
            property("position", "fixed")
            property("top", "0")
            property("left", "0")
            property("width", "100vw")
            property("height", "100vh")
            property("background-color", "rgba(0, 0, 0, 0.5)")
            property("display", "flex")
            property("align-items", "center")
            property("justify-content", position.stringValue)
            property("z-index", "999")
            property("overflow", "hidden")
        }
    ) {
        // Caixa do modal
        C.div(
            attrs = {
                // Prevents the internal click from closing the modal ---->>
                onClick { it.stopPropagation() }
            },
            style = {
                property("background-color", theme.backgroundColor)
                property("padding", Spacing.SM)
                property("margin", Spacing.SM)
                property("border-radius", BorderRadius.XL)
                property("min-width", "300px")
                property("max-width", "90vw")
                property("max-height", "90vh")
                property("width", width)
                property("height", height)
                property("overflow-y", "auto")
                property("box-shadow", "0 0 20px rgba(0,0,0,0.25)")

                if (style != null) style()
            }
        ) {
        content()
        }
    }
}


enum class HorizontalAlignment(val stringValue: String) {
    START("flex-start"),
    CENTER("center"),
    END("flex-end"),
}