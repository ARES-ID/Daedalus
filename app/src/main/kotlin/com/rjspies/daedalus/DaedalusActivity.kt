package com.rjspies.daedalus

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.view.WindowCompat
import com.rjspies.daedalus.navigation.NavigationHost
import com.rjspies.daedalus.ui.theme.DaedalusTheme
import com.rjspies.daedalus.ui.widgets.DaedalusSnackbar

private const val SPLASHSCREEN_EXIT_ANIMATION_START = 0f
private const val SPLASHSCREEN_EXIT_ANIMATION_DURATION = 200L

class DaedalusActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setTheme(R.style.Theme_Daedalus)
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.animateExit()
        }

        setContent {
            DaedalusTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = DaedalusTheme.colors.background,
                    snackbarHost = {
                        SnackbarHost(
                            hostState = snackbarHostState,
                            snackbar = { DaedalusSnackbar(it) },
                        )
                    },
                    content = { NavigationHost(snackbarHostState) },
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun SplashScreen.animateExit() {
        setOnExitAnimationListener { view ->
            val slideDownAnimation = ObjectAnimator.ofFloat(
                /* target = */ view,
                /* property = */ View.TRANSLATION_Y,
                /* ...values = */ 0f,
                view.height.toFloat(),
            )
            slideDownAnimation.interpolator = LinearInterpolator()
            slideDownAnimation.duration = SPLASHSCREEN_EXIT_ANIMATION_DURATION
            slideDownAnimation.doOnEnd { view.remove() }
            slideDownAnimation.start()
        }
    }
}
