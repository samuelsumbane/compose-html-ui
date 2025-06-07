import androidx.compose.runtime.*
import com.samuelsumbane.composehtmlui.components.card
import dev.composehtmlui.C
import dev.composehtmlui.components.buttons.ButtonStylesheet
import dev.composehtmlui.components.cTexts.h3
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
import dev.composehtmlui.components.cTexts.h1
import dev.composehtmlui.components.cTexts.h2
import dev.composehtmlui.components.cTexts.p
import dev.composehtmlui.components.navigation.sidebar
import dev.composehtmlui.core.tokens.loadThemePreference
import dev.composehtmlui.core.tokens.saveThemePreference
import dev.composehtmlui.layout.column
import dev.composehtmlui.layout.div
import dev.composehtmlui.layout.row
import dev.composehtmlui.style.AppColors
import dev.composehtmlui.style.DarkTheme
import dev.composehtmlui.style.GlobalStyles
import dev.composehtmlui.style.LightTheme
import dev.composehtmlui.style.LightTheme.backgroundColor
import dev.composehtmlui.style.icons.FilledIcon
import dev.composehtmlui.style.icons.Icon
import dev.composehtmlui.style.icons.filledIconSvg
import dev.composehtmlui.style.icons.iconSvg
import org.jetbrains.compose.web.ExperimentalComposeWebSvgApi
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.minHeight
import org.jetbrains.compose.web.css.minus
import org.jetbrains.compose.web.css.overflow
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Hr
import org.jetbrains.compose.web.dom.Option
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable
import org.jetbrains.compose.web.svg.width


fun main() {
    renderComposable(rootElementId = "root") {
        Style(GlobalStyles)
        Body()
    }
}


@OptIn(ExperimentalComposeWebSvgApi::class)
@Composable
fun Body() {
    var isDarkTheme by remember { mutableStateOf(loadThemePreference()) }
    var sidebarExpanded by remember { mutableStateOf(false) }
    val visibleContent = if (sidebarExpanded) ShowButtonContent.BOTH else ShowButtonContent.ICON

    var inputText by remember { mutableStateOf("") }



    ComposeHtmlTheme(theme = if (isDarkTheme) DarkTheme else LightTheme) {

        val theme = LocalTheme.current
        C.div(
            attrs = {
                style {
                    width(100.percent)
                    height(100.percent)
                    position(Position.Fixed)
                    display(DisplayStyle.Flex)
                    backgroundColor(theme.backgroundColor)
                }
            }
        ) {

            C.sidebar(
                attrs = {
                    style {
                        width(if (sidebarExpanded) 240.px else 60.px)
                        height(97.percent)
                    }
                },
                header = {
                    C.row(
                        attrs = {
                            style {
                                alignItems(AlignItems.Center)
                                justifyContent(JustifyContent.SpaceAround)
                                padding(0.px)
                            }
                        }
                    ) {
                        if (sidebarExpanded) {
                            C.h3("Software", attrs = {
                                style {
                                    property("color", theme.sidebarColor)
                                }
                            })
                        }
                        C.sidebarButton(
                            "",
                            icon = {
                                C.iconSvg(icon = Icon.LIST, fillColor = AppColors.white)
                            },
                            showOnly = ShowButtonContent.ICON
                        ) {
                            sidebarExpanded = !sidebarExpanded
                        }
                    }
                },

                content = {
                    C.sidebarButton(
                        text = "Apple",
                        icon = { C.iconSvg(Icon.APPLE, fillColor = AppColors.white) },
                        showOnly = visibleContent) {
                    }

                    C.sidebarButton(
                        text = "Windows",
                        icon = { C.iconSvg(Icon.WINDOWS, fillColor = AppColors.white) },
                        showOnly = visibleContent) {
                    }

                    C.sidebarButton(
                        text = "Chrome",
                        icon = { C.iconSvg(Icon.BROWSER_CHROME, fillColor = AppColors.white) },
                        showOnly = visibleContent
                    ) {
                    }

                    C.sidebarButton(
                        text = "Github",
                        icon = { C.iconSvg(Icon.GITHUB, fillColor = AppColors.white) },
                        showOnly = visibleContent
                    ) { }

                    C.sidebarButton(
                        text = "Ubuntu",
                        icon = { C.iconSvg(Icon.UBUNTU, fillColor = AppColors.white) },
                        showOnly = visibleContent
                    ) { }
                },

                footer = {
                    C.outlineButton("L", showOnly = ShowButtonContent.TEXT) {}
                }
            )


            C.column(
                attrs = {
                    style {
                        width(100.percent - if (sidebarExpanded) 240.px else 60.px)
                        height(100.percent)
                        overflow("auto")
                    }
                }
            ) {
                Br()

                C.primaryButton(text = "change ", icon = {
                    C.iconSvg(Icon.PALETTE)
                }) {
                    isDarkTheme = !isDarkTheme
                    saveThemePreference(isDarkTheme)
                }
                Br()

                C.outlineButton("text", icon = {
                    C.filledIconSvg(FilledIcon.BASKET_FILL)
                }) {}

                Br()

                C.textButton("Forgot password") {}
                Br()

                C.column(
                    attrs = {
                        style {
                            gap(18.px)
                        }
                    }
                ) {
                    C.primaryButton("Kotlin", icon = {
                        C.iconSvg(Icon.UBUNTU)

                    }) {}
                    C.primaryButton("is") {}
                    C.primaryButton("Awesome column") {}

                }

                Br()

                C.h1("Hello world")

                C.row(
                    attrs = {
                        style {
                            gap(18.px)
                        }
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
                    Option("1") { Text("Svelte1") }
                    Option("0") { Text("Ktor") }
//                    Option("1") { Text("Spring boot") }

                }

                Br()

//            C.dangerAlert(
//                "Are you show you want do delete", "This action can not be ondone."
//            )

                Br()

                C.card(
                    title = {
                        C.h2("Hello")
                    },
                    content = {
                        C.p("o")
                    }
                )
                Br()

//            C.modal(
//                onDismissRequest = {},
//                position = HorizontalAlignment.START
//            ) {
//                // Content()
//            }

//            C.snackbar("Item saved successfuly")

            }
        }
    }
}
