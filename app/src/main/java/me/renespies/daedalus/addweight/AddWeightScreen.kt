package me.renespies.daedalus.addweight

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.renespies.daedalus.compose.VerticalSpacerXL
import me.renespies.daedalus.compose.horizontalSpacingM
import me.renespies.daedalus.compose.verticalSpacingXXL
import me.renespies.daedalus.ui.theme.DaedalusTypography

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
