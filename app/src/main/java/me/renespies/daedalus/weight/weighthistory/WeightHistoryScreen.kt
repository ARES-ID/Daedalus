package me.renespies.daedalus.weight.weighthistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import me.renespies.daedalus.compose.ToolbarContent
import me.renespies.daedalus.ui.theme.Spacings
import java.time.ZonedDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeightHistoryScreen(
    onBack: () -> Unit,
    viewModel: WeightHistoryViewModel = viewModel(factory = WeightHistoryViewModel.Factory)
) {
    ToolbarContent(title = "Weight history", onBack = onBack) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            val scope = rememberCoroutineScope()
            Button(onClick = {
                scope.launch {
                    viewModel.clearWeights()
                }
            }) {
                Text(text = "clear")
            }
            val weights by viewModel.weights.collectAsState()
            weights.forEach {
                Card(Modifier.padding(Spacings.M)) {
                    Column {
                        Text(text = it.weight.toString())
                        Text(text = it.dateTime.userfacingString)
                    }
                }
            }
        }
    }
}

val ZonedDateTime.userfacingString: String
    get() = "$year-$monthValue-$dayOfYear $hour:$minute:$second"
