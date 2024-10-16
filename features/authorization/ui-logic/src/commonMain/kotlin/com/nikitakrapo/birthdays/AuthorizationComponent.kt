package com.nikitakrapo.birthdays

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.nikitakrapo.birthdays.landing.AuthLandingComponent
import com.nikitakrapo.birthdays.login.LoginComponent
import com.nikitakrapo.birthdays.registration.RegistrationComponent
import kotlinx.coroutines.flow.StateFlow

interface AuthorizationComponent {

    val childValue: Value<ChildStack<*, AuthorizationChild>>
    val child: StateFlow<ChildStack<*, AuthorizationChild>>

    fun onBackClicked()

    sealed interface AuthorizationChild {
        class Landing(val component: AuthLandingComponent) : AuthorizationChild
        class Login(val component: LoginComponent) : AuthorizationChild
        class Registration(val component: RegistrationComponent) : AuthorizationChild
    }
}