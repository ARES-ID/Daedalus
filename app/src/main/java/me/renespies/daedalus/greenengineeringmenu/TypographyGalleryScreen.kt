package me.renespies.daedalus.greenengineeringmenu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import me.renespies.daedalus.compose.horizontalSpacingM
import me.renespies.daedalus.compose.verticalSpacingXXL
import me.renespies.daedalus.ui.theme.Spacings

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
                HeadlineTexts()
                Spacer(modifier = Modifier.height(Spacings.M))
                SubtitleTexts()
                Spacer(modifier = Modifier.height(Spacings.M))
                BodyTexts()
                Spacer(modifier = Modifier.height(Spacings.M))
                Text(
                    text = "button",
                    modifier = Modifier
                        .horizontalSpacingM()
                        .typographyBorder(),
//                    style = MaterialTheme.typography.button TODO
                )
                Spacer(modifier = Modifier.height(Spacings.M))
                Text(
                    text = "caption",
                    modifier = Modifier
                        .horizontalSpacingM()
                        .typographyBorder(),
//                    style = MaterialTheme.typography.caption TODO
                )
                Spacer(modifier = Modifier.height(Spacings.M))
                Text(
                    text = "overline",
                    modifier = Modifier
                        .horizontalSpacingM()
                        .typographyBorder(),
//                    style = MaterialTheme.typography.overline TODO
                )
            }
        )
    }
}

@Composable
private fun HeadlineTexts() {
    Text(
        text = "h1",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
//        style = MaterialTheme.typography.h1 TODO
    )
    Spacer(modifier = Modifier.height(Spacings.M))
    Text(
        text = "h2",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
//        style = MaterialTheme.typography.h2 TODO
    )
    Spacer(modifier = Modifier.height(Spacings.M))
    Text(
        text = "h3",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
//        style = MaterialTheme.typography.h3 TODO
    )
    Spacer(modifier = Modifier.height(Spacings.M))
    Text(
        text = "h4",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
//        style = MaterialTheme.typography.h4 TODO
    )
    Spacer(modifier = Modifier.height(Spacings.M))
    Text(
        text = "h5",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
//        style = MaterialTheme.typography.h5 TODO
    )
    Spacer(modifier = Modifier.height(Spacings.M))
    Text(
        text = "h6",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
//        style = MaterialTheme.typography.h6 TODO
    )
}

@Composable
private fun SubtitleTexts() {
    Text(
        text = "subtitle1",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
//        style = MaterialTheme.typography.subtitle1 TODO
    )
    Spacer(modifier = Modifier.height(Spacings.M))
    Text(
        text = "subtitle2",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
////        style = MaterialTheme.typography.subtitle2 TODO
    )
}

@Composable
private fun BodyTexts() {
    Text(
        text = "body1",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
//        style = MaterialTheme.typography.body1 TODO
    )
    Spacer(modifier = Modifier.height(Spacings.M))
    Text(
        text = "body2",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
//        style = MaterialTheme.typography.body2 TODO
    )
}

private fun Modifier.typographyBorder() = composed {
    border(
        border = BorderStroke(
            width = Dp.Hairline,
            color = MaterialTheme.colorScheme.onBackground
        )
    )
}
