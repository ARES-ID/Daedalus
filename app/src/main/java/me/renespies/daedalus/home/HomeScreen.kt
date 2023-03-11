package me.renespies.daedalus.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import me.renespies.daedalus.R
import me.renespies.daedalus.compose.horizontalSpacingM
import me.renespies.daedalus.compose.verticalSpacingM
import me.renespies.daedalus.ui.theme.Spacings
import me.renespies.daedalus.ui.widgets.FilledButton
import me.renespies.daedalus.ui.widgets.OutlinedButton
import me.renespies.daedalus.weight.weightgraph.WeightGraphScreen

@Composable
fun HomeScreen(toAddWeight: () -> Unit, toWeightHistory: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding()
            .verticalSpacingM(),
        content = {
            Text(
                text = stringResource(id = R.string.home_title),
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalSpacingM()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalSpacingM(),
                content = { WeightGraphScreen() }
            )
            Spacer(modifier = Modifier.weight(1f))
            FilledButton(
                text = stringResource(id = R.string.home_add_weight_button_text),
                onClick = toAddWeight,
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalSpacingM()
                    .padding(top = Spacings.M)
            )
            OutlinedButton(
                text = stringResource(id = R.string.home_weight_history_button_text),
                onClick = toWeightHistory,
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalSpacingM()
                    .padding(top = Spacings.XS)
            )
        }
    )
}
