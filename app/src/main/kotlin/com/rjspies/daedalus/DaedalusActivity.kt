package com.rjspies.daedalus

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AddChart
import androidx.compose.material.icons.rounded.AreaChart
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.animation.doOnEnd
import com.rjspies.daedalus.data.data.Weight
import com.rjspies.daedalus.navigation.NavigationHost
import com.rjspies.daedalus.ui.VerticalSpacerM
import com.rjspies.daedalus.ui.common.ANIMATION_DURATION_MILLISECONDS
import com.rjspies.daedalus.ui.horizontalSpacingM
import com.rjspies.daedalus.ui.theme.DaedalusTheme
import com.rjspies.daedalus.weight.addweight.AddWeightViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinContext

class DaedalusActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Suppress("LongMethod")
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Daedalus)
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        splashScreen.animateExit()

        setContent {
            KoinContext {
                DaedalusTheme {
                    val snackbarHostState = remember { SnackbarHostState() }
                    var showDialog by remember { mutableStateOf(false) }
                    val addWeightViewModel: AddWeightViewModel = koinViewModel()
                    var weight by remember { mutableFloatStateOf(0f) }
                    var note by remember { mutableStateOf<String?>(null) }

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            confirmButton = {
                                TextButton(onClick = { addWeightViewModel.saveWeight(Weight(value = weight, note = note)) }) {
                                    Text(text = stringResource(R.string.add_weight_add_button_text))
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { showDialog = false }) {
                                    Text(text = "Abbrechen")
                                }
                            },
                            icon = {
                                Icon(imageVector = Icons.Rounded.AddChart, contentDescription = null)
                            },
                            title = {
                                Text(text = stringResource(R.string.add_weight_toolbar_title))
                            },
                            text = {
                                Column {
                                    OutlinedTextField(
                                        value = weight.toString(),
                                        onValueChange = { weight = it.toFloat() },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .horizontalSpacingM(),
                                        label = {
                                            Text(text = stringResource(R.string.add_weight_weight_text_field_label))
                                        },
                                        singleLine = true,
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Decimal,
                                            imeAction = ImeAction.Next,
                                        ),
                                        supportingText = {
                                            Text(text = stringResource(R.string.add_weight_weight_text_field_supporting_message))
                                        },
                                        isError = false,
                                    )
                                    VerticalSpacerM()
                                    OutlinedTextField(
                                        value = note ?: "",
                                        onValueChange = { note = it },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .horizontalSpacingM(),
                                        label = {
                                            Text(text = stringResource(R.string.add_weight_note_text_field_label))
                                        },
                                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                    )
                                }
                            },
                        )
                    }

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        snackbarHost = {
                            SnackbarHost(
                                hostState = snackbarHostState,
                                modifier = Modifier.imePadding(),
                                snackbar = { Snackbar(it) },
                            )
                        },
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "Title")
                                },
                            )
                        },
                        bottomBar = {
                            NavigationBar {
                                NavigationBarItem(
                                    selected = false, onClick = { /*TODO*/ },
                                    icon = {
                                        Icon(imageVector = Icons.Rounded.AreaChart, contentDescription = null)
                                    },
                                    label = {
                                        Text(text = "Grafik")
                                    },
                                )
                                NavigationBarItem(
                                    selected = false, onClick = { /*TODO*/ },
                                    icon = {
                                        Icon(imageVector = Icons.AutoMirrored.Rounded.List, contentDescription = null)
                                    },
                                    label = {
                                        Text(text = "Historie")
                                    },
                                )
                                NavigationBarItem(
                                    selected = false, onClick = { /*TODO*/ },
                                    icon = {
                                        Icon(imageVector = Icons.Rounded.Info, contentDescription = null)
                                    },
                                    label = {
                                        Text(text = "Info")
                                    },
                                )
                            }
                        },
                        floatingActionButton = {
                            FloatingActionButton(onClick = { showDialog = !showDialog }) {
                                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
                            }
                        },
                        content = {
                            Box(Modifier.padding(it)) {
                                VerticalSpacerM()
                                NavigationHost(snackbarHostState)
                            }
                        },
                    )
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun SplashScreen.animateExit() {
        setOnExitAnimationListener { view ->
            val slideDownAnimation = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f)
            slideDownAnimation.interpolator = LinearInterpolator()
            slideDownAnimation.duration = ANIMATION_DURATION_MILLISECONDS.toLong()
            slideDownAnimation.doOnEnd { view.remove() }
            slideDownAnimation.start()
        }
    }
}
