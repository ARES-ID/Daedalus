package me.renespies.daedalus.greenengineeringmenu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import me.renespies.daedalus.R
import me.renespies.daedalus.compose.ToolbarContent
import me.renespies.daedalus.compose.horizontalSpacingM
import me.renespies.daedalus.compose.verticalSpacingXXL
import me.renespies.daedalus.navigation.Route
import me.renespies.daedalus.ui.theme.Spacings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreenEngineeringMenuScreen(onBack: () -> Unit, navigateToItem: (Route) -> Unit) {
    ToolbarContent(title = stringResource(R.string.green_engineering_menu_title), onBack = onBack) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .verticalSpacingXXL()
                .navigationBarsPadding()
                .horizontalSpacingM(),
            verticalArrangement = Arrangement.spacedBy(Spacings.XS),
            content = {
                items(
                    items = GreenEngineeringMenuItems.values(),
                    key = { it.name },
                    itemContent = { GreenEngineeringMenuEntry(it, navigateToItem) }
                )
            }
        )
    }
}

@Composable
fun GreenEngineeringMenuEntry(
    item: GreenEngineeringMenuItems,
    onNavigation: (Route) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onNavigation(item.route)
            }
    ) {
        Text(
            text = item.name,
            modifier = Modifier.padding(Spacings.M),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
