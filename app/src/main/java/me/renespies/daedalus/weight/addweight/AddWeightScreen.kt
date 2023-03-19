package me.renespies.daedalus.weight.addweight

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.renespies.daedalus.R
import me.renespies.daedalus.compose.ToolbarContent
import me.renespies.daedalus.compose.VerticalSpacerM
import me.renespies.daedalus.compose.WeightedSpacer
import me.renespies.daedalus.compose.horizontalSpacingM
import me.renespies.daedalus.compose.verticalSpacingM
import me.renespies.daedalus.ui.widgets.ButtonType
import me.renespies.daedalus.ui.widgets.DaedalusButton
import me.renespies.daedalus.ui.widgets.DaedalusOutlinedTextField
import me.renespies.daedalus.weight.service.data.Weight

@Suppress("LongMethod")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWeightScreen(
    onBack: () -> Unit,
    viewModel: AddWeightViewModel = viewModel(factory = AddWeightViewModel.Factory),
) {
    ToolbarContent(title = stringResource(R.string.add_weight_toolbar_title), onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .verticalSpacingM(),
            content = {
                val weight = remember { mutableStateOf<String?>(null) }
                val weightError = remember { mutableStateOf<WeightError?>(null) }
                val note = remember { mutableStateOf<String?>(null) }
                val coroutineScope = rememberCoroutineScope()
                val weightSupportingText = when (weightError.value) {
                    is WeightError.Empty -> stringResource(R.string.add_weight_weight_text_field_supporting_message)
                    is WeightError.Undefined -> stringResource(R.string.add_weight_weight_text_field_error_message)
                    else -> stringResource(R.string.add_weight_weight_text_field_supporting_message)
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
                WeightedSpacer()
                AddButton {
                    weight.value?.toFloatOrNull()?.let {
                        coroutineScope.launch(Dispatchers.IO) {
                            viewModel.saveWeight(
                                weight = Weight(
                                    weight = it,
                                    note = note.value
                                )
                            )
                        }
                    } ?: run {
                        weightError.value = if (weight.value.isNullOrEmpty()) {
                            WeightError.Empty
                        } else WeightError.Undefined
                    }
                }
            }
        )
    }
}

@Composable
fun AddButton(onClick: () -> Unit) {
    DaedalusButton(
        text = stringResource(R.string.add_weight_add_button_text),
        type = ButtonType.Filled,
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .horizontalSpacingM()
    )
}

private fun String.filtered(): String = filter { it.isDigit() || it == '.' || it == ',' }

private sealed class WeightError {
    object Empty : WeightError()
    object Undefined : WeightError()
}
