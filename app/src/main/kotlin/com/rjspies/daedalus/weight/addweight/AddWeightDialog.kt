package com.rjspies.daedalus.weight.addweight

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddChart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.rjspies.daedalus.AddWeightError
import com.rjspies.daedalus.R
import com.rjspies.daedalus.data.data.Weight
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddWeightDialog(onDismiss: () -> Unit) {
    val viewModel = koinViewModel<AddWeightViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    var weightValue by rememberSaveable { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    viewModel.setDismissDialog(onDismiss)

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                TextButton(
                    onClick = {
                        val newWeightValue = weightValue.parseToFloat()
                        if (newWeightValue != null) {
                            val weight = Weight(
                                value = newWeightValue,
                                note = null,
                            )
                            viewModel.saveWeight(weight)
                        } else {
                            viewModel.setError(AddWeightError.ParseFloatError)
                        }
                    },
                ) {
                    Text(text = "Hinzufügen")
                }
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
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }

            OutlinedTextField(
                value = weightValue,
                onValueChange = {
                    weightValue = it.filtered()
                },
                modifier = Modifier.focusRequester(focusRequester),
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
                isError = uiState.error != null,
            )
        },
    )
}

private fun String.filtered(): String = filter { it.isDigit() || it == '.' || it == ',' }
private fun String?.parseToFloat(): Float? = this?.replace(",", ".")?.toFloatOrNull()
