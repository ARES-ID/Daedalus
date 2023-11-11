package com.rjspies.daedalus.greenengineeringmenu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rjspies.daedalus.compose.ToolbarContent
import com.rjspies.daedalus.ui.VerticalSpacerM
import com.rjspies.daedalus.ui.horizontalSpacingM
import com.rjspies.daedalus.ui.verticalSpacingXXL
import com.rjspies.daedalus.ui.widgets.ButtonType
import com.rjspies.daedalus.ui.widgets.DaedalusButton

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
                DaedalusButton(
                    text = "Filled button",
                    type = ButtonType.Filled,
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalSpacingM(),
                )
                VerticalSpacerM()
                DaedalusButton(
                    text = "Outlined button",
                    type = ButtonType.Outlined,
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalSpacingM(),
                )
                VerticalSpacerM()
                DaedalusButton(
                    text = "Transparent button",
                    type = ButtonType.Transparent,
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalSpacingM(),
                )
            },
        )
    }
}
