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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AreaChart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import com.rjspies.daedalus.navigation.NavigationHost
import com.rjspies.daedalus.ui.common.ANIMATION_DURATION_MILLISECONDS
import com.rjspies.daedalus.ui.theme.DaedalusTheme
import com.rjspies.daedalus.ui.widgets.DaedalusSnackbar
import org.koin.compose.KoinContext

class DaedalusActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Suppress("LongMethod")
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Daedalus)
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.animateExit()
        }

        setContent {
            KoinContext {
                DaedalusTheme {
                    val snackbarHostState = remember { SnackbarHostState() }
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = DaedalusTheme.colors.background,
                        snackbarHost = {
                            SnackbarHost(
                                hostState = snackbarHostState,
                                modifier = Modifier.imePadding(),
                                snackbar = { DaedalusSnackbar(it) },
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
                            BottomAppBar(
                                floatingActionButton = {
                                    FloatingActionButton(onClick = { /*TODO*/ }) {
                                        Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
                                    }
                                },
                                actions = {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.Rounded.AreaChart,
                                            contentDescription = null,
                                        )
                                    }
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Rounded.List,
                                            contentDescription = null,
                                        )
                                    }
                                },
                            )
                        },
                        content = {
                            Box(Modifier.padding(it)) {
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
