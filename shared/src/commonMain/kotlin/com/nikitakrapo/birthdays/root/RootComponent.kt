package com.nikitakrapo.birthdays.root

import androidx.compose.runtime.Stable
import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.birthdays.AuthorizationComponent
import com.nikitakrapo.birthdays.mainscreen.MainComponent
import kotlinx.coroutines.flow.StateFlow

/**
 * Application root screen
 */
@Stable
interface RootComponent {

    val stack: StateFlow<ChildStack<*, RootChild>>

    sealed class RootChild {
        class Main(val component: MainComponent) : RootChild()
        class Authorization(val component: AuthorizationComponent) : RootChild()
    }
}