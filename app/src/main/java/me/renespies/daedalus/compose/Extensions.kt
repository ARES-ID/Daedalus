package me.renespies.daedalus.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.renespies.daedalus.ui.theme.Spacings

fun Modifier.verticalSpacingXXL() = padding(vertical = Spacings.XXL)
fun Modifier.horizontalSpacingM() = padding(horizontal = Spacings.M)

@Composable
fun VerticalSpacerXL() {
    Spacer(modifier = Modifier.height(Spacings.XL))
}
