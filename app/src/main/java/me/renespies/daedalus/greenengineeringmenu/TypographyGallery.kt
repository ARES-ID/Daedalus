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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp
import me.renespies.daedalus.compose.horizontalSpacingM
import me.renespies.daedalus.compose.verticalSpacingXXL
import me.renespies.daedalus.ui.theme.Spacings

@Composable
fun TypographyGallery() {
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
                style = MaterialTheme.typography.button
            )
            Spacer(modifier = Modifier.height(Spacings.M))
            Text(
                text = "caption",
                modifier = Modifier
                    .horizontalSpacingM()
                    .typographyBorder(),
                style = MaterialTheme.typography.caption
            )
            Spacer(modifier = Modifier.height(Spacings.M))
            Text(
                text = "overline",
                modifier = Modifier
                    .horizontalSpacingM()
                    .typographyBorder(),
                style = MaterialTheme.typography.overline
            )
        }
    )
}

@Composable
private fun HeadlineTexts() {
    Text(
        text = "h1",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.h1
    )
    Spacer(modifier = Modifier.height(Spacings.M))
    Text(
        text = "h2",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.h2
    )
    Spacer(modifier = Modifier.height(Spacings.M))
    Text(
        text = "h3",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.h3
    )
    Spacer(modifier = Modifier.height(Spacings.M))
    Text(
        text = "h4",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.h4
    )
    Spacer(modifier = Modifier.height(Spacings.M))
    Text(
        text = "h5",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.h5
    )
    Spacer(modifier = Modifier.height(Spacings.M))
    Text(
        text = "h6",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.h6
    )
}

@Composable
private fun SubtitleTexts() {
    Text(
        text = "subtitle1",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.subtitle1
    )
    Spacer(modifier = Modifier.height(Spacings.M))
    Text(
        text = "subtitle2",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.subtitle2
    )
}

@Composable
private fun BodyTexts() {
    Text(
        text = "body1",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.body1
    )
    Spacer(modifier = Modifier.height(Spacings.M))
    Text(
        text = "body2",
        modifier = Modifier
            .horizontalSpacingM()
            .typographyBorder(),
        style = MaterialTheme.typography.body2
    )
}

private fun Modifier.typographyBorder() = composed {
    border(
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.onBackground
        )
    )
}
