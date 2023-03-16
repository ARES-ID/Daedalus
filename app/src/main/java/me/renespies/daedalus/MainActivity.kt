package me.renespies.daedalus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import me.renespies.daedalus.navigation.NavigationHost
import me.renespies.daedalus.ui.theme.DaedalusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val navigationController = rememberNavController()
            DaedalusTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DaedalusTheme.colors.background,
                    content = { NavigationHost(navigationController) },
                )
            }
        }
    }
}
