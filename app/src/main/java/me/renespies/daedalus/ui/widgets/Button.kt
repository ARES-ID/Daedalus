package me.renespies.daedalus.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.renespies.daedalus.ui.theme.DaedalusTheme
import me.renespies.daedalus.ui.theme.Spacings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaedalusButton(
    text: String,
    type: ButtonType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val background = when (type) {
        ButtonType.Filled -> DaedalusTheme.colors.primary
        ButtonType.Outlined,
        ButtonType.Transparent -> Color.Transparent
    }
    val contentColor = when (type) {
        ButtonType.Filled -> DaedalusTheme.colors.onPrimary
        ButtonType.Outlined,
        ButtonType.Transparent -> DaedalusTheme.colors.text
    }
    val borderStroke = when (type) {
        ButtonType.Outlined -> BorderStroke(
            width = 1.dp,
            color = DaedalusTheme.colors.primary
        )
        ButtonType.Filled,
        ButtonType.Transparent -> null
    }

    Surface(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(percent = 100),
        color = background,
        contentColor = contentColor,
        border = borderStroke,
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
                content = {
                    Text(
                        text = text,
                        modifier = Modifier.padding(Spacings.S),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            )
        }
    )
}

enum class ButtonType {
    Filled,
    Outlined,
    Transparent
}
