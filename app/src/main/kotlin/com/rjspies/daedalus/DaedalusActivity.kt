package com.rjspies.daedalus

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.animation.doOnEnd
import com.rjspies.daedalus.ui.common.ANIMATION_DURATION_MILLISECONDS
import com.rjspies.daedalus.ui.main.MainScreen
import com.rjspies.daedalus.ui.theme.DaedalusTheme
import org.koin.compose.KoinContext

class DaedalusActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Daedalus)
        enableEdgeToEdge()
        splashScreen.animateExit()

        super.onCreate(savedInstanceState)

        setContent {
            KoinContext {
                DaedalusTheme {
                    MainScreen()
                }
            }
        }
    }

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
