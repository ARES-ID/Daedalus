package me.renespies.daedalus.compose

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import me.renespies.daedalus.BuildConfig
import me.renespies.daedalus.R
import me.renespies.daedalus.ui.theme.Spacings

fun Modifier.verticalSpacingXXL() = padding(vertical = Spacings.XXL)
fun Modifier.horizontalSpacingM() = padding(horizontal = Spacings.M)

@Composable
fun VerticalSpacerXL() {
    Spacer(modifier = Modifier.height(Spacings.XL))
}

fun Modifier.greenEngineeringMenuGestureDetector(vararg keys: Any?, onDetection: () -> Unit): Modifier {
    return if (BuildConfig.DEBUG) {
        pointerInput(keys) {
            detectTapGestures(onDoubleTap = { onDetection() })
        }
    } else this
}

@Composable
fun ToolbarContent(title: String, onBack: () -> Unit, content: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h6
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        content = {
                            Icon(
                                imageVector = Icons.TwoTone.ArrowBack,
                                contentDescription = stringResource(R.string.extensions_content_description_toolbar_navigation_icon),
                            )
                        }
                    )
                }
            )
        },
        content = content,
    )
}
