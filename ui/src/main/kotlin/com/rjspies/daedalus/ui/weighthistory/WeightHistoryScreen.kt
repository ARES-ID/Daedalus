package com.rjspies.daedalus.ui.weighthistory

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.automirrored.rounded.TrendingDown
import androidx.compose.material.icons.automirrored.rounded.TrendingFlat
import androidx.compose.material.icons.automirrored.rounded.TrendingUp
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.rjspies.daedalus.data.data.Weight
import com.rjspies.daedalus.ui.R
import com.rjspies.daedalus.ui.common.horizontalSpacingM
import com.rjspies.daedalus.ui.common.tableItems
import com.rjspies.daedalus.ui.common.theme.Spacings
import com.rjspies.daedalus.ui.common.verticalSpacingM
import com.rjspies.daedalus.ui.common.widgets.EmptyScreen
import org.koin.androidx.compose.koinViewModel
import java.text.DecimalFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale
import kotlinx.coroutines.launch

@Composable
internal fun WeightHistoryScreen() {
    val viewModel: WeightHistoryViewModel = koinViewModel()
    val weights by viewModel.weights.collectAsState()

    if (weights.isNotEmpty()) {
        Weights(
            weights = weights,
            viewModel = viewModel,
        )
    } else {
        EmptyScreen(
            icon = rememberVectorPainter(Icons.AutoMirrored.Rounded.List),
            contentDescription = "",
            title = stringResource(R.string.weight_history_empty_screen_title),
            subtitle = stringResource(R.string.weight_history_empty_screen_subtitle),
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .verticalSpacingM()
                .horizontalSpacingM(),
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Weights(
    weights: List<Weight>,
    viewModel: WeightHistoryViewModel,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {
            tableItems(
                items = weights,
                key = { it.id },
                itemContent = {
                    val predecessor = remember(weights) {
                        val index = weights.indexOf(it)
                        weights.getOrNull(index + 1)
                    }
                    val state = when {
                        predecessor?.value == null -> ArrowState.Neutral
                        it.value > predecessor.value -> ArrowState.Upwards
                        it.value < predecessor.value -> ArrowState.Downwards
                        else -> ArrowState.Neutral
                    }
                    WeightRow(
                        weight = it,
                        state = state,
                        viewModel = viewModel,
                        modifier = Modifier.animateItemPlacement(),
                    )
                },
            )
        },
    )
}

@Suppress("LongMethod")
@Composable
private fun WeightRow(
    weight: Weight,
    state: ArrowState,
    viewModel: WeightHistoryViewModel,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        content = {
            val (avatar, title, date, noteReference, deleteButton) = createRefs()
            val locale = LocalConfiguration.current.locales[0]
            val coroutineScope = rememberCoroutineScope()
            val note = weight.note
            val showDeletePrompt = rememberSaveable { mutableStateOf(false) }

            if (showDeletePrompt.value) {
                AlertDialog(
                    onDismissRequest = { showDeletePrompt.value = false },
                    title = {
                        Text(stringResource(R.string.weight_history_dialog_delete_item_title))
                    },
                    text = {
                        Text(
                            text = stringResource(R.string.weight_history_dialog_delete_item_message),
                            style = MaterialTheme.typography.bodySmall,
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    viewModel.deleteWeight(weight)
                                }
                            },
                            content = {
                                Text(text = stringResource(R.string.weight_history_dialog_delete_item_button))
                            },
                        )
                    },
                )
            }

            Avatar(
                state = state,
                modifier = Modifier.constrainAs(avatar) {
                    top.linkTo(parent.top, margin = Spacings.M)
                    start.linkTo(parent.start, margin = Spacings.M)
                    bottom.linkTo(parent.bottom, margin = Spacings.M)
                },
            )
            Text(
                text = weight.value.asUserfacingString(locale),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.constrainAs(title) {
                    width = Dimension.fillToConstraints
                    top.linkTo(parent.top, margin = Spacings.M)
                    start.linkTo(avatar.end, margin = Spacings.M)
                    end.linkTo(deleteButton.start, margin = Spacings.M)
                },
            )
            Text(
                text = weight.dateTime.asUserfacingString(locale),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.constrainAs(date) {
                    width = Dimension.fillToConstraints
                    top.linkTo(title.bottom)
                    start.linkTo(avatar.end, margin = Spacings.M)
                    end.linkTo(deleteButton.start, margin = Spacings.M)

                    if (note == null) {
                        bottom.linkTo(parent.bottom, margin = Spacings.M)
                    }
                },
            )

            if (note != null) {
                Text(
                    text = stringResource(R.string.weight_history_item_note, note),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.constrainAs(noteReference) {
                        width = Dimension.fillToConstraints
                        top.linkTo(date.bottom)
                        start.linkTo(avatar.end, margin = Spacings.M)
                        bottom.linkTo(parent.bottom, margin = Spacings.M)
                        end.linkTo(deleteButton.start, margin = Spacings.M)
                    },
                )
            }

            IconButton(
                onClick = {
                    showDeletePrompt.value = true
                },
                modifier = Modifier.constrainAs(deleteButton) {
                    top.linkTo(parent.top, margin = Spacings.M)
                    end.linkTo(parent.end, margin = Spacings.M)
                    bottom.linkTo(parent.bottom, margin = Spacings.M)
                },
                content = {
                    Icon(
                        imageVector = Icons.Rounded.DeleteOutline,
                        contentDescription = "",
                    )
                },
            )
        },
    )
}

@Composable
private fun Avatar(
    state: ArrowState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.Gray.copy(alpha = .3f))
            .then(modifier),
        content = {
//            val contentDescription = when (state) {
//                ArrowState.Neutral -> stringResource(R.string.extensions_content_description_trending_flat)
//                ArrowState.Upwards -> stringResource(R.string.extensions_content_description_trending_up)
//                ArrowState.Downwards -> stringResource(R.string.extensions_content_description_trending_down)
//            }

            Icon(
                imageVector = state.vector,
                contentDescription = "",
                modifier = Modifier.padding(Spacings.S),
            )
        },
    )
}

private sealed class ArrowState(val vector: ImageVector) {
    data object Neutral : ArrowState(Icons.AutoMirrored.Rounded.TrendingFlat)
    data object Downwards : ArrowState(Icons.AutoMirrored.Rounded.TrendingDown)
    data object Upwards : ArrowState(Icons.AutoMirrored.Rounded.TrendingUp)
}

private fun Float.asUserfacingString(locale: Locale): String {
    return "${DecimalFormat.getInstance(locale).format(this)} kg"
}

private fun ZonedDateTime.asUserfacingString(
    locale: Locale,
    style: FormatStyle = FormatStyle.MEDIUM,
) = format(DateTimeFormatter.ofLocalizedDateTime(style).withLocale(locale))
