package com.rjspies.daedalus.weight.addweight

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddChart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.rjspies.daedalus.R
import com.rjspies.daedalus.data.data.Weight
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddWeightDialog(onDismiss: () -> Unit) {
    val viewModel = koinViewModel<AddWeightViewModel>()
    val weightValue = viewModel.weightValue.collectAsState().value
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    if (weightValue != null) {
                        val weight = Weight(value = weightValue, note = null)
                        viewModel.saveWeight(weight)
                        onDismiss()
                    } else {
                        // TODO set error
                    }
                },
            ) {
                Text(text = "Hinzufügen")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = "Abbrechen")
            }
        },
        icon = {
            Icon(
                imageVector = Icons.Rounded.AddChart,
                contentDescription = null,
            )
        },
        title = {
            Text(text = "Gewicht hinzufügen")
        },
        text = {
            OutlinedTextField(
                value = weightValue?.toString() ?: "",
                onValueChange = {
                    val newWeightValue = it.filtered().parseToFloat()
                    viewModel.setWeightValue(newWeightValue)
                },
                label = {
                    Text(text = stringResource(id = R.string.add_weight_weight_text_field_label))
                },
                supportingText = {
                    Text(text = stringResource(id = R.string.add_weight_weight_text_field_supporting_message))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next,
                ),
            )
        },
    )
}

private fun String.filtered(): String = filter { it.isDigit() || it == '.' || it == ',' }
private fun String?.parseToFloat(): Float? = this?.replace(",", ".")?.toFloatOrNull()
