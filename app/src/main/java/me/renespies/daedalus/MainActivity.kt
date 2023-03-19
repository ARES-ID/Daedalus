package me.renespies.daedalus

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import me.renespies.daedalus.navigation.NavigationHost
import me.renespies.daedalus.ui.theme.DaedalusTheme
import me.renespies.daedalus.ui.widgets.DaedalusSnackbar

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
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
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .systemBarsPadding(),
                            content = {
                                SnackbarHost(
                                    hostState = snackbarHostState,
                                    modifier = Modifier.align(Alignment.TopCenter),
                                    snackbar = { DaedalusSnackbar(it) }
                                )
                            }
                        )
                    },
                    content = { NavigationHost(navigationController, snackbarHostState) }
                )
            }
        }
    }
}
