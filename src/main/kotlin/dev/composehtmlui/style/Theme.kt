package dev.composehtmlui.style

import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.Color


/**
 * Configure your themes
 *
 * Change themes colors values in objects.
 * Can use color name like "dodgerblue"
 * e.g: override val primary = "dodgerblue"
 */


interface Theme {
    val primary: CSSColorValue
    val onPrimary: CSSColorValue

    val outline: CSSColorValue
    val disabled: CSSColorValue

    val background: CSSColorValue
    val onBackground: CSSColorValue

    val surface: CSSColorValue
    val onSurface: CSSColorValue

    val inputBackground: CSSColorValue
    val inputText: CSSColorValue

    val buttonText: CSSColorValue

    val sidebarBackground: CSSColorValue
    val sidebarContent: CSSColorValue

    val error: CSSColorValue
    val warning: CSSColorValue
    val link: CSSColorValue
    val icon: CSSColorValue
}


object LightTheme : Theme {
    override val primary = AppColors.blue700
    override val onPrimary = AppColors.slate900

    override val outline = AppColors.blue950
    override val disabled = AppColors.gray600

    override val background = AppColors.white
    override val onBackground = AppColors.gray700

    override val surface = AppColors.teal50
    override val onSurface = AppColors.slate700

    override val inputBackground = AppColors.gray400
    override val inputText = AppColors.black

    override val buttonText = AppColors.sky100

    override val sidebarBackground = AppColors.slate900
    override val sidebarContent = AppColors.sky100

    override val error = AppColors.red500
    override val warning = AppColors.orange600
    override val link = AppColors.blue900
    override val icon = AppColors.sky500
}


object DarkTheme : Theme {
    override val primary = AppColors.blue600
    override val onPrimary = AppColors.sky100

    override val outline = AppColors.blue900
    override val disabled = AppColors.gray600

    override val background = AppColors.slate900
    override val onBackground = AppColors.white

    override val surface = AppColors.slate700
    override val onSurface = AppColors.blue100

    override val inputBackground = AppColors.gray600
    override val inputText = AppColors.white

    override val buttonText = AppColors.sky100

    override val sidebarBackground = AppColors.slate600
    override val sidebarContent = AppColors.sky100

    override val error = AppColors.red600
    override val warning = AppColors.orange600
    override val link = AppColors.blue600
    override val icon = AppColors.sky200
}
