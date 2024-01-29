package com.nikitakrapo.trips.root

import androidx.compose.runtime.Stable
import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.trips.mainscreen.MainComponent
import kotlinx.coroutines.flow.StateFlow

/**
 * Application root screen
 */
@Stable
interface RootComponent {

    val stack: StateFlow<ChildStack<*, RootChild>>

    sealed class RootChild {
        class Main(val component: MainComponent) : RootChild()
        data object Login : RootChild()
    }
}