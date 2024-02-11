package com.rjspies.daedalus.weight.addweight

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddChart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.rjspies.daedalus.AddWeightError
import com.rjspies.daedalus.R
import com.rjspies.daedalus.data.data.Weight
import com.rjspies.daedalus.ui.VerticalSpacerXXS
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
        icon = {
            Icon(
                imageVector = Icons.Rounded.AddChart,
                contentDescription = null,
            )
        },
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Text(stringResource(R.string.add_weight_dialog_title))

                    if (uiState.isLoading) {
                        VerticalSpacerXXS()
                        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                    }
                },
            )
        },
        text = {
            LaunchedEffect(Unit) { focusRequester.requestFocus() }

            OutlinedTextField(
                value = weightValue,
                onValueChange = { weightValue = it.filtered() },
                modifier = Modifier.focusRequester(focusRequester),
                label = { Text(stringResource(R.string.add_weight_weight_text_field_label)) },
                supportingText = {
                    if (uiState.error != null) {
                        Text(stringResource(R.string.add_weight_weight_text_field_supporting_message_error))
                    } else {
                        Text(stringResource(R.string.add_weight_weight_text_field_supporting_message))
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = { saveWeight(viewModel, weightValue) },
                ),
                isError = uiState.error != null,
                singleLine = true,
                enabled = !uiState.isLoading,
            )
        },
        confirmButton = {
            TextButton(
                onClick = { saveWeight(viewModel, weightValue) },
                content = { Text(stringResource(R.string.add_weight_add_button_text)) },
                enabled = !uiState.isLoading,
            )
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                content = { Text(stringResource(R.string.general_cancel)) },
                enabled = !uiState.isLoading,
            )
        },
    )
}

private fun saveWeight(viewModel: AddWeightViewModel, weightValue: String) {
    viewModel.setError(null)
    val parsedWeightValue = weightValue.parseToFloat()
    if (parsedWeightValue != null) {
        val weight = Weight(
            value = parsedWeightValue,
            note = null,
        )
        viewModel.saveWeight(weight)
    } else {
        viewModel.setError(AddWeightError.ParseFloatError)
    }
}

private fun String.filtered(): String = filter { it.isDigit() || it == '.' || it == ',' }
private fun String?.parseToFloat(): Float? = this?.replace(",", ".")?.toFloatOrNull()
