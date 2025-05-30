package dev.composehtmlui.components.navigation


import androidx.compose.runtime.Composable
import dev.composehtmlui.C
import dev.composehtmlui.layout.div
import dev.composehtmlui.style.AppColors
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Nav
import org.jetbrains.compose.web.dom.Text


@Composable
fun C.topBar(
    title: String,
    content: @Composable () -> Unit = {}
) {
    Nav (
        attrs = {
            style {
                width(100.percent)
                display(DisplayStyle.Flex)
                justifyContent(JustifyContent.SpaceBetween)
                alignItems(AlignItems.Center)
                padding(1.em)
                backgroundColor(AppColors.orange600)
                color(Color.white)
                fontSize(1.25.em)
                fontWeight("bold")
                property("box-shadow", "0 2px 4px rgba(0,0,0,.2)")
            }

        }
    ) {

        C.div {
            Text(title)
        }

        C.div(
            style = {
                display(DisplayStyle.Flex)
                gap(1.em)
                alignItems(AlignItems.Center)
            }
        ) {
            content()
        }
    }
}
