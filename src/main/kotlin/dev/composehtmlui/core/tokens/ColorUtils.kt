package dev.composehtmlui.core.tokens

import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.rgb
import org.jetbrains.compose.web.css.rgba
import kotlin.math.roundToInt


fun CSSColorValue.darken(percent: Int): CSSColorValue {
    val hex = this.toString().removePrefix("#")
    if (hex.length != 6) {
        console.error("Invalid color for darken: $hex")
        return this
    }

    val p = percent.coerceIn(0, 100)
    val factor = 1 - (p / 100.0)

    val r = (hex.substring(0, 2).toInt(16) * factor).roundToInt().coerceIn(0, 255)
    val g = (hex.substring(2, 4).toInt(16) * factor).roundToInt().coerceIn(0, 255)
    val b = (hex.substring(4, 6).toInt(16) * factor).roundToInt().coerceIn(0, 255)

    return rgb(r, g, b)
}


fun CSSColorValue.withAlpha(alpha: Double): CSSColorValue {
    val hex = this.toString().removePrefix("#")
    if (hex.length != 6) {
        console.error("Invalid color for withAlpha: $hex")
        return this
    }

    val a = alpha.coerceIn(0.0, 1.0)

    val r = hex.substring(0, 2).toInt(16)
    val g = hex.substring(2, 4).toInt(16)
    val b = hex.substring(4, 6).toInt(16)

    return rgba(r, g, b, a)
}