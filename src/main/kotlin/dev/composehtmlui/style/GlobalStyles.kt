package dev.composehtmlui.style

import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.boxSizing
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px


object GlobalStyles : StyleSheet() {
    val reset = style(universal) {
        padding(0.px)
        margin(0.px)
        boxSizing("border-box")
    }

    val htmlBody = style(selector("html, body")) {
        fontFamily("sans-serif")
    }
}