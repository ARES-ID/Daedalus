package me.renespies.daedalus.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import me.renespies.daedalus.ui.theme.Spacings
import me.renespies.daedalus.ui.widgets.FilledButton
import me.renespies.daedalus.ui.widgets.OutlinedButton
import me.renespies.daedalus.weight.weightgraph.WeightGraphScreen

@Composable
fun HomeScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding(),
        content = {
            val (title, graph, addWeight, history) = createRefs()

            Text(
                text = "Deine Statistik",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.constrainAs(title) {
                    width = Dimension.fillToConstraints
                    top.linkTo(parent.top, margin = Spacings.M)
                    start.linkTo(parent.start, margin = Spacings.M)
                    end.linkTo(parent.end, margin = Spacings.M)
                }
            )

            Box(
                modifier = Modifier.constrainAs(graph) {
                    width = Dimension.fillToConstraints
                    top.linkTo(title.bottom, Spacings.M)
                    start.linkTo(parent.start, Spacings.M)
                    end.linkTo(parent.end, Spacings.M)
                },
                content = {
                    WeightGraphScreen()
                }
            )
            FilledButton(
                text = "Gewicht hinzufügen",
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(addWeight) {
                        width = Dimension.fillToConstraints
                        start.linkTo(parent.start, margin = Spacings.M)
                        end.linkTo(parent.end, margin = Spacings.M)
                        bottom.linkTo(history.top, margin = Spacings.XXS)
                    }
            )
            OutlinedButton(
                text = "Verlauf ansehen",
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(history) {
                        width = Dimension.fillToConstraints
                        start.linkTo(parent.start, margin = Spacings.M)
                        end.linkTo(parent.end, margin = Spacings.M)
                        bottom.linkTo(parent.bottom, margin = Spacings.M)
                    }
            )
        }
    )
}
