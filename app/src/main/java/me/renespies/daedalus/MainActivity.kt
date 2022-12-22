package me.renespies.daedalus

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.Modifier
import me.renespies.daedalus.ui.theme.DaedalusTheme
import me.renespies.daedalus.weightgraph.WeightGraphScreen

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaedalusTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = DaedalusTheme.colors.background,
                    floatingActionButton = {
                        ExtendedFloatingActionButton(
                            text = {
                                Text(
                                    text = "Add weight",
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = null,
                                    tint = DaedalusTheme.colors.text,
                                )
                            },
                            onClick = { /*TODO*/ },
                        )
                    },
                    content = { WeightGraphScreen() },
                )
            }
        }
    }
}
