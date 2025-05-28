import androidx.compose.runtime.*
import components.MyHoverableComponent
import components.ShowButtonContent
import components.outlineButton
import components.primaryButton
import components.sidebarButton
import components.textButton
import dev.components.C
import dev.components.CTexts.h3
import dev.components.CTexts.p
import dev.components.layouts.JustifyContent
import dev.components.Inputs.checkbox
import dev.components.layouts.column
import dev.components.Inputs.inputField
import dev.components.Inputs.select
import dev.components.card
import dev.components.feedback.HorizontalAlignment
import dev.components.feedback.modal
import dev.components.feedback.snackbar
import dev.components.generic.icon
import dev.components.layouts.AlignItems
//import dev.components.generic.img
import dev.components.layouts.div
import dev.components.layouts.row
import dev.components.navigation.sidebar
import dev.components.navigation.topBar
import dev.composables.ComposeHtmlTheme
import dev.composables.LocalTheme
import dev.loadThemePreference
import dev.saveThemePreference
import dev.style.DarkTheme
import dev.style.LightTheme
import dev.tokkens.Spacing
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Br
//import org.jetbrains.compose.web.css.GridAutoFlow.Column
import org.jetbrains.compose.web.dom.Hr
import org.jetbrains.compose.web.dom.Img
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


    ComposeHtmlTheme(theme = if (isDarkTheme) DarkTheme else LightTheme) {
//        Column {

        val theme = LocalTheme.current

        C.div(
            width = "100%",
            style = {
                property("display", "flex")
                property("height", "100vh")
                property("background", theme.backgroundColor)
            }
        ) {

            C.sidebar(
                width = if (sidebarExpanded) "240px" else "60px",
                header = {
                    C.row(
                        withPadding = false,
                        alignItems = AlignItems.CENTER,
                    ) {
                        if (sidebarExpanded) {
                            C.h3("Software", style = {
                                property("color", theme.sidebarColor)
                            })
                        }
                        C.primaryButton("x") {
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
                    //
//                    <svg class="bi bi-exclamation-triangle text-success" width="32" height="32" fill="currentColor" viewBox="0 0 16 16" xmlns="http://www.w3.org/2000/svg">
//                    ...
//                    </svg>
//                    SVG
                    MyHoverableComponent(style = {
                        backgroundColor(Color.sienna)
                    })
                },

                footer = {
                    C.outlineButton("L") {
//                        C.snackbar("Log out successfully")
                    }
                }
            )


            C.column(width = if (sidebarExpanded) "calc(100% - 240px)" else "calc(100% - 80px)") {
                Br()

                C.topBar("ola") {
                    C.sidebarButton(
                        text = "Ubuntu",
                        icon = { C.icon(name = "ubuntu", alt = "Ubuntu") },
                        showOnly = visibleContent
                    ) { }
                }

                C.primaryButton(text = "change theme") {
                    isDarkTheme = !isDarkTheme
                    saveThemePreference(isDarkTheme)
                }
                Br()

                C.outlineButton("text") {}

                Br()

                C.textButton("Esqueci a senha") {}
                Br()

                C.column(
                    justifyContent = JustifyContent.FLEXSTART,
                    style = { gap(18.px) }
                ) {
                    C.primaryButton("Kotlin") {}
                    C.primaryButton("is") {}
                    C.primaryButton("Awesome column") {}
                }

                Br()

                C.row(
                    justityContent = JustifyContent.FLEXSTART,
                    style = { gap(18.px) }
                ) {
                    C.primaryButton("Kotlin") {}
                    C.primaryButton("is") {}
                    C.primaryButton("Awesome in row") {}
                }

                Br()

                checkbox(true, label = "Permanecer logado.") {}

                Br()
                Hr()

                inputField("Sumbane", type = InputType.Text, placeholder = "Digite o nome") {}

                Br()

                select( onChange = {
                    if (it != null) {
                        console.log(it)
                    }
                } ) {
                    Option("0") { Text("Ktor") }
                    Option("1") { Text("Spring boot") }
                    Option("2") { Text("Other") }
                }

                Br()

//            C.dangerAlert(
//                "Venda feita com sucesso", "A venda feita por xy foi aceite. haha."
//            )

                Br()

                C.card("HP Printer", width = "100%", height = "100px",
                    content = {
                        C.p("o")
                    })
                Br()

//            C.modal(
//                onDismissRequest = {},
//                width = "30vw",
//                height = "90vw",
//                position = HorizontalAlignment.START
//            ) {
//
//            }

//            C.snackbar("Item saved successfuly")


            }
        }
    }
}
