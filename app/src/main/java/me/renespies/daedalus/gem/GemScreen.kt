package me.renespies.daedalus.gem

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.renespies.daedalus.compose.horizontalSpacingM
import me.renespies.daedalus.compose.verticalSpacingXXL

@Suppress("LongMethod")
@Composable
fun GemScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .verticalSpacingXXL()
            .navigationBarsPadding(),
        content = {
            Text(
                text = "h1",
                modifier = Modifier
                    .horizontalSpacingM()
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.Black
                        )
                    ),
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = "h2",
                modifier = Modifier
                    .horizontalSpacingM()
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.Black
                        )
                    ),
                style = MaterialTheme.typography.h2
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = "h3",
                modifier = Modifier
                    .horizontalSpacingM()
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.Black
                        )
                    ),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = "h4",
                modifier = Modifier
                    .horizontalSpacingM()
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.Black
                        )
                    ),
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = "h5",
                modifier = Modifier
                    .horizontalSpacingM()
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.Black
                        )
                    ),
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = "h6",
                modifier = Modifier
                    .horizontalSpacingM()
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.Black
                        )
                    ),
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = "subtitle1",
                modifier = Modifier
                    .horizontalSpacingM()
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.Black
                        )
                    ),
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = "subtitle2",
                modifier = Modifier
                    .horizontalSpacingM()
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.Black
                        )
                    ),
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = "body1",
                modifier = Modifier
                    .horizontalSpacingM()
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.Black
                        )
                    ),
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = "body2",
                modifier = Modifier
                    .horizontalSpacingM()
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.Black
                        )
                    ),
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = "button",
                modifier = Modifier
                    .horizontalSpacingM()
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.Black
                        )
                    ),
                style = MaterialTheme.typography.button
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = "caption",
                modifier = Modifier
                    .horizontalSpacingM()
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.Black
                        )
                    ),
                style = MaterialTheme.typography.caption
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = "overline",
                modifier = Modifier
                    .horizontalSpacingM()
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.Black
                        )
                    ),
                style = MaterialTheme.typography.overline
            )
        }
    )
}
