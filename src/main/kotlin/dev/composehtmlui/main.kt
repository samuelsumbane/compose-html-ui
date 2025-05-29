import androidx.compose.runtime.*
import com.samuelsumbane.composehtmlui.components.card
import dev.composehtmlui.C
import dev.composehtmlui.components.CTexts.h2
import dev.composehtmlui.components.CTexts.h3
import dev.composehtmlui.components.CTexts.p
import dev.composehtmlui.style.ComposeHtmlTheme
import dev.composehtmlui.components.Inputs.checkbox
import dev.composehtmlui.components.Inputs.inputField
import dev.composehtmlui.components.Inputs.select
import dev.composehtmlui.style.LocalTheme
import dev.composehtmlui.components.buttons.ShowButtonContent
import dev.composehtmlui.components.buttons.outlineButton
import dev.composehtmlui.components.buttons.primaryButton
import dev.composehtmlui.components.buttons.sidebarButton
import dev.composehtmlui.components.buttons.textButton
import dev.composehtmlui.components.generic.icon
import dev.composehtmlui.components.navigation.sidebar
import dev.composehtmlui.components.navigation.topBar
import dev.composehtmlui.core.tokkens.loadThemePreference
import dev.composehtmlui.core.tokkens.saveThemePreference
import dev.composehtmlui.layout.AlignItems
import dev.composehtmlui.layout.JustifyContent
import dev.composehtmlui.layout.column
import dev.composehtmlui.layout.div
import dev.composehtmlui.layout.row
import dev.composehtmlui.style.DarkTheme
import dev.composehtmlui.style.LightTheme
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Br
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


    ComposeHtmlTheme(theme = if (isDarkTheme) DarkTheme else LightTheme) {

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
                            C.h3("Software", style = { theme ->
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
                },

                footer = {
                    C.outlineButton("L") {
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

                C.textButton("Forgot password") {}
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

                C.checkbox(true, label = "Permanecer logado.") {}

                Br()
                Hr()

                C.inputField("Sumbane", type = InputType.Text, placeholder = "Digite o nome") {}

                Br()

                C.select( onChange = {
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

                C.card(title = {
                    C.h2("Hello")
                }, width = "100%", height = "100px",
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
