package com.rjspies.daedalus

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.core.animation.doOnEnd
import com.rjspies.daedalus.ui.common.theme.DaedalusTheme
import com.rjspies.daedalus.ui.main.MainScreen
import org.koin.compose.KoinContext

private const val ANIMATION_DURATION = 250L

class DaedalusActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Daedalus)
        enableEdgeToEdge()
        splashScreen.animateExit()

        super.onCreate(savedInstanceState)

        setContent {
            KoinContext {
                DaedalusTheme {
                    StatusBar()
                    MainScreen()
                }
            }
        }
    }

    @Composable
    private fun StatusBar() {
        val backgroundColor = MaterialTheme.colorScheme.background.copy(alpha = .69f).toArgb()
        SideEffect {
            window.statusBarColor = backgroundColor
        }
    }

    private fun SplashScreen.animateExit() {
        setOnExitAnimationListener { view ->
            val slideDownAnimation = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f)
            slideDownAnimation.interpolator = LinearInterpolator()
            slideDownAnimation.duration = ANIMATION_DURATION
            slideDownAnimation.doOnEnd { view.remove() }
            slideDownAnimation.start()
        }
    }
}
