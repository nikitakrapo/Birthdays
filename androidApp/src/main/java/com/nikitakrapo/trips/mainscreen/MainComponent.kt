package com.nikitakrapo.trips.mainscreen

import androidx.compose.runtime.Stable
import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.trips.bottomnav.BottomNavigationComponent
import kotlinx.coroutines.flow.StateFlow

/**
 * Application's main screen. Basically is a bottom navigation host
 */
@Stable
interface MainComponent {

    val stack: StateFlow<ChildStack<*, MainChild>>

    val bottomNavigationComponent: BottomNavigationComponent

    sealed interface MainChild {
        data object Trips : MainChild
        data object Profile : MainChild
    }
}