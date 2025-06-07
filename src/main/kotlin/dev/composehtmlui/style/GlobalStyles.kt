package dev.composehtmlui.style

import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.Transitions
import org.jetbrains.compose.web.css.boxSizing
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.transitions
import org.jetbrains.compose.web.dom.Style


object GlobalStyles : StyleSheet() {
    val reset = style(universal) {
        padding(0.px)
        margin(0.px)
        boxSizing("border-box")
        property("transition", "0.7s")
    }

    val htmlBody = style(selector("html, body")) {
        fontFamily("sans-serif")
    }

}

