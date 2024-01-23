package com.banklannister.marsrover

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.banklannister.marsrover.nav.NavCompose
import com.banklannister.marsrover.ui.theme.MarsRoverTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarsRoverTheme {
                NavCompose()
            }
        }
    }
}

