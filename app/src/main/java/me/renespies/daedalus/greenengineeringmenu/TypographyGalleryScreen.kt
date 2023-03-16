package me.renespies.daedalus.greenengineeringmenu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import me.renespies.daedalus.R
import me.renespies.daedalus.compose.ToolbarContent
import me.renespies.daedalus.compose.VerticalSpacerM
import me.renespies.daedalus.compose.horizontalSpacingM
import me.renespies.daedalus.compose.verticalSpacingXXL
import me.renespies.daedalus.ui.theme.DaedalusTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypographyGalleryScreen(onBack: () -> Unit) {
    ToolbarContent(title = stringResource(R.string.green_engineering_menu_item_typography_title), onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .verticalSpacingXXL()
                .navigationBarsPadding(),
            content = {
                DisplayTexts()
                VerticalSpacerM()
                HeadlineTexts()
                VerticalSpacerM()
                TitleTexts()
                VerticalSpacerM()
                LabelTexts()
                VerticalSpacerM()
                BodyTexts()
            }
        )
    }
}

@Composable
private fun DisplayTexts() {
    Text(
        text = "displayLarge",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.displayLarge
    )
    VerticalSpacerM()
    Text(
        text = "displayMedium",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.displayMedium
    )
    VerticalSpacerM()
    Text(
        text = "displaySmall",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.displaySmall
    )
}

@Composable
private fun HeadlineTexts() {
    Text(
        text = "headlineLarge",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.headlineLarge
    )
    VerticalSpacerM()
    Text(
        text = "headlineMedium",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.headlineMedium
    )
    VerticalSpacerM()
    Text(
        text = "headlineSmall",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
private fun TitleTexts() {
    Text(
        text = "titleLarge",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.titleLarge
    )
    VerticalSpacerM()
    Text(
        text = "titleMedium",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.titleMedium
    )
    VerticalSpacerM()
    Text(
        text = "titleSmall",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.titleSmall
    )
}

@Composable
private fun LabelTexts() {
    Text(
        text = "labelLarge",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.labelLarge
    )
    VerticalSpacerM()
    Text(
        text = "labelMedium",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.labelMedium
    )
    VerticalSpacerM()
    Text(
        text = "labelSmall",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.labelSmall
    )
}

@Composable
private fun BodyTexts() {
    Text(
        text = "bodyLarge",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.bodyLarge
    )
    VerticalSpacerM()
    Text(
        text = "bodyMedium",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.bodyMedium
    )
    VerticalSpacerM()
    Text(
        text = "bodySmall",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.bodySmall
    )
}

private fun Modifier.typographyBorder() = composed {
    border(
        border = BorderStroke(
            width = Dp.Hairline,
            color = DaedalusTheme.colors.text
        )
    )
}
