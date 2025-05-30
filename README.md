
# ComposeHtmlUI

A modern, lightweight Kotlin library for building reusable HTML UI with [JetBrains Compose for Web](https://github.com/JetBrains/compose-multiplatform).

## Installation

Add the following dependencies in your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("org.jetbrains.compose.web:web-core:<version>")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation(project(":compose-html-ui"))
}
```

## Project Structure
dev.composehtmlui/

    ├── core/         → Base helpers and infrastructure
        ├── tokens/       → Design tokens (colors, typography, spacing)
    ├── layout/       → Flexbox-style layout builders (`row`, `column`, etc.)
    ├── components/   → Ready-to-use UI components
        ├── buttons/     → (primaryButton, outlineButton, textButton)
        ├── cTexts/      → Composables for text elements (C.p, C.h1, C.h2, etc.)
        ├── feedback/    → (Alert, Modal, SnackBar)
        ├── generic/     → (Img)
        ├── inputs/      → (CheckBox, InputField, SelectBox)
        ├── navigation/  → Basic navigation composables (Sidebar, TopBar)
    ├── style/        → Design system variables like colors and theme.


ComposeHtmlUI composables extend from the (C) object, so creation and use must follow this C.`tagName` structure.
Example: C.p, C.h4, C.primaryButton, C.div, C.column and more.



## Example Usage

```
import dev.composehtmlui.layout.column
import dev.composehtmlui.components.buttons.PrimaryButton

@Composable
fun MyApp() {
    C.column {
        PrimaryButton("Click me") {
            println("Button clicked!")
        }
    }
}
```

### Modal
```
C.modal(
    onDismissRequest = { },
    width = 30.vw,
    height = 90.vw,
    position = HorizontalAlignment.START
) {
    // Content()
}
```

### Select
```
C.select( onChange = {
    it?.let { techId ->
        console.log(techId)
    }
} ) {
    Option("0") { Text("Ktor") }
    Option("1") { Text("Spring boot") }
    Option("2") { Text("Other") }
}
```
Find more examples in: ```dev/composehtmlui/style/main.kt```


## AppColorss 
ComposeHtmlUI brings with it the TailWind color palette (https://tailwindcss.com/docs/colors).
The colors object is in:
```dev/composehtmlui/style/AppColorss.kt```

Usage example:
### H1
```
C.h1(
    style = {
        color(AppColors.orange300)
    }
) {
    Text(“Hello world”)
}
```
### Button
```
C.outlineButton(text = "Create account", style = {
    backgroundAppColors(AppColors.sky900)
    color(AppColors.sky100)
) {
    // TODO()
}

```

## Themes
You can easily set the colors of the light and dark theme
to work for all ComposeHtmlUI composables (which follow this C.`tagName` structure).

You can add or change theme values in this file:
 ``` dev/composehtmlui/style/Theme.kt ```

You can use the theme variables like this:
``` 
// retrieving the theme 
val theme = LocalTheme.current

// use
C.div(
    style = {
        backgroundAppColors(theme.backgroundAppColors)
    }
) {
    // Content() 
}
```

## Contributing
We welcome contributions!
To contribute:

    Fork the repository

    Create a new branch (feature/your-feature-name)

    Commit your changes

    Submit a Pull Request




[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This template uses the HTML Compose library.

- `./gradlew jsBrowserRun` - run application in a browser
- `./gradlew jsBrowserRun --continuous` - run application continuously in a browser
- `./gradlew jsBrowserProductionWebpack` - produce the output in `build/dist`




