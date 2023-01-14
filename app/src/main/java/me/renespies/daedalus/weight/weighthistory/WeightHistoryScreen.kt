package me.renespies.daedalus.weight.weighthistory

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import me.renespies.daedalus.compose.ToolbarContent

@Composable
fun WeightHistoryScreen(
    onBack: () -> Unit,
    viewModel: WeightHistoryViewModel = viewModel(factory = WeightHistoryViewModel.Factory)
) {
    ToolbarContent(title = "Weight history", onBack = onBack) {
        Column {
            val weights by viewModel.weights.collectAsState()
            weights.forEach {
                Text(text = it.weight.toString())
            }
        }
    }
}
