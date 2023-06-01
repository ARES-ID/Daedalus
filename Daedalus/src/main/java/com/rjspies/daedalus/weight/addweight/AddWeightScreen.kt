package com.rjspies.daedalus.weight.addweight

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rjspies.daedalus.R
import com.rjspies.daedalus.compose.ToolbarContent
import com.rjspies.daedalus.compose.VerticalSpacerM
import com.rjspies.daedalus.compose.VerticalSpacerXS
import com.rjspies.daedalus.compose.WeightedSpacer
import com.rjspies.daedalus.compose.horizontalSpacingM
import com.rjspies.daedalus.compose.verticalSpacingM
import com.rjspies.daedalus.ui.theme.daedalusDatePickerDialogColors
import com.rjspies.daedalus.ui.widgets.ButtonType
import com.rjspies.daedalus.ui.widgets.DaedalusButton
import com.rjspies.daedalus.ui.widgets.DaedalusOutlinedTextField
import com.rjspies.daedalus.weight.service.data.Weight
import com.rjspies.daedalus.weight.weighthistory.asUserfacingString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.FormatStyle

@Suppress("LongMethod")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWeightScreen(
    onBack: () -> Unit,
    showSnackbar: suspend (message: String) -> Unit
) {
    ToolbarContent(title = stringResource(R.string.add_weight_toolbar_title), onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .verticalSpacingM(),
            content = {
                val viewModel = viewModel<AddWeightViewModel>(factory = AddWeightViewModel.Factory)
                val weight = rememberSaveable { mutableStateOf<String?>(null) }
                val weightError = rememberSaveable { mutableStateOf<WeightError?>(null) }
                val note = rememberSaveable { mutableStateOf<String?>(null) }
                val showDialog = rememberSaveable { mutableStateOf(false) }
                val selectedDate = rememberSaveable { mutableStateOf(Instant.now()) }
                val weightSupportingText = when (weightError.value) {
                    is WeightError.Empty -> stringResource(R.string.add_weight_weight_text_field_supporting_message)
                    is WeightError.Undefined -> stringResource(R.string.add_weight_weight_text_field_error_message)
                    else -> stringResource(R.string.add_weight_weight_text_field_supporting_message)
                }

                if (showDialog.value) {
                    DatePicker(
                        onDismiss = { showDialog.value = false },
                        onConfirm = { selectedTimestamp ->
                            selectedTimestamp?.let {
                                selectedDate.value = Instant.ofEpochMilli(it)
                            }
                            showDialog.value = false
                        }
                    )
                }

                DaedalusOutlinedTextField(
                    value = weight.value,
                    onValueChange = {
                        if (weightError.value != null) weightError.value = null
                        weight.value = it.filtered()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalSpacingM(),
                    label = stringResource(R.string.add_weight_weight_text_field_label),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Next
                    ),
                    supportingText = weightSupportingText,
                    isError = weightError.value != null
                )
                VerticalSpacerM()
                DaedalusOutlinedTextField(
                    value = note.value,
                    onValueChange = { note.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalSpacingM(),
                    label = stringResource(R.string.add_weight_note_text_field_label),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                )
                VerticalSpacerM()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalSpacingM(),
                    content = {
                        val locale = LocalConfiguration.current.locales[0]
                        Text(
                            text = stringResource(R.string.add_weight_date_label),
                            style = MaterialTheme.typography.bodySmall
                        )
                        WeightedSpacer()
                        Text(
                            text = selectedDate.value.asUserfacingString(locale, FormatStyle.SHORT),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                )
                WeightedSpacer()
                AddButton(
                    weight = weight.value,
                    note = note.value,
                    date = selectedDate.value,
                    viewModel = viewModel,
                    showSnackbar = showSnackbar,
                    onError = { weightError.value = it }
                )
                VerticalSpacerXS()
                DaedalusButton(
                    text = stringResource(R.string.add_weight_choose_date_button),
                    type = ButtonType.Outlined,
                    onClick = { showDialog.value = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalSpacingM()
                )
            }
        )
    }
}

@Composable
private fun AddButton(
    weight: String?,
    note: String?,
    date: Instant,
    viewModel: AddWeightViewModel,
    showSnackbar: suspend (message: String) -> Unit,
    onError: (WeightError) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val successMessage = stringResource(R.string.add_weight_add_success_message)
    val context = Dispatchers.IO

    DaedalusButton(
        text = stringResource(R.string.add_weight_add_button_text),
        type = ButtonType.Filled,
        onClick = {
            weight?.toFloatOrNull()?.let {
                coroutineScope.launch(context) {
                    viewModel.saveWeight(
                        weight = Weight(
                            value = it,
                            note = note?.takeIf { it.isNotBlank() },
                            dateTime = ZonedDateTime.ofInstant(date, ZoneId.systemDefault())
                        )
                    )

                    showSnackbar(successMessage)
                }
            } ?: run {
                if (weight.isNullOrEmpty()) {
                    onError(WeightError.Empty)
                } else {
                    onError(WeightError.Undefined)
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .horizontalSpacingM()
    )
}

@ExperimentalMaterial3Api
@Composable
private fun DatePicker(onDismiss: () -> Unit, onConfirm: (Long?) -> Unit) {
    val today = rememberSaveable { Instant.now().toEpochMilli() }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = today)
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            DaedalusButton(
                text = stringResource(R.string.add_weight_date_picker_button),
                type = ButtonType.Filled,
                onClick = { onConfirm(datePickerState.selectedDateMillis) }
            )
        },
        modifier = Modifier.horizontalSpacingM(),
        colors = daedalusDatePickerDialogColors(),
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        ),
        content = {
            DatePicker(
                state = datePickerState,
                colors = daedalusDatePickerDialogColors()
            )
        }
    )
}

private fun String.filtered(): String = filter { it.isDigit() || it == '.' || it == ',' }

private sealed class WeightError {
    object Empty : WeightError()
    object Undefined : WeightError()
}
