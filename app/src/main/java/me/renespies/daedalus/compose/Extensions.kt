package me.renespies.daedalus.compose

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
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
import me.renespies.daedalus.BuildConfig
import me.renespies.daedalus.R
import me.renespies.daedalus.ui.theme.Spacings

fun Modifier.verticalSpacingXXL() = padding(vertical = Spacings.XXL)
fun Modifier.horizontalSpacingM() = padding(horizontal = Spacings.M)

@Composable
fun VerticalSpacerXL() {
    Spacer(modifier = Modifier.height(Spacings.XL))
}

@Composable
fun VerticalSpacerM() {
    Spacer(modifier = Modifier.height(Spacings.M))
}

fun Modifier.greenEngineeringMenuGestureDetector(vararg keys: Any?, onDetection: () -> Unit): Modifier {
    return if (BuildConfig.DEBUG) {
        pointerInput(keys) {
            detectTapGestures(onDoubleTap = { onDetection() })
        }
    } else this
}

@ExperimentalMaterial3Api
@Composable
fun ToolbarContent(title: String, onBack: () -> Unit, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall
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
        content = {
            Box(Modifier.padding(it)) {
                content()
            }
        },
    )
}
