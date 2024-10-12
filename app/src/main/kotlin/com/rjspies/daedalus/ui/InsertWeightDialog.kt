package com.rjspies.daedalus.ui

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
import androidx.compose.runtime.DisposableEffect
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
import com.rjspies.daedalus.R
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun AddWeightDialog(onDismiss: () -> Unit) {
    val viewModel = koinViewModel<InsertWeightViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    var weightValue by rememberSaveable { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    viewModel.setDismissDialog(onDismiss)

    DisposableEffect(Unit) {
        onDispose {
            viewModel.setError(null)
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                imageVector = Icons.Rounded.AddChart,
                contentDescription = stringResource(R.string.add_weight_icon_content_description),
            )
        },
        title = {
            Title(uiState)
        },
        text = {
            LaunchedEffect(Unit) { focusRequester.requestFocus() }

            Input(
                weightValue = weightValue,
                focusRequester = focusRequester,
                uiState = uiState,
                onValueChange = { weightValue = it.filtered() },
                onDone = { viewModel.insertWeight(weightValue) },
            )
        },
        confirmButton = {
            TextButton(
                onClick = { viewModel.insertWeight(weightValue) },
                content = { Text(stringResource(R.string.add_weight_add_button_text)) },
                enabled = !uiState.isLoading,
            )
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                content = { Text(stringResource(R.string.common_cancel)) },
                enabled = !uiState.isLoading,
            )
        },
    )
}

@Composable
private fun Input(
    weightValue: String,
    focusRequester: FocusRequester,
    uiState: InsertWeightUiState,
    onValueChange: (String) -> Unit,
    onDone: (String) -> Unit,
) {
    OutlinedTextField(
        value = weightValue,
        onValueChange = onValueChange,
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
            onDone = { onDone(weightValue) },
        ),
        isError = uiState.error != null,
        singleLine = true,
        enabled = !uiState.isLoading,
    )
}

@Composable
private fun Title(uiState: InsertWeightUiState) {
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
}

private fun String.filtered(): String = filter { it.isDigit() || it == '.' || it == ',' }
