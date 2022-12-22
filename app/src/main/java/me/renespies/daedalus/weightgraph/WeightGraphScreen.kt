package me.renespies.daedalus.weightgraph

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.renespies.daedalus.ui.theme.DaedalusTypography
import me.renespies.daedalus.compose.verticalSpacingXXL

@Composable
fun WeightGraphScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .verticalSpacingXXL()
    ) {
        Text(text = "Graph", style = DaedalusTypography.headlineMedium)
    }
}
