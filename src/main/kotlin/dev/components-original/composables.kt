//package components
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import app.softwork.routingcompose.Router
//import kotlinx.browser.sessionStorage
//import kotlinx.browser.window
//import kotlinx.coroutines.launch
//import org.jetbrains.compose.web.attributes.ButtonType
//import org.jetbrains.compose.web.attributes.InputType
//import org.jetbrains.compose.web.attributes.forId
//import org.jetbrains.compose.web.attributes.maxLength
////import org.jetbrains.compose.web.attributes.InputType
//import org.jetbrains.compose.web.attributes.min
//import org.jetbrains.compose.web.attributes.onSubmit
//import org.jetbrains.compose.web.attributes.readOnly
//import org.jetbrains.compose.web.css.*
//import org.jetbrains.compose.web.dom.*
//import org.jetbrains.compose.web.events.SyntheticInputEvent
//import org.w3c.dom.HTMLInputElement
//import repository.UserRepository
//
//@Composable
//fun cardButtons(
//    showDetailsButton: Boolean = false,
//    onSeeDetails: () -> Unit = {},
//    onEditButton: () -> Unit = {},
//    showDeleteBtn: Boolean = true,
//    onDeleteButton: () -> Unit = {}
//) = Div(attrs = { classes("card-footer-buttons") }) {
//    if (showDetailsButton) {
//        button("eyeBtn", "", hoverText = "Ver detalhes") {
//            onSeeDetails()
//        }
//    } else {
//        button("card-editButton", "", hoverText = "Editar") {
//            onEditButton()
//        }
//    }
//
//    if (showDeleteBtn) {
//        button("deleteButton", "", hoverText = "Deletar") {
//            onDeleteButton()
//        }
//    }
//}
//
//@Composable
//fun userCardButtons(
//    onSeeMoreDetailsButton: () -> Unit,
//) = Div(attrs = { classes("card-footer-buttons") }) {
//    button("eyeBtn", "", hoverText = "Ver Detalhes", onClick = onSeeMoreDetailsButton)
//}
//
//@Composable
//fun optionsColectionDiv(
//    btnText: String = "Exportar Todos",
//    divContent: @Composable () -> Unit
//) {
//    var showDivContent by mutableStateOf(false)
//    Div(attrs = {classes("multiFilesExportButton")}) {
//        button("multiFilesExportButton-btn", btnText) {
//            showDivContent = !showDivContent
//        }
//        if (showDivContent) {
//            Div(attrs = { classes("multiFilesExportButton-child")}) {
//                divContent()
//            }
//        }
//    }
//}
//
//@Composable
//fun clickableOption(text: String, onClickFun: () -> Unit) {
//    P(attrs = {
//        onClick { onClickFun() }
//    }) { Text(text) }
//}
//
//@Composable
//fun OptionsDiv(divId: String, divContent: @Composable () -> Unit) {
//    Div(attrs = {
//        classes("options-div")
//        id(divId)
//    }) {
//        Div(attrs = { classes("options-div-child")}) {
//            divContent()
//        }
//    }
//}
//
//@Composable
//fun OptionsDivItem(
//    title: String,
//    text: String,
//    onClick: () -> Unit
//) {
//    Div(attrs = {
//        onClick { onClick() }
//    }) {
//        P(attrs = { classes("optinsDivItemTitle") }){
//            Text(title)
//        }
//        P(attrs = { classes("optinsDivItemText") }) {
//            Text(text)
//        }
//    }
//}
//
//
//
//
//
//@Composable
//fun loadingModal() {
//    div("flashingModal") {
//        Div(attrs = { classes("dotsdiv") }) {
//            Div(attrs = { classes("snippet") }) {
//                Div(attrs = {
//                    classes("stage")
//                }) {
//                    Div(attrs = { classes("dot-flashing") }) {
//                    }
//                }
//
//            }
//        }
//    }
//}
//
//fun showSoldProductChart(labels: Array<String>, quantity: Array<String>) {
//    window.setTimeout({
//        (window.asDynamic().soldProductsChart)(labels, quantity)
//    }, 500)
//}
//
//fun showTopUsers(labels: Array<String>, sales: Array<String>) {
//    window.setTimeout({
//        (window.asDynamic().topUsersChart)(labels, sales)
//    }, 500)
//}
//
//fun showMonthlyProfits(labels: Array<String>, profits: Array<String>) {
//    window.setTimeout({
//        (window.asDynamic().salesProfitsByMonthsAndYear)(labels, profits)
//    }, 400)
//}
//fun showMonthlySales(labels: Array<String>, quantities: Array<Int>) {
//    window.setTimeout({
//        (window.asDynamic().salesQuantitiesByMonthsAndYear)(labels, quantities)
//    }, 400)
//}
//
//
//fun initializeDataTable() {
//    window.setTimeout({
//        js("""
//            $(document).ready(function() {
//                $('.myTable').DataTable({
//                    stripeClasses: ['odd', 'even'],
//                    paging: true,
//                    searching: true,
//                    language: {
//                        "decimal":        "",
//                        "emptyTable": "",
//                        "zeroRecords": "",
//                        "info":           "Mostrando _START_ to _END_ of _TOTAL_ registros",
//                        "infoEmpty":      "Mostrando 0 to 0 of 0 registros",
//                        "infoFiltered":   "(Filtrado de _MAX_ total registros)",
//                        "infoPostFix":    "",
//                        "thousands":      ",",
//                        "lengthMenu":     "Mostrar _MENU_ registros",
//                        "loadingRecords": "Carregando...",
//                        "processing":     "",
//                        "search":         "Pesquisar:",
//                        "paginate": {
//                            "first":      "Primeiro",
//                            "last":       "Último",
//                            "next":       "Próximo",
//                            "previous":   "Anterior"
//                        },
//                        "aria": {
//                            "orderable":  "Ordenar esta coluna",
//                            "orderableReverse": " Reverse esta coluna"
//                        }
//                    }
//                });
//            });
//        """)
//    }, 500) // Aguarde para garantir que a tabela foi montada
//}
//
//fun printPaper() {
//    window.setTimeout({
//        js(
//            """
//            var printData = document.getElementById("parentMainDiv");
//            var newWin = window.open("");
//            newWin.document.write(printData.outerHTML);
//            newWin.print();
//            newWin.close();
//        """
//        )
//    })
//}
//
//@Composable
//fun CardPitem(pKey: String, pValue: String) {
//    Div {
//        P { Text("$pKey: ") }
//        P { Text(pValue) }
//    }
//}
//
//@Composable
//fun <K> formDiv(
//    label: String,
//    inputValue: String,
//    inputType: InputType<K>,
//    maxLength: Int = 0,
//    oninput: (SyntheticInputEvent<K, HTMLInputElement>) -> Unit,
//    spanError: String?,
//) {
//    Div {
//        Label(attrs = { classes("inputTitleLabel") }) { Text(label) }
//        Input(type = inputType, attrs = {
//            classes("formTextInput")
//            value(inputValue)
//            min("0")
//            if (maxLength > 0) { maxLength(maxLength) }
//            onInput { event -> oninput(event) }
//        })
//        if (spanError != null) {
//            Span(attrs = { classes("errorText") }) { Text(spanError) }
//        }
//    }
//}
//
//
//@Composable
//fun formDivReadOnly(
//    label: String,
//    inputValue: String,
//) {
//    Div {
//        Label { Text(label) }
//        Input(type = InputType.Text, attrs = {
//            classes("formTextInput")
//            readOnly()
//            value(inputValue)
//        })
//    }
//}
//
//@Composable
//fun modalPItem(
//    key: String,
//    value:@Composable () -> Unit
//) {
//    Div(attrs = { classes("inRowDiv")}) {
//        Label { Text(key) }
//        value()
//    }
//}
//
//
////sellButtonsControl
//@Composable
//fun submitButtons(
//    divClass: String = "min-submit-buttons",
//    submitBtnText: String = "Finalizar",
//    onBackClicked: () -> Unit,
//) {
//    Div(attrs = {
////        id("sellButtonsControl")
//        classes("min-submit-buttons")
//    }) {
//        button("closeButton", "Fechar") { onBackClicked() }
//        button("submitButton", btnText = submitBtnText, ButtonType.Submit)
//    }
//}
//
//
//@Composable
//fun afStatusIndicator(spanText: String, id: String, labelText: Int) {
//    Div(attrs = { classes("tooltip") }) {
//        Span(attrs = { classes("tooltiptext") }) { Text(spanText) }
//        Div(attrs = { classes("afStatus-indicator"); id(id) })
//        Label(attrs = { id("") }) {
//            Text("$labelText")
//        }
//    }
//}
//
//@Composable
//fun homeDivMinResume(
//    divId: String,
//    title: String,
//    firstTextFirstDiv: String,
//    secondTextFirstDiv: String,
//    firstTextSecondDiv: String,
//    secondTextSecondDiv: String,
//) {
//    div("divId") {
//        H4 { Text(title) }
//        Br()
//        div(divClasses=listOf("flex")) {
//            Label { Text("$firstTextFirstDiv: ") }
//            P(attrs = { classes("home-bold") }) {
//                Text(secondTextFirstDiv)
//            }
//        }
//        Br()
//        div(divClasses=listOf("flex")) {
//            P { Text("$firstTextSecondDiv:") }
//            P (attrs = { classes("home-bold") }) {
//                Text(secondTextSecondDiv)
//            }
//        }
//    }
//}
//
//@Composable
//fun UserPerfilOptions(
//    id: String,
//    userName: String,
//    currentTheme: String,
//    onChangeTheme: () -> Unit,
//) {
//    val router = Router.current
//    val coroutineScope = rememberCoroutineScope()
//
//    div( id, listOf("user-perfil-options")) {
//        P(attrs = { id("userNameLabel") }) {
//            Text(userName)
//        }
//
//        button("bt", "Perfil") {
//            router.navigate("/eachUser")
//        }
//
//        button("bt", "Tema: $currentTheme") { onChangeTheme() }
//
//        button("bt", "Sair") {
//            coroutineScope.launch {
//                val (status, message) = UserRepository().logout()
//                if (status == 200) {
//                    sessionStorage.removeItem("jwt_token")
//                    router.navigate("/")
//                } else {
//                    alert("error", "Erro", message)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun labelFor(id: String = "", lClasses: List<String> = emptyList(), text: String) {
//    Label(attrs = {
//        if (id.isNotBlank()) { forId(id) }
//        if (lClasses.isNotEmpty()) { classes(lClasses) }
//    }) { Text(text) }
//}
//
//@Composable
//fun selectDiv(
//    label: String,
//    selectId: String,
//    inputError: String?,
//    onOptionChange: (String?) -> Unit,
//    optionsContent: @Composable () -> Unit
//) {
//    Div(attrs = {
//        style {
//            display(DisplayStyle.Flex)
//            flexDirection(FlexDirection.Column)
//        }
//    }) {
//        labelFor(selectId, text = label)
//        Select(attrs = {
//            style { height(33.px) }
//            id(selectId)
//            classes("formTextInput")
//            onChange { onOptionChange(it.value) }
//        }) { optionsContent() }
//        if (inputError != null) {
//            labelFor(lClasses = listOf("errorText"), text = inputError)
//        }
//    }
//}
//
//
//fun unknownErrorAlert() {
//    alert("error", "Erro", "Houve um erro desconhecido. Actualiza a pagina e tente novamente.\n Se o erro persistir entre em contacto com o gerente.")
//}
//
//@Composable
//fun rightPartForReports(
//    title: String,
//    thisModalState: String,
//    onCloseModal: () -> Unit,
//    leftPartFun: @Composable () -> Unit,
//) {
//    Div(attrs = { classes("scrolled", "max-modal", "customizedMaxModal", thisModalState) }) {
//
//        Div(attrs = { classes("max-modal-header") }) {
//            H3(attrs = { classes("max-modal-title") }) { Text(title) }
//        }
//
//        Div(attrs = { classes("max-modal-body") }) {
//            Form(attrs = {
//                classes("max-modal-body-sellForm")
//                onSubmit { event ->
//                    event.preventDefault()
//                }
//            }) {
//
//                Div(attrs = { id("r-leftPart") }) { leftPartFun() }
//                Div(attrs = {
//                    id("r-rightPart")
//                    style {
//                        width(40.percent)
//                        property("margin", "0 0 0 auto")
//                    }
//                }) {
//                    Div(attrs = { id("rightPart-body") }) {
//                        Div(attrs = { classes("reportButtons") }) {
//                            Button(attrs = {
//                                id("cancelButton")
//                                onClick { onCloseModal() }
//                            }) {
//                                Label(attrs = { classes("btnLabel") }) {
//                                    Text("Fechar")
//                                }
//                            }
//
//                            Button(attrs = {
//                                id("printFatDoc")
//                                onClick { printPaper() }
//                            }) {
//                                Label(
//                                    attrs = { classes("btnLabel") }
//                                ) { Text("Imprimir") }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        Div(attrs = { classes("max-modal-footer") })
//    }
//
//}