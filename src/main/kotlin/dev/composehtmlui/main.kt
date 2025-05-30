import androidx.compose.runtime.*
import com.samuelsumbane.composehtmlui.components.card
import dev.composehtmlui.C
import dev.composehtmlui.components.cTexts.h2
import dev.composehtmlui.components.cTexts.h3
import dev.composehtmlui.components.cTexts.p
import dev.composehtmlui.style.ComposeHtmlTheme
import dev.composehtmlui.components.inputs.checkbox
import dev.composehtmlui.components.inputs.inputField
import dev.composehtmlui.components.inputs.select
import dev.composehtmlui.style.LocalTheme
import dev.composehtmlui.components.buttons.ShowButtonContent
import dev.composehtmlui.components.buttons.outlineButton
import dev.composehtmlui.components.buttons.primaryButton
import dev.composehtmlui.components.buttons.sidebarButton
import dev.composehtmlui.components.buttons.textButton
import dev.composehtmlui.components.feedback.HorizontalAlignment
import dev.composehtmlui.components.feedback.modal
import dev.composehtmlui.components.generic.icon
import dev.composehtmlui.components.navigation.sidebar
import dev.composehtmlui.components.navigation.topBar
import dev.composehtmlui.core.tokens.loadThemePreference
import dev.composehtmlui.core.tokens.saveThemePreference
import dev.composehtmlui.layout.column
import dev.composehtmlui.layout.div
import dev.composehtmlui.layout.row
import dev.composehtmlui.style.AppColors
import dev.composehtmlui.style.DarkTheme
import dev.composehtmlui.style.LightTheme
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.minus
import org.jetbrains.compose.web.css.overflow
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vw
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Hr
import org.jetbrains.compose.web.dom.Option
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable(rootElementId = "root") {
        Body()
    }
}

@Composable
fun Body() {
    var isDarkTheme by remember { mutableStateOf(loadThemePreference()) }
    var sidebarExpanded by remember { mutableStateOf(false) }
    val visibleContent = if (sidebarExpanded) ShowButtonContent.BOTH else ShowButtonContent.ICON

    var inputText by remember { mutableStateOf("") }

    ComposeHtmlTheme(theme = if (isDarkTheme) DarkTheme else LightTheme) {

        val theme = LocalTheme.current

        C.div(
            width = 100.percent,
            height = 100.percent,
            style = {
                position(Position.Fixed)
                display(DisplayStyle.Flex)
                backgroundColor(theme.backgroundColor)
            }
        ) {

            C.sidebar(
                width = if (sidebarExpanded) 240.px else 60.px,
                header = {
                    C.row(
                        padding = null,
                        style = {
                            alignItems(AlignItems.Center)
                            justifyContent(JustifyContent.SpaceAround)
                        }
                    ) {
                        if (sidebarExpanded) {
                            C.h3("Software", style = { theme ->
                                property("color", theme.sidebarColor)
                            })
                        }
                        C.outlineButton("", icon = {
                            C.icon("list", style = { width(26.px) })
                        }, style = {
                            property("border", "none")
                            padding(0.px)
                        }) {
                            sidebarExpanded = !sidebarExpanded
                        }
                    }
                },

                content = {
                    C.sidebarButton(
                        text = "Apple",
                        icon = { C.icon(name = "apple", alt = "Apple") },
                        showOnly = visibleContent) {
                    }

                    C.sidebarButton(
                        text = "Windows",
                        icon = { C.icon(name = "windows", alt = "Windows") },
                        showOnly = visibleContent) {
                    }

                    C.sidebarButton(
                        text = "Chrome",
                        icon = { C.icon(name = "browser-chrome", alt = "Chrome") },
                        showOnly = visibleContent
                    ) {
                    }

                    C.sidebarButton(
                        text = "Github",
                        icon = { C.icon(name = "github", alt = "Github") },
                        showOnly = visibleContent
                    ) { }

                    C.sidebarButton(
                        text = "Ubuntu",
                        icon = { C.icon(name = "ubuntu", alt = "Ubuntu") },
                        showOnly = visibleContent
                    ) { }
                },

                footer = {
                    C.outlineButton("L") {
                    }
                }
            )


            C.column(
                width = 100.percent - if (sidebarExpanded) 240.px else 60.px,
                style = {
                    height(100.percent)
                    overflow("auto")
                }
            ) {
                Br()

//                C.topBar("ola") {
//                    C.sidebarButton(
//                        text = "Ubuntu",
//                        icon = { C.icon(name = "ubuntu", alt = "Ubuntu") },
//                        showOnly = visibleContent
//                    ) { }
//                }

                C.primaryButton(text = "change theme") {
                    isDarkTheme = !isDarkTheme
                    saveThemePreference(isDarkTheme)
                }
                Br()

                C.outlineButton("text") {}

                Br()

                C.textButton("Forgot password") {}
                Br()

                C.column(
                    style = {
                        gap(18.px)
                    }
                ) {
                    C.primaryButton("Kotlin") {}
                    C.primaryButton("is") {}
                    C.primaryButton("Awesome column") {}

                }

                Br()

                C.row(
                    style = {
                        gap(18.px)
                    }
                ) {
                    C.primaryButton("Kotlin") {}
                    C.primaryButton("is") {}
                    C.primaryButton("Awesome in row") {}
                }

                Br()

                C.checkbox(true, label = "rester connecté") {}

                Br()
                Hr()

                C.inputField(
                    inputText,
                    type = InputType.Text,
                    placeholder = "Enter your name"
                ) {
                    inputText = it.value
                }

                Br()

                C.select( onChange = {
                    it?.let { techId ->
                        console.log(techId)
                    }
                } ) {
                    Option("0") { Text("Ktor") }
                    Option("1") { Text("Spring boot") }
                    Option("2") { Text("Other") }
                }

                Br()

//            C.dangerAlert(
//                "Are you show you want do delete", "This action can not be ondone."
//            )

                Br()

//                C.card(title = {
//                    C.h2("Hello")
//                }, width = 100.percent, height = 100.px,
//                    content = {
//                        C.p("o")
//                    })
//                Br()

//            C.modal(
//                onDismissRequest = {},
//                width = 30.vw,
//                height = 90.vw,
//                position = HorizontalAlignment.START
//            ) {
//                // Content()
//            }

//            C.snackbar("Item saved successfuly")

            }
        }
    }
}
