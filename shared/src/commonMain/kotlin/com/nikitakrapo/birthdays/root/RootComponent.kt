package com.nikitakrapo.birthdays.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.nikitakrapo.birthdays.AuthorizationComponent
import com.nikitakrapo.birthdays.mainscreen.MainComponent
import kotlinx.coroutines.flow.StateFlow

/**
 * Application root screen
 */
interface RootComponent {

    val stackValue: Value<ChildStack<*, RootChild>>
    val stack: StateFlow<ChildStack<*, RootChild>>

    sealed class RootChild {
        class Main(val component: MainComponent) : RootChild()
        class Authorization(val component: AuthorizationComponent) : RootChild()
    }
}