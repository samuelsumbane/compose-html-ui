package dev.composehtmlui.core.tokens

import kotlinx.browser.window

fun saveThemePreference(isDark: Boolean) {
    window.localStorage.setItem("isDarkTheme", isDark.toString())
}

fun loadThemePreference(): Boolean {
    return window.localStorage.getItem("isDarkTheme")?.toBoolean() ?: false
}
