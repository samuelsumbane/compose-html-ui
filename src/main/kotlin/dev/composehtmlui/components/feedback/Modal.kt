package dev.composehtmlui.components.feedback

import androidx.compose.runtime.Composable
import dev.composehtmlui.C
import dev.composehtmlui.core.tokens.BorderRadius
import dev.composehtmlui.core.tokens.Spacing
import dev.composehtmlui.layout.div
import dev.composehtmlui.style.AppColors
import dev.composehtmlui.style.LocalTheme
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.maxHeight
import org.jetbrains.compose.web.css.maxWidth
import org.jetbrains.compose.web.css.minWidth
import org.jetbrains.compose.web.css.overflow
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.css.vw
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Aside
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLFormElement

@Composable
fun C.modal(
    position: HorizontalAlignment = HorizontalAlignment.CENTER,
    attrs: (AttrsScope<HTMLElement>.() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val theme = LocalTheme.current
    C.div(
        attrs = {
            attr("role", "dialog")
            attr("aria-modal", "true")

            style {
                position(Position.Fixed)
                property("top", "0")
                property("left", "0")
                width(100.percent)
                height(100.percent)
                backgroundColor(rgba(0, 0, 0, 0.5))
                display(DisplayStyle.Flex)
                alignItems(AlignItems.Center)
                property("justify-content", position.stringValue)
                property("z-index", "999")
                overflow("hidden")
                property("transition", "0.8s")
            }
        },
    ) {
        // Modal box
        Aside(
            attrs = {
                // Prevents the internal click from closing the modal ---->>
                onClick { it.stopPropagation() }
                style {
                    backgroundColor(theme.surface)
                    padding(Spacing.MD)
                    property("margin", Spacing.SM)
                    property("border-radius", BorderRadius.XL)
                    minWidth(300.px)
                    maxWidth(90.percent)
                    height(95.percent)
                    property("width", "clamp(200px, 25vw, 500px)")
                    property("overflow-y", "auto")
                    property("box-shadow", "0 0 20px rgba(0,0,0,0.25)")
                }
                attrs?.invoke(this)
            },
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