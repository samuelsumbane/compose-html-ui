package dev.composehtmlui.core.tokens

import kotlinx.browser.window

fun saveThemePreference(key: String, value: String) {
    window.localStorage.setItem(key, value)
}

fun loadThemePreference(key: String): Boolean {
    return when(window.localStorage.getItem(key)) {
        "dark" -> true
        "light" -> false
        else -> window.matchMedia("(prefers-color-scheme: dark)").matches
    }
}
