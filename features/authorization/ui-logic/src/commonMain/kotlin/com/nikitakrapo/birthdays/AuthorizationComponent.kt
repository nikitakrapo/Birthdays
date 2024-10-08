package com.nikitakrapo.birthdays

import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.birthdays.login.LoginComponent
import com.nikitakrapo.birthdays.registration.RegistrationComponent
import kotlinx.coroutines.flow.StateFlow

interface AuthorizationComponent {

    val child: StateFlow<ChildStack<*, AuthorizationChild>>

    sealed interface AuthorizationChild {
        class Login(val component: LoginComponent) : AuthorizationChild
        class Registration(val component: RegistrationComponent) : AuthorizationChild
    }
}