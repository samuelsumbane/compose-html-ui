package dev.composehtmlui.style

import org.jetbrains.compose.web.css.CSSColorValue


/**
 * Configure your themes
 *
 * Change themes colors values in objects.
 * Can use color name like "dodgerblue"
 * e.g: override val primaryColor = "dodgerblue"
 */

interface Theme {
    val primaryColor: CSSColorValue
    val primaryTextColor: CSSColorValue
    val outlineColor: CSSColorValue
    val disabledColor: CSSColorValue
    val backgroundColor: CSSColorValue
    val layoutsBackground: CSSColorValue
    val inputFieldBgColor: CSSColorValue
    val inputFieldTextColor: CSSColorValue
    val buttonTextColor: CSSColorValue
    val sidebarBackground: CSSColorValue
    val sidebarColor: CSSColorValue

    val errorColor: CSSColorValue
    val warningColor: CSSColorValue
    val linkColor: CSSColorValue
}

object LightTheme : Theme {
    override val primaryColor = AppColors.blue700
    override val primaryTextColor = AppColors.slate900
    override val outlineColor = AppColors.blue950
    override val disabledColor = AppColors.gray600
    override val backgroundColor = AppColors.white
    override val layoutsBackground = AppColors.teal100
    override val inputFieldBgColor = AppColors.gray400
    override val inputFieldTextColor = AppColors.black
    override val buttonTextColor = AppColors.sky100
    override val sidebarBackground = AppColors.slate900
    override val sidebarColor = AppColors.sky100

    override val errorColor = AppColors.red600
    override val warningColor = AppColors.orange600
    override val linkColor = AppColors.blue900
}

object DarkTheme : Theme {
    override val primaryColor = AppColors.blue600
    override val primaryTextColor = AppColors.sky100
    override val outlineColor = AppColors.blue900
    override val disabledColor = AppColors.gray600
    override val backgroundColor = AppColors.slate900
    override val layoutsBackground = AppColors.slate700
    override val inputFieldBgColor = AppColors.gray600
    override val inputFieldTextColor = AppColors.white
    override val buttonTextColor = AppColors.sky100
    override val sidebarBackground = AppColors.slate600
    override val sidebarColor = AppColors.sky100

    override val errorColor = AppColors.red600
    override val warningColor = AppColors.orange600
    override val linkColor = AppColors.orange700
}
