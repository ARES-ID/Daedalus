package me.renespies.daedalus.gem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.renespies.daedalus.compose.horizontalSpacingM
import me.renespies.daedalus.compose.verticalSpacingXXL
import me.renespies.daedalus.ui.theme.DaedalusTypography
import me.renespies.daedalus.ui.theme.Spacings

@Composable
fun GemScreen() {
    val values = DaedalusTypography.values()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .verticalSpacingXXL(),
        verticalArrangement = Arrangement.spacedBy(Spacings.S)
    ) {
        items(
            values,
            key = { it.hashCode() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalSpacingM()
            ) {
                Text(text = it.first, style = it.second)
            }
        }
    }
}
