package com.rjspies.daedalus.weight.addweight

import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.DialogProperties
import com.rjspies.daedalus.R
import com.rjspies.daedalus.compose.ToolbarContent
import com.rjspies.daedalus.compose.WeightedSpacer
import com.rjspies.daedalus.ui.VerticalSpacerM
import com.rjspies.daedalus.ui.VerticalSpacerXS
import com.rjspies.daedalus.ui.horizontalSpacingM
import com.rjspies.daedalus.ui.theme.Spacings
import com.rjspies.daedalus.ui.theme.daedalusDatePickerDialogColors
import com.rjspies.daedalus.ui.verticalSpacingM
import com.rjspies.daedalus.ui.widgets.ButtonType
import com.rjspies.daedalus.ui.widgets.DaedalusButton
import com.rjspies.daedalus.ui.widgets.DaedalusOutlinedTextField
import com.rjspies.daedalus.weight.service.data.Weight
import com.rjspies.daedalus.weight.weighthistory.asUserfacingString
import org.koin.androidx.compose.koinViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.FormatStyle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

@Suppress("LongMethod")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWeightScreen(
    onBack: () -> Unit,
    showSnackbar: suspend (message: String) -> Unit,
) {
    ToolbarContent(title = stringResource(R.string.add_weight_toolbar_title), onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .verticalSpacingM(),
            content = {
                val viewModel: AddWeightViewModel = koinViewModel()
                val weightError = viewModel.weightError.collectAsState()
                val weight = viewModel.weight.collectAsState()
                val note = viewModel.note.collectAsState()
                val showDialog = viewModel.shouldShowBanner.collectAsState()
                val selectedDate = viewModel.selectedDate.collectAsState()
                val weightSupportingText = when (weightError.value) {
                    is WeightError.Undefined -> stringResource(R.string.add_weight_weight_text_field_error_message)
                    else -> stringResource(R.string.add_weight_weight_text_field_supporting_message)
                }

                if (showDialog.value) {
                    DatePicker(
                        onDismiss = { viewModel.setShouldShowBanner(false) },
                        onConfirm = { selectedTimestamp ->
                            selectedTimestamp?.let {
                                viewModel.setSelectedDate(Instant.ofEpochMilli(it))
                            }
                            viewModel.setShouldShowBanner(false)
                        },
                    )
                }

                DaedalusOutlinedTextField(
                    value = weight.value,
                    onValueChange = {
                        viewModel.resetWeightError()
                        viewModel.setWeight(it.filtered())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalSpacingM(),
                    label = stringResource(R.string.add_weight_weight_text_field_label),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Next,
                    ),
                    supportingText = weightSupportingText,
                    isError = weightError.value != WeightError.None,
                )
                VerticalSpacerM()
                DaedalusOutlinedTextField(
                    value = note.value,
                    onValueChange = viewModel::setNote,
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalSpacingM(),
                    label = stringResource(R.string.add_weight_note_text_field_label),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
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
                            style = MaterialTheme.typography.bodySmall,
                        )
                        WeightedSpacer()
                        Text(
                            text = selectedDate.value.asUserfacingString(locale, FormatStyle.SHORT),
                            style = MaterialTheme.typography.bodySmall,
                        )
                    },
                )
                WeightedSpacer()
                Column(
                    modifier = Modifier
                        .padding(top = Spacings.M)
                        .imePadding(),
                ) {
                    AddButton(
                        weight = weight.value,
                        note = note.value,
                        date = selectedDate.value,
                        viewModel = viewModel,
                        showSnackbar = showSnackbar,
                        onError = { viewModel.setWeightError(it) },
                    )
                    VerticalSpacerXS()
                    DaedalusButton(
                        text = stringResource(R.string.add_weight_choose_date_button),
                        type = ButtonType.Outlined,
                        onClick = { viewModel.setShouldShowBanner(true) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalSpacingM(),
                    )
                }
            },
        )
    }
}

@Composable
private fun AddButton(
    weight: String?,
    note: String?,
    date: Instant,
    viewModel: AddWeightViewModel,
    context: CoroutineDispatcher = Dispatchers.IO,
    showSnackbar: suspend (message: String) -> Unit,
    onError: (WeightError) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val successMessage = stringResource(R.string.add_weight_add_success_message)

    DaedalusButton(
        text = stringResource(R.string.add_weight_add_button_text),
        type = ButtonType.Filled,
        onClick = {
            weight?.parseToFloat()?.let {
                coroutineScope.launch(context) {
                    viewModel.saveWeight(
                        weight = Weight(
                            value = it,
                            note = note?.takeIf { it.isNotBlank() },
                            dateTime = ZonedDateTime.ofInstant(date, ZoneId.systemDefault()),
                        ),
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
            .horizontalSpacingM(),
    )
}

@ExperimentalMaterial3Api
@Composable
private fun DatePicker(
    onDismiss: () -> Unit,
    onConfirm: (Long?) -> Unit,
) {
    val today = rememberSaveable { Instant.now().toEpochMilli() }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = today)
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            DaedalusButton(
                text = stringResource(R.string.add_weight_date_picker_button),
                type = ButtonType.Filled,
                onClick = { onConfirm(datePickerState.selectedDateMillis) },
            )
        },
        modifier = Modifier.horizontalSpacingM(),
        colors = daedalusDatePickerDialogColors(),
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false,
        ),
        content = {
            DatePicker(
                state = datePickerState,
                colors = daedalusDatePickerDialogColors(),
            )
        },
    )
}

private fun String.filtered(): String = filter { it.isDigit() || it == '.' || it == ',' }
private fun String?.parseToFloat(): Float? = this?.replace(",", ".")?.toFloatOrNull()

@Parcelize
sealed class WeightError : Parcelable {
    @Parcelize
    data object None : WeightError()

    @Parcelize
    data object Empty : WeightError()

    @Parcelize
    data object Undefined : WeightError()
}