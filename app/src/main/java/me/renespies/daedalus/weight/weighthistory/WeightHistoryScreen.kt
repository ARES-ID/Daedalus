package me.renespies.daedalus.weight.weighthistory

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FormatListBulleted
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import me.renespies.daedalus.R
import me.renespies.daedalus.compose.ToolbarContent
import me.renespies.daedalus.compose.VerticalSpacerM
import me.renespies.daedalus.compose.horizontalSpacingM
import me.renespies.daedalus.compose.tableItems
import me.renespies.daedalus.compose.verticalSpacingM
import me.renespies.daedalus.ui.theme.DaedalusTheme
import me.renespies.daedalus.ui.theme.Spacings
import me.renespies.daedalus.weight.service.data.Weight
import java.text.DecimalFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeightHistoryScreen(onBack: () -> Unit) {
    ToolbarContent(title = stringResource(R.string.weight_history_toolbar_title), onBack = onBack) {
        val viewModel: WeightHistoryViewModel = viewModel(factory = WeightHistoryViewModel.Factory)
        val weights by viewModel.weights.collectAsState()

        if (weights.isNotEmpty()) {
            Weights(
                weights = weights,
                viewModel = viewModel
            )
        } else {
            EmptyScreen()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Weights(
    weights: List<Weight>,
    viewModel: WeightHistoryViewModel
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = Spacings.M),
        content = {
            tableItems(
                items = weights,
                key = { it.id },
                itemContent = {
                    val predecessor = remember(weights) {
                        val index = weights.indexOf(it)
                        weights.getOrNull(index - 1)
                    }
                    val state = when {
                        predecessor?.value == null -> ArrowState.Neutral
                        it.value > predecessor.value -> ArrowState.Negative
                        it.value < predecessor.value -> ArrowState.Positive
                        else -> ArrowState.Neutral
                    }
                    WeightRow(
                        weight = it,
                        state = state,
                        viewModel = viewModel,
                        modifier = Modifier.animateItemPlacement()
                    )
                }
            )
        }
    )
}

@Composable
private fun WeightRow(
    weight: Weight,
    state: ArrowState,
    viewModel: WeightHistoryViewModel,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        content = {
            val (avatar, title, date, deleteButton) = createRefs()
            val locale = LocalConfiguration.current.locales[0]
            val coroutineScope = rememberCoroutineScope()

            Avatar(
                state = state,
                modifier = Modifier.constrainAs(avatar) {
                    top.linkTo(parent.top, margin = Spacings.M)
                    start.linkTo(parent.start, margin = Spacings.M)
                    bottom.linkTo(parent.bottom, margin = Spacings.M)
                }
            )
            Text(
                text = weight.value.asUserfacingString(locale),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.constrainAs(title) {
                    width = Dimension.fillToConstraints
                    top.linkTo(parent.top, margin = Spacings.M)
                    start.linkTo(avatar.end, margin = Spacings.M)
                    end.linkTo(deleteButton.start, margin = Spacings.M)
                }
            )
            Text(
                text = weight.dateTime.asUserfacingString(locale),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.constrainAs(date) {
                    width = Dimension.fillToConstraints
                    top.linkTo(title.bottom)
                    start.linkTo(avatar.end, margin = Spacings.M)
                    bottom.linkTo(parent.bottom, margin = Spacings.M)
                    end.linkTo(deleteButton.start, margin = Spacings.M)
                }
            )
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        viewModel.deleteWeight(weight)
                    }
                },
                modifier = Modifier.constrainAs(deleteButton) {
                    top.linkTo(parent.top, margin = Spacings.M)
                    end.linkTo(parent.end, margin = Spacings.M)
                    bottom.linkTo(parent.bottom, margin = Spacings.M)
                },
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = stringResource(R.string.extensions_content_description_delete_action)
                    )
                }
            )
        }
    )
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

@Composable
private fun EmptyScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .verticalSpacingM()
            .horizontalSpacingM(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {
            Icon(
                imageVector = Icons.Outlined.FormatListBulleted,
                contentDescription = stringResource(R.string.extensions_content_description_list),
                modifier = Modifier.size(48.dp)
            )
            VerticalSpacerM()
            Text(
                text = stringResource(R.string.weight_history_empty_screen_title),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.weight_history_empty_screen_description),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    )
}

private enum class ArrowState {
    Neutral,
    Positive,
    Negative
}

private fun Float.asUserfacingString(locale: Locale): String {
    return "${DecimalFormat.getInstance(locale).format(this)} kg"
}

private fun ZonedDateTime.asUserfacingString(locale: Locale, style: FormatStyle = FormatStyle.MEDIUM): String {
    return format(DateTimeFormatter.ofLocalizedDateTime(style).withLocale(locale))
}

fun Instant.asUserfacingString(locale: Locale, style: FormatStyle = FormatStyle.MEDIUM): String {
    val zone = ZoneId.systemDefault()
    val formatter = DateTimeFormatter.ofLocalizedDate(style)
    return atZone(zone).toLocalDate().format(formatter.withLocale(locale))
}
