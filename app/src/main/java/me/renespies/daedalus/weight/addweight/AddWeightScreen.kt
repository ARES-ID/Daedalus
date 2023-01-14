package me.renespies.daedalus.weight.addweight

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
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
import me.renespies.daedalus.weight.service.data.Weight

@Composable
fun AddWeightScreen(
    onBack: () -> Unit,
    viewModel: AddWeightViewModel = viewModel(factory = AddWeightViewModel.Factory),
) {
    ToolbarContent(title = "Add weight", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalSpacingXXL(),
            content = {
                val weight = remember { mutableStateOf<String?>(null) }
                val note = remember { mutableStateOf<String?>(null) }
                val coroutineScope = rememberCoroutineScope()

                suspend fun saveWeight(weight: Int, note: String?) {
                    viewModel.saveWeight(
                        weight = Weight(
                            weight = weight,
                            note = note
                        )
                    )
                }

                OutlinedTextField(
                    value = weight.value ?: "",
                    onValueChange = { weight.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalSpacingM(),
                    label = { Text("Weight") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(Spacings.M))
                OutlinedTextField(
                    value = note.value ?: "",
                    onValueChange = { note.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalSpacingM(),
                    label = { Text("Note") },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                )
                Button(
                    onClick = {
                        weight.value?.toInt()?.let {
                            coroutineScope.launch(Dispatchers.IO) {
                                saveWeight(it, note.value)
                            }
                        }
                    },
                    content = { Text("Add", style = MaterialTheme.typography.button) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalSpacingM()
                )
            }
        )
    }
}
