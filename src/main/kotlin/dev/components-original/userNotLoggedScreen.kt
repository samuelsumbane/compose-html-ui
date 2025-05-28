package components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.*

//@Composable
//fun userNotLoggedScreen() {
//    Div() {
//        H3() {
//            Text("Usuário não logado.\n Faça o login para aceder a essa pagina")
//        }
//        A(href = "/") {
//            Text("Ir para pagina de login")
//        }
//    }
//}

@Composable
fun userNotLoggedScreen() {
    basicAlertPage(
        "Usuário não logado.",
        "Faça o login para acessar esta página.",
        "/",
        "Fazer Login"
    )
}

@Composable
fun userHasNotAccessScreen(to: String = "sales") {
    basicAlertPage(
        "Acesso Restrito.",
        "Você não tem acesso a está pagina.",
        newPagePath = if (to != "sales") "/#/$to" else "/#/sales",
        "Ir para vendas"
    )
}

@Composable
fun basicAlertPage(title: String, message: String, newPagePath: String = "",
                   btnText: String = "", hasBtn: Boolean = true
) {
    Div(attrs = {
        classes("user-not-logged-container")
    }) {
        H3(attrs = {
            classes("user-not-logged-title")
        }) {
            Text(title)
        }
        P (attrs = {
            classes("user-not-logged-message")
        }) {
            Text(message)
        }
        if (hasBtn) {
            A(href = newPagePath, attrs = {
                classes("login-button")
            }) {
                Text(btnText)
            }
        }
    }
}
