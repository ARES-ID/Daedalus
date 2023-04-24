package me.renespies.daedalus.weight.weighthistory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import me.renespies.daedalus.compose.ToolbarContent
import me.renespies.daedalus.compose.tableItems
import me.renespies.daedalus.ui.theme.DaedalusTheme
import me.renespies.daedalus.ui.theme.Spacings
import me.renespies.daedalus.weight.service.data.Weight
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

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
                    val predecessor = remember(weights) {
                        val index = weights.indexOf(it)
                        weights.getOrNull(index - 1)
                    }
                    val state = when {
                        predecessor?.weight == null -> ArrowState.Neutral
                        it.weight > predecessor.weight -> ArrowState.Negative
                        it.weight < predecessor.weight -> ArrowState.Positive
                        else -> ArrowState.Neutral
                    }
                    WeightRow(weight = it, state)
                }
            )
        }
    }
}

@Composable
private fun WeightRow(weight: Weight, state: ArrowState) {
    ConstraintLayout(Modifier.fillMaxWidth()) {
        val (avatar, title, date) = createRefs()
        Avatar(
            state = state,
            modifier = Modifier.constrainAs(avatar) {
                top.linkTo(parent.top, margin = Spacings.M)
                start.linkTo(parent.start, margin = Spacings.M)
                bottom.linkTo(parent.bottom, margin = Spacings.M)
            }
        )
        Text(
            text = weight.weight.userfacingString,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.constrainAs(title) {
                width = Dimension.fillToConstraints
                top.linkTo(parent.top, margin = Spacings.M)
                start.linkTo(avatar.end, margin = Spacings.M)
                end.linkTo(parent.end, margin = Spacings.M)
            }
        )
        Text(
            text = weight.dateTime.userfacingString,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.constrainAs(date) {
                width = Dimension.fillToConstraints
                top.linkTo(title.bottom)
                start.linkTo(avatar.end, margin = Spacings.M)
                bottom.linkTo(parent.bottom, margin = Spacings.M)
                end.linkTo(parent.end, margin = Spacings.M)
            }
        )
    }
}

@Composable
private fun Avatar(
    state: ArrowState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.Gray.copy(alpha = .3f))
            .then(modifier),
        content = {
            val color = when (state) {
                ArrowState.Neutral -> DaedalusTheme.colors.text
                ArrowState.Positive -> DaedalusTheme.colors.positive
                ArrowState.Negative -> DaedalusTheme.colors.negative
            }
            val icon = when (state) {
                ArrowState.Neutral -> Icons.Outlined.Remove
                ArrowState.Positive -> Icons.Outlined.ArrowDownward
                ArrowState.Negative -> Icons.Outlined.ArrowUpward
            }

            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.padding(Spacings.S),
                tint = color
            )
        }
    )
}

private enum class ArrowState {
    Neutral,
    Positive,
    Negative
}

val Float.userfacingString: String
    get() = "${toString()} kg"

val ZonedDateTime.userfacingString: String
    get() = format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))
