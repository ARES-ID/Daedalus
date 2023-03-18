package me.renespies.daedalus.weight.addweight

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.renespies.daedalus.compose.ToolbarContent
import me.renespies.daedalus.compose.horizontalSpacingM
import me.renespies.daedalus.compose.verticalSpacingXXL
import me.renespies.daedalus.ui.theme.Spacings
import me.renespies.daedalus.ui.widgets.DaedalusOutlinedTextField
import me.renespies.daedalus.weight.service.data.Weight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWeightScreen(
    onBack: () -> Unit,
    viewModel: AddWeightViewModel = viewModel(factory = AddWeightViewModel.Factory),
) {
    ToolbarContent(title = "Messung hinzufügen", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalSpacingXXL(),
            content = {
                val weight = remember { mutableStateOf<String?>(null) }
                val note = remember { mutableStateOf<String?>(null) }
                val coroutineScope = rememberCoroutineScope()

                suspend fun saveWeight(weight: Float, note: String?) {
                    viewModel.saveWeight(
                        weight = Weight(
                            weight = weight,
                            note = note
                        )
                    )
                }

                DaedalusOutlinedTextField(
                    value = weight.value ?: "",
                    onValueChange = { weight.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalSpacingM(),
                    label = "Gewicht",
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(Spacings.M))
                DaedalusOutlinedTextField(
                    value = note.value ?: "",
                    onValueChange = { note.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalSpacingM(),
                    label = "Notiz",
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                )
                Button(
                    onClick = {
                        weight.value?.toFloatOrNull()?.let {
                            coroutineScope.launch(Dispatchers.IO) {
                                saveWeight(it, note.value)
                            }
                        }
                    },
                    content = { Text("Add") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalSpacingM()
                )
            }
        )
    }
}
