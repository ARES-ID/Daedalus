package me.renespies.daedalus.greenengineeringmenu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.renespies.daedalus.compose.horizontalSpacingM
import me.renespies.daedalus.compose.verticalSpacingXXL
import me.renespies.daedalus.ui.theme.Spacings

@Composable
fun GreenEngineeringMenuScreen(navigateToItem: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .verticalSpacingXXL()
            .navigationBarsPadding()
            .horizontalSpacingM(),
        verticalArrangement = Arrangement.spacedBy(Spacings.XS),
        content = {
            items(
                items = GreenEngineeringMenu.values(),
                key = { it.name },
                itemContent = { GreenEngineeringMenuEntry(it, navigateToItem) }
            )
        }
    )
}

@Composable
fun GreenEngineeringMenuEntry(
    item: GreenEngineeringMenu,
    onNavigation: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onNavigation)
    ) {
        Text(
            text = item.name,
            modifier = Modifier.padding(Spacings.M),
            style = MaterialTheme.typography.body1
        )
    }
}
