package com.nikitakrapo.trips.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.nikitakrapo.trips.design.theme.TripsTheme
import com.nikitakrapo.trips.root.RootComponentImpl
import com.nikitakrapo.trips.root.RootScreen

class MainActivity : ComponentActivity() {

    private val componentContext by lazy { defaultComponentContext() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TripsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = TripsTheme.colorScheme.background
                ) {
                    val rootComponent = remember {
                        RootComponentImpl(componentContext = componentContext)
                    }
                    RootScreen(
                        rootComponent = rootComponent,
                    )
                }
            }
        }
    }
}
