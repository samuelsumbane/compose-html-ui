import androidx.compose.runtime.*
import com.samuelsumbane.composehtmlui.components.buttons.MyHoverableComponent
import com.samuelsumbane.composehtmlui.components.buttons.ShowButtonContent
import com.samuelsumbane.composehtmlui.components.buttons.outlineButton
import com.samuelsumbane.composehtmlui.components.buttons.primaryButton
import com.samuelsumbane.composehtmlui.components.buttons.sidebarButton
import com.samuelsumbane.composehtmlui.components.buttons.textButton
import com.samuelsumbane.composehtmlui.components.C
import com.samuelsumbane.composehtmlui.components.CTexts.h3
import com.samuelsumbane.composehtmlui.components.CTexts.p
import com.samuelsumbane.composehtmlui.layout.JustifyContent
import com.samuelsumbane.composehtmlui.components.Inputs.checkbox
import com.samuelsumbane.composehtmlui.layout.column
import com.samuelsumbane.composehtmlui.components.Inputs.inputField
import com.samuelsumbane.composehtmlui.components.Inputs.select
import com.samuelsumbane.composehtmlui.components.card
import com.samuelsumbane.composehtmlui.components.generic.icon
import com.samuelsumbane.composehtmlui.layout.AlignItems
//import com.samuelsumbane.composehtmlui.components.generic.img
import com.samuelsumbane.composehtmlui.layout.div
import com.samuelsumbane.composehtmlui.layout.row
import com.samuelsumbane.composehtmlui.components.navigation.sidebar
import com.samuelsumbane.composehtmlui.components.navigation.topBar
import com.samuelsumbane.composehtmlui.dev.composables.ComposeHtmlTheme
import com.samuelsumbane.composehtmlui.dev.composables.LocalTheme
import com.samuelsumbane.composehtmlui.dev.loadThemePreference
import com.samuelsumbane.composehtmlui.dev.saveThemePreference
import com.samuelsumbane.composehtmlui.style.DarkTheme
import com.samuelsumbane.composehtmlui.style.LightTheme
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Br
//import org.jetbrains.compose.web.css.GridAutoFlow.Column
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
