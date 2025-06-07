package examples.code

import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import com.samuelsumbane.composehtmlui.components.card
import dev.composehtmlui.C
import dev.composehtmlui.components.buttons.iconButton
import dev.composehtmlui.components.buttons.outlineButton
import dev.composehtmlui.components.buttons.primaryButton
import dev.composehtmlui.components.cTexts.h3
import dev.composehtmlui.components.cTexts.p
import dev.composehtmlui.components.feedback.HorizontalAlignment
import dev.composehtmlui.components.feedback.modal
import dev.composehtmlui.components.feedback.snackbar
import dev.composehtmlui.components.generic.form
import dev.composehtmlui.components.inputs.checkbox
import dev.composehtmlui.components.inputs.inputField
import dev.composehtmlui.components.inputs.select
import dev.composehtmlui.components.inputs.textArea
import dev.composehtmlui.core.tokens.loadThemePreference
import dev.composehtmlui.core.tokens.saveThemePreference
import dev.composehtmlui.layout.column
import dev.composehtmlui.layout.div
import dev.composehtmlui.layout.row
import dev.composehtmlui.style.ComposeHtmlTheme
import dev.composehtmlui.style.DarkTheme
import dev.composehtmlui.style.GlobalStyles
import dev.composehtmlui.style.LightTheme
import dev.composehtmlui.style.LocalTheme
import dev.composehtmlui.style.icons.Icon
import dev.composehtmlui.style.icons.iconSvg
import org.jetbrains.compose.web.attributes.ButtonType
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.type
import org.jetbrains.compose.web.css.AlignItems
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
import org.jetbrains.compose.web.css.overflowY
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.right
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Option
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable(rootElementId = "root") {
        Style(GlobalStyles)
        Body()
    }
}

@Composable
fun Body() {
    var counter by remember { mutableStateOf(0) }
    var isDarkTheme by remember { mutableStateOf(loadThemePreference()) }
    var showModal by remember { mutableStateOf(false) }
    var modalTitle by remember { mutableStateOf("") }
    var task by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    val errors = mutableStateMapOf<String, String>()
    var allTasks by remember { mutableStateOf(listOf<taskItem>())}
    var toShowTasks by remember { mutableStateOf(listOf<taskItem>())}
    var alertVisible by remember { mutableStateOf(false) }
    var alertTitle by remember { mutableStateOf("") }
    var alertMessage by remember { mutableStateOf("") }


    ComposeHtmlTheme(theme = if (isDarkTheme) DarkTheme else LightTheme) {
        val theme = LocalTheme.current
        toShowTasks = allTasks

        C.div(
            attrs = {
                style {
                    position(Position.Fixed)
                    width(100.percent)
                    height(100.percent)
                    display(DisplayStyle.Flex)
                    backgroundColor(theme.background)
                }
            }
        ) {
            C.iconButton(
                icon = {
                    C.iconSvg(icon = if (isDarkTheme) Icon.SUN else Icon.MOON)
                },
                attrs = {
                    style {
                        position(Position.Absolute)
                        top(2.percent)
                        right(2.percent)
                    }
                }
            ) {
                isDarkTheme = !isDarkTheme
                saveThemePreference(isDarkTheme)
            }
            C.card(
                attrs = {
                    style {
                        property("margin", "auto")
                        width(60.percent)
                        height(80.percent)
                    }
                },
                content = {
                    C.row(
                        attrs = {
                            style {
                                width(100.percent)
                                height(50.px)
                                justifyContent(JustifyContent.SpaceBetween)
                                alignItems(AlignItems.Center)
                            }
                        }
                    ) {
                        C.h3("Tasks ( ${toShowTasks.size} )")

                        C.row {
                            C.p("Filter:", color = theme.onBackground)
                            C.select(
                                onChange = { option ->
                                    toShowTasks = when (option) {
                                        "0" -> allTasks
                                        "1" -> allTasks.filter { it.state == TaskState.PEDDING }
                                        else -> allTasks.filter { it.state == TaskState.DONE }
                                    }
                                }
                            ) {
                                Option("0") { Text("All") }
                                Option("1") { Text("Pedding") }
                                Option("2") { Text("Done") }
                            }
                        }

                        C.primaryButton("+ Task") {
                            showModal = true
                            modalTitle = "Add task"
                        }
                    }

                    C.column(
                        attrs = {
                            style {
                                padding(0.px)
                                width(100.percent)
                                gap(0.px)
                                overflowY("auto")
                            }
                        }
                    ) {
                        toShowTasks.forEach { task ->
                            C.row(
                                attrs = {
                                    style {
                                        width(100.percent)
                                        justifyContent(JustifyContent.SpaceBetween)
                                    }
                                }
                            ) {
                                C.checkbox(
                                    checked = task.state == TaskState.DONE,
                                    label = "${task.name} | ${task.description}",
                                    labelAttrs = {
                                        if (task.state == TaskState.DONE) {
                                            style {
                                                property("text-decoration", "line-through")
                                            }
                                        }
                                    }
                                ) { checked ->
                                    allTasks = allTasks.map { t ->
                                        if (t.name == task.name) {
                                            t.copy(state = if (task.state == TaskState.DONE) TaskState.PEDDING else TaskState.DONE )
                                        } else t
                                    }
                                }

                                C.iconButton (
                                    icon = {
                                        C.iconSvg(icon = Icon.TRASH)
                                    },
                                ) {
                                    allTasks = allTasks.filter {
                                        it.name != task.name
                                    }
                                }
                            }
                        }
                    }
                }
            )
        }


        if (showModal) {
            C.modal(
                position = HorizontalAlignment.END,
                attrs = {
                    style {
                        width(320.px)
                        height(95.percent)
                    }
                }
            ) {
                C.form(
                    header = {
                        C.row { C.h3(modalTitle) }
                    },
                    main = {
                        C.column(
                            attrs = {
                                style {
                                    width(100.percent)
                                    gap(10.px)
                                }
                            }
                        ) {
                            C.inputField(
                                task, InputType.Text,
                                labelText = "Enter task",
                                errorText = errors["taskError"]
                            ) {
                                task = it.value
                            }

                            C.textArea(
                                taskDescription,
                                labelText = "Enter task description",
                                errorText = errors["taskDescriptionError"]
                            ) {
                                taskDescription = it.value
                            }
                        }

                    },
                    footer = {
                        C.row(
                            attrs = {
                                style {
                                    width(100.percent)
                                    justifyContent(JustifyContent.SpaceAround)
                                }
                            }
                        ) {
                            C.outlineButton("Cancel") {
                                showModal = false
                                // Clean inputfields
                                task = ""
                                taskDescription = ""
                            }

                            C.primaryButton("Submit", attrs = {
                                type(ButtonType.Submit)
                            })
                        }
                    }
                ) {
                    errors["taskError"] = if (task == "") "Task is required" else ""
                    errors["taskDescriptionError"] = if (taskDescription == "") "Task description is required" else ""
                    if (errors.none { it.value.isNotBlank() }) {
                        allTasks = allTasks + taskItem(task, taskDescription, TaskState.PEDDING)
                        task = ""
                        taskDescription = ""
                        alertMessage = "Task add successfully"
                        alertVisible = true
                    }
                }
            }
        }

        C.snackbar(
            message = alertMessage,
            visible = alertVisible,
            onDismiss = {
                alertVisible = false
            }
        )
    }
}

data class taskItem(
    val name: String,
    val description: String,
    val state: TaskState
)

enum class TaskState {
    ALL, PEDDING, DONE
}

