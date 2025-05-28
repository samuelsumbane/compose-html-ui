package components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.disabled
//import kotlinx.html.InputType
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.cursor
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Input
//import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.Text


@Composable
fun CheckboxWG( // CheckboxWidget ------>>
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String = "",
    id: String = "checkbox-${label.lowercase().replace(" ", "-")}",
    enabled: Boolean = true
) {
    Label(
        forId = id,
        attrs = {
            style {
                display(DisplayStyle.Flex)
                alignItems(AlignItems.Center)
                gap(8.px)
                cursor(if (enabled) "pointer" else "not-allowed")
                opacity(if (enabled) 1.0 else 0.6)
            }
        }
    ) {
        Input(
            type = InputType.Checkbox,
            attrs = {
                this.id(id)
                checked(checked)
                if (!enabled) { disabled() }
                onInput {
//                    val isChecked = it.value == "on" || it.nativeEvent.target?.asDynamic()?.checked == true
                    val isChecked = it.nativeEvent.target?.asDynamic()?.checked as? Boolean ?: false
                    onCheckedChange(isChecked)

                }
            }
        )
        if (label.isNotBlank()) {
            Text(label)
        }
    }
}
