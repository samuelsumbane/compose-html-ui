package dev.style


/**
 * Configure your themes
 *
 * Change themes colors values in objects.
 * Can use color name like "dodgerblue"
 * e.g: override val primaryColor = "dodgerblue"
 */

interface Theme {
    val primaryColor: String
    val primaryTextColor: String
    val outlineColor: String
    val disabledColor: String
    val backgroundColor: String
    val layoutsBackground: String
    val inputFieldBgColor: String
    val inputFieldTextColor: String
    val buttonTextColor: String
    val sidebarBackground: String
    val sidebarColor: String

    val errorColor: String
    val warningColor: String
    val linkColor: String
}

object LightTheme : Theme {
    override val primaryColor = Color.blue700
    override val primaryTextColor = Color.slate900
    override val outlineColor = Color.blue950
    override val disabledColor = Color.gray600
    override val backgroundColor = Color.white
    override val layoutsBackground = Color.teal100
    override val inputFieldBgColor = Color.gray400
    override val inputFieldTextColor = Color.black
    override val buttonTextColor = Color.sky100
    override val sidebarBackground = Color.slate900
    override val sidebarColor = Color.sky100

    override val errorColor = Color.red600
    override val warningColor = Color.orange600
    override val linkColor = Color.blue900
}

object DarkTheme : Theme {
    override val primaryColor = Color.blue600
    override val primaryTextColor = Color.sky100
    override val outlineColor = Color.blue900
    override val disabledColor = Color.gray600
    override val backgroundColor = Color.slate900
    override val layoutsBackground = Color.slate700
    override val inputFieldBgColor = Color.gray600
    override val inputFieldTextColor = Color.white
    override val buttonTextColor = Color.sky100
    override val sidebarBackground = Color.slate300
    override val sidebarColor = Color.slate900

    override val errorColor = Color.red600
    override val warningColor = Color.orange600
    override val linkColor = Color.orange700
}
