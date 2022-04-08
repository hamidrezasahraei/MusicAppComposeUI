package me.hamsah.musiccompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import me.hamsah.musiccompose.ui.theme.MusicComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicComposeTheme {
                MainScreen()
            }
        }
    }
}