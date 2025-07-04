package dev.composehtmlui.components.generic

import androidx.compose.runtime.Composable
import androidx.compose.web.attributes.SelectAttrsScope
import dev.composehtmlui.C
import dev.composehtmlui.layout.column
import dev.composehtmlui.style.AppColors
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.attributes.EventsListenerScope
import org.jetbrains.compose.web.attributes.onSubmit
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Form
import org.w3c.dom.HTMLFormElement

@Composable
fun C.form(
    header: (@Composable () -> Unit)? = null,
    main: (@Composable () -> Unit)? = null,
    footer: (@Composable () -> Unit)? = null,
    attrs: (AttrsScope<HTMLFormElement>.() -> Unit)? = null,
    onSubmit: () -> Unit,
) {
    Form(
        attrs = {
            onSubmit {
                it.preventDefault()
                onSubmit()
            }
            style {
                width(100.percent)
                height(100.percent)
            }
            attrs?.invoke(this)
        }
    ) {
        C.column(
            attrs = {
                style {
                    width(100.percent)
                    height(100.percent)
                    justifyContent(JustifyContent.SpaceBetween)
                }
            }
        ) {
            header?.invoke()
            main?.invoke()
            footer?.invoke()
        }
    }
}