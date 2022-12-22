package me.renespies.daedalus.addweight

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.renespies.daedalus.ui.theme.DaedalusTypography
import me.renespies.daedalus.ui.theme.VerticalSpacerXL
import me.renespies.daedalus.ui.theme.horizontalSpacingM
import me.renespies.daedalus.ui.theme.verticalSpacingXXL

@Composable
fun AddWeightScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .verticalSpacingXXL()
    ) {
        Text(
            text = "Gewichtsmessung",
            modifier = Modifier.horizontalSpacingM(),
            style = DaedalusTypography.headlineMedium,
        )
        VerticalSpacerXL()
    }
}
