package me.renespies.daedalus.greenengineeringmenu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.renespies.daedalus.compose.ToolbarContent
import me.renespies.daedalus.compose.VerticalSpacerM
import me.renespies.daedalus.compose.horizontalSpacingM
import me.renespies.daedalus.compose.verticalSpacingXXL
import me.renespies.daedalus.ui.widgets.FilledButton
import me.renespies.daedalus.ui.widgets.OutlinedButton
import me.renespies.daedalus.ui.widgets.TransparentButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonGalleryScreen(onBack: () -> Unit) {
    ToolbarContent(title = "Buttons", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .verticalSpacingXXL(),
            content = {
                FilledButton(
                    text = "Filled button",
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalSpacingM()
                )
                VerticalSpacerM()
                OutlinedButton(
                    text = "Outlined button",
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalSpacingM()
                )
                VerticalSpacerM()
                TransparentButton(
                    text = "Transparent button",
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalSpacingM()
                )
            }
        )
    }
}
