package com.rjspies.daedalus.weight.weighthistory

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.rjspies.daedalus.R
import com.rjspies.daedalus.compose.ToolbarContent
import com.rjspies.daedalus.compose.tableItems
import com.rjspies.daedalus.data.data.Weight
import com.rjspies.daedalus.ui.horizontalSpacingM
import com.rjspies.daedalus.ui.theme.DaedalusTheme
import com.rjspies.daedalus.ui.theme.Spacings
import com.rjspies.daedalus.ui.verticalSpacingM
import com.rjspies.daedalus.ui.widgets.ButtonType
import com.rjspies.daedalus.ui.widgets.DaedalusButton
import com.rjspies.daedalus.ui.widgets.EmptyScreen
import org.koin.androidx.compose.koinViewModel
import java.text.DecimalFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeightHistoryScreen(onBack: () -> Unit) {
    ToolbarContent(title = stringResource(R.string.weight_history_toolbar_title), onBack = onBack) {
        val viewModel: WeightHistoryViewModel = koinViewModel()
        val weights by viewModel.weights.collectAsState()

        if (weights.isNotEmpty()) {
            Weights(
                weights = weights,
                viewModel = viewModel,
            )
        } else {
            EmptyScreen(
                icon = painterResource(R.drawable.icon_list_24),
                contentDescription = stringResource(R.string.extensions_content_description_list),
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
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Weights(
    weights: List<Weight>,
    viewModel: WeightHistoryViewModel,
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
                        weights.getOrNull(index + 1)
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
                        DaedalusButton(
                            text = stringResource(R.string.weight_history_dialog_delete_item_button),
                            type = ButtonType.Filled,
                            onClick = {
                                coroutineScope.launch {
                                    viewModel.deleteWeight(weight)
                                }
                            },
                        )
                    },
                    titleContentColor = DaedalusTheme.colors.text,
                    textContentColor = DaedalusTheme.colors.text,
                    containerColor = DaedalusTheme.colors.background,
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
                        painter = painterResource(R.drawable.icon_delete_24),
                        contentDescription = stringResource(R.string.extensions_content_description_delete_action),
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
            val contentDescription = when (state) {
                ArrowState.Neutral -> stringResource(R.string.extensions_content_description_trending_flat)
                ArrowState.Positive -> stringResource(R.string.extensions_content_description_trending_down)
                ArrowState.Negative -> stringResource(R.string.extensions_content_description_trending_up)
            }

            Icon(
                painter = painterResource(state.iconResource),
                contentDescription = contentDescription,
                modifier = Modifier.padding(Spacings.S),
            )
        },
    )
}

private sealed class ArrowState(
    @DrawableRes val iconResource: Int,
) {
    data object Neutral : ArrowState(R.drawable.icon_trending_flat_24)
    data object Negative : ArrowState(R.drawable.icon_trending_down_24)
    data object Positive : ArrowState(R.drawable.icon_trending_up_24)
}

private fun Float.asUserfacingString(locale: Locale): String {
    return "${DecimalFormat.getInstance(locale).format(this)} kg"
}

private fun ZonedDateTime.asUserfacingString(
    locale: Locale,
    style: FormatStyle = FormatStyle.MEDIUM,
): String {
    return format(DateTimeFormatter.ofLocalizedDateTime(style).withLocale(locale))
}

fun Instant.asUserfacingString(
    locale: Locale,
    style: FormatStyle = FormatStyle.MEDIUM,
): String {
    val zone = ZoneId.systemDefault()
    val formatter = DateTimeFormatter.ofLocalizedDate(style)
    return atZone(zone).toLocalDate().format(formatter.withLocale(locale))
}
