package me.renespies.daedalus.weight.weighthistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import me.renespies.daedalus.compose.ToolbarContent
import me.renespies.daedalus.compose.tableItems
import java.time.ZonedDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeightHistoryScreen(
    onBack: () -> Unit,
    viewModel: WeightHistoryViewModel = viewModel(factory = WeightHistoryViewModel.Factory)
) {
    ToolbarContent(title = "Verlauf", onBack = onBack) {
        val weights by viewModel.weights.collectAsState()
        LazyColumn {
            tableItems(
                items = weights,
                key = { it.id },
                itemContent = {
                    Column {
                        Text(text = it.weight.toString())
                        Text(text = it.dateTime.userfacingString)
                    }
                }
            )
        }
    }
}

val ZonedDateTime.userfacingString: String
    get() = "$year-$monthValue-$dayOfYear $hour:$minute:$second"
