package me.renespies.daedalus.weight.weighthistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import me.renespies.daedalus.compose.ToolbarContent
import me.renespies.daedalus.ui.theme.Spacings
import java.time.ZonedDateTime

@Composable
fun WeightHistoryScreen(
    onBack: () -> Unit,
    viewModel: WeightHistoryViewModel = viewModel(factory = WeightHistoryViewModel.Factory)
) {
    ToolbarContent(title = "Weight history", onBack = onBack) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
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
