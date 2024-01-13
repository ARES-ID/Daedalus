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
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.animation.doOnEnd
import androidx.core.view.WindowCompat
import com.rjspies.daedalus.navigation.NavigationHost
import com.rjspies.daedalus.ui.common.ANIMATION_DURATION_MILLISECONDS
import com.rjspies.daedalus.ui.theme.DaedalusTheme
import com.rjspies.daedalus.ui.theme.Spacings
import com.rjspies.daedalus.ui.widgets.DaedalusSnackbar
import org.koin.compose.KoinContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

private const val SPLASHSCREEN_EXIT_ANIMATION_START = 0f

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
            KoinContext {
                DaedalusTheme {
                    val notificationManager = NotificationManagerImpl()
                    val notification by notificationManager.notification.collectAsState(null)
                    CompositionLocalProvider(LocalNotificationManager provides notificationManager) {
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

                        AnimatedVisibility(
                            visible = notification != null,
                            enter = fadeIn(
                                animationSpec = tween(
                                    durationMillis = ANIMATION_DURATION_MILLISECONDS,
                                    easing = LinearEasing,
                                ),
                            ),
                            exit = fadeOut(
                                animationSpec = tween(
                                    durationMillis = ANIMATION_DURATION_MILLISECONDS,
                                    easing = LinearEasing,
                                ),
                            ),
                            label = "Notification",
                        ) {
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .statusBarsPadding()
                                    .padding(Spacings.M),
                                color = Color.Gray,
                                contentColor = Color.White,
                                shadowElevation = 4.dp,
                                border = BorderStroke(1.dp, Color.Black),
                                onClick = notificationManager::reset,
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(Spacings.S),
                                ) {
                                    Text(
                                        text = notification?.title ?: "waaagh",
                                        modifier = Modifier.fillMaxWidth(),
                                        style = MaterialTheme.typography.titleSmall,
                                    )
                                    Text(
                                        text = notification?.description ?: "waaagh",
                                        modifier = Modifier.fillMaxWidth(),
                                        style = MaterialTheme.typography.labelSmall,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun SplashScreen.animateExit() {
        setOnExitAnimationListener { view ->
            val slideDownAnimation = ObjectAnimator.ofFloat(
                view,
                View.TRANSLATION_X,
                SPLASHSCREEN_EXIT_ANIMATION_START,
                -view.width.toFloat(),
            )
            slideDownAnimation.interpolator = LinearInterpolator()
            slideDownAnimation.duration = ANIMATION_DURATION_MILLISECONDS.toLong()
            slideDownAnimation.doOnEnd { view.remove() }
            slideDownAnimation.start()
        }
    }
}

val LocalNotificationManager = staticCompositionLocalOf<NotificationManager> { error("LocalNotifier not provided") }

interface NotificationManager {
    fun showNotification(title: String, description: String)
}

class NotificationManagerImpl : NotificationManager {
    private val _notification: MutableStateFlow<Notification?> = MutableStateFlow(null)
    val notification: Flow<Notification?>
        get() = _notification

    override fun showNotification(title: String, description: String) {
        _notification.value = Notification(title, description)
    }

    fun reset() {
        _notification.value = null
    }
}

data class Notification(
    val title: String,
    val description: String,
)
