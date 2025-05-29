package com.samuelsumbane.composehtmlui.layout

import androidx.compose.runtime.Composable
import com.samuelsumbane.composehtmlui.components.C
import com.samuelsumbane.composehtmlui.core.tokkens.Spacing
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement


@Composable
fun C.div(
    attrs: AttrsScope<HTMLDivElement>.() -> Unit = {},
    style: (StyleScope.() -> Unit)? = null,
    width: String? = "auto",
    height: String? = "auto",
    content: @Composable () -> Unit
) {
    Div(
        attrs = {
            attrs()
            style {
                if (width != null) property("width", width)
                if (height != null) property("height", height)
                if (style != null) { style() }
            }
        }
    ) { content() }
}

@Composable
fun C.row(
    justityContent: JustifyContent = JustifyContent.SPACEEVENLY,
    alignItems: AlignItems = AlignItems.FLEXSTART,
    width: String? = null,
    height: String? = null,
    withPadding: Boolean = true,
    attrs: (AttrsScope<HTMLDivElement>.() -> Unit)? = null,
    style: (StyleScope.() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    C.div(
        width = width,
        height = height,
        style = {
            property("display", "flex")
            if (withPadding) property("padding", Spacing.SM)
            property("justify-content", justityContent.stringValue)
            property("align-items", alignItems.stringValue)
            if (style != null) { style() }
        },
        attrs = { if (attrs != null) attrs() }
    ) { content() }
}

/**
 * Column extends from 'row' function and
 * adding 'flex-direction: colum' css rule
 */

@Composable
fun C.column(
    justifyContent: JustifyContent = JustifyContent.SPACEAROUND,
    width: String? = null,
    height: String? = null,
    attrs: (AttrsScope<HTMLDivElement>.() -> Unit)? = null,
    style: (StyleScope.() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    C.row(
        width = width,
        height = height,
        justityContent = justifyContent,
        style = {
            property("flex-direction", "column")
            if (style != null) { style() }
        },
        attrs = { if (attrs != null) attrs() }
    ) { content() }
}


enum class JustifyContent(val stringValue: String) {
    START("start"),
    FLEXSTART("flex-start"),
    CENTER("center"),
    FLEXEND("flex-end"),
    END("end"),
    SPACEBETWEEN("space-between"),
    SPACEAROUND("space-around"),
    SPACEEVENLY("space-evenly"),
}

enum class AlignItems(val stringValue: String) {
    FLEXSTART("flex-start"),
    CENTER("center"),
    FLEXEND("flex-end"),
    STRETCH("stretch"),
    BASELINE("baseline")
}
