package com.rjspies.daedalus.compose

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.rjspies.daedalus.BuildConfig
import com.rjspies.daedalus.R

@Composable
fun RowScope.WeightedSpacer(weight: Float = 1f) = Spacer(modifier = Modifier.weight(weight))

@Composable
fun ColumnScope.WeightedSpacer(weight: Float = 1f) = Spacer(modifier = Modifier.weight(weight))

@Composable
fun Divider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        thickness = Dp.Hairline,
        modifier = modifier,
    )
}

fun Modifier.greenEngineeringMenuGestureDetector(
    vararg keys: Any?,
    onDetection: () -> Unit,
): Modifier {
    return if (BuildConfig.DEBUG) {
        pointerInput(keys) {
            detectTapGestures(onDoubleTap = { onDetection() })
        }
    } else {
        this
    }
}

@ExperimentalMaterial3Api
@Composable
fun ToolbarContent(
    title: String,
    onBack: () -> Unit,
    content: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = stringResource(R.string.extensions_content_description_toolbar_navigation_icon),
                            )
                        },
                    )
                },
            )
        },
        content = {
            Box(Modifier.padding(it)) {
                content()
            }
        },
    )
}
