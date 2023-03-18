package me.renespies.daedalus.compose

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Divider
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
import androidx.compose.ui.unit.Dp
import me.renespies.daedalus.BuildConfig
import me.renespies.daedalus.R
import me.renespies.daedalus.ui.theme.DaedalusTheme
import me.renespies.daedalus.ui.theme.Spacings
import me.renespies.daedalus.ui.theme.daedalusTopAppBarColors

fun Modifier.verticalSpacingXXL() = padding(vertical = Spacings.XXL)
fun Modifier.verticalSpacingM() = padding(vertical = Spacings.M)
fun Modifier.horizontalSpacingM() = padding(horizontal = Spacings.M)

@Composable
fun VerticalSpacerXL() = Spacer(modifier = Modifier.height(Spacings.XL))

@Composable
fun VerticalSpacerM() = Spacer(modifier = Modifier.height(Spacings.M))

@Composable
fun HorizontalSpacerM() = Spacer(modifier = Modifier.width(Spacings.M))

@Composable
fun Divider() = Divider(
    thickness = Dp.Hairline,
    color = MaterialTheme.colorScheme.primary.copy(alpha = .3f)
)

inline fun <T> LazyListScope.tableItems(
    items: List<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    me.renespies.daedalus.compose.Divider()
    itemContent(items[it])
    if (it >= items.lastIndex) me.renespies.daedalus.compose.Divider()
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
        containerColor = DaedalusTheme.colors.background,
        topBar = {
            TopAppBar(
                colors = daedalusTopAppBarColors(),
                title = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        content = {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBack,
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
