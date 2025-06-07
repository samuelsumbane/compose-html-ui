package dev.composehtmlui.style.icons

import androidx.compose.runtime.Composable
import dev.composehtmlui.C
import dev.composehtmlui.style.LocalTheme
import org.jetbrains.compose.web.ExperimentalComposeWebSvgApi
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.maxWidth
import org.jetbrains.compose.web.css.minHeight
import org.jetbrains.compose.web.css.minWidth
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.ElementScope
import org.jetbrains.compose.web.svg.Path
import org.jetbrains.compose.web.svg.Svg
import org.jetbrains.compose.web.svg.fill
import org.jetbrains.compose.web.svg.fillRule
import org.jetbrains.compose.web.svg.height
import org.jetbrains.compose.web.svg.width
import org.w3c.dom.svg.SVGElement
import org.w3c.dom.svg.SVGPathElement
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.iterator

@OptIn(ExperimentalComposeWebSvgApi::class)
@Composable
fun C.iconSvg(
    icon: Icon,
    size: CSSNumeric = 20.px,
    fillColor: CSSColorValue? = null,
) {
    val theme = LocalTheme.current
    basicSvgLoad(size) {
        // fr = fill-rule
        for((d, fr) in icon.paths) {
            Path(
                d = d,
                attrs = {
                    fill(fillColor?.toString() ?: theme.icon.toString())
                    fr?.let {
                        fillRule(it)
                    }
                    width(16.px)
                    height(16.px)
                }
            )
        }
    }
}

@OptIn(ExperimentalComposeWebSvgApi::class)
@Composable
fun C.filledIconSvg(
    filledIcon: FilledIcon,
    size: CSSNumeric = 20.px,
    fillColor: CSSColorValue? = null,
) {
    val theme = LocalTheme.current
    basicSvgLoad(size) {
        // fr = fill-rule
        for((d, fr) in filledIcon.paths) {
            Path(
                d = d,
                attrs = {
                    fill(fillColor?.toString() ?: theme.icon.toString())
                    fr?.let {
                        fillRule(it)
                    }
                    width(16.px)
                    height(16.px)
                }
            )
        }
    }
}

@OptIn(ExperimentalComposeWebSvgApi::class)
@Composable
fun basicSvgLoad(
    size: CSSNumeric = 20.px,
    content:  @Composable ElementScope<SVGElement>.() -> Unit
) {
    Svg(
        viewBox = "0 0 16 16",
        attrs = {
            style {
                minHeight(size)
                minWidth(size)
                maxWidth(size)
            }
        }
    ) {
        content()
    }
}