package dev.composehtmlui.core.tokens


import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.rgb
import org.jetbrains.compose.web.css.rgba

data class RGB(val r: Int, val g: Int, val b: Int)

fun rgb(r: Int, g: Int, b: Int): CSSColorValue = rgb(r, g, b)

fun rgba(r: Int, g: Int, b: Int, a: Double): CSSColorValue = rgba(r, g, b, a)

fun CSSColorValue.withAlpha(alpha: Double): CSSColorValue {
    val rgb = this.toRgbOrNull() ?: return this
    return rgba(rgb.r, rgb.g, rgb.b, alpha.coerceIn(0.0, 1.0))
}

fun CSSColorValue.darken(percent: Int): CSSColorValue {
    val rgb = this.toRgbOrNull() ?: return this
    val factor = (100 - percent.coerceIn(0, 100)) / 100.0
    return rgb(
        (rgb.r * factor).toInt(),
        (rgb.g * factor).toInt(),
        (rgb.b * factor).toInt()
    )
}

fun CSSColorValue.toRgbOrNull(): RGB? {
    val str = this.toString().lowercase()

    // Try extract to rgb(...) or rgba(...)
    val regex = Regex("""rgba?\((\d+),\s*(\d+),\s*(\d+)(,\s*\d+(\.\d+)?)?\)""")
    val match = regex.find(str) ?: return null

    val (r, g, b) = match.destructured
    return RGB(r.toInt(), g.toInt(), b.toInt())
}
