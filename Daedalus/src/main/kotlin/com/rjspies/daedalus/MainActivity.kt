package com.rjspies.daedalus

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.rjspies.daedalus.navigation.NavigationHost
import com.rjspies.daedalus.ui.theme.DaedalusTheme
import com.rjspies.daedalus.ui.widgets.DaedalusSnackbar

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            DaedalusTheme {
                val navigationController = rememberNavController()
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
                    content = { NavigationHost(navigationController, snackbarHostState) },
                )
            }
        }
    }
}
