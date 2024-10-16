package com.nikitakrapo.birthdays

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import com.nikitakrapo.birthdays.AuthorizationComponent.AuthorizationChild
import com.nikitakrapo.birthdays.landing.AuthLandingComponentImpl
import com.nikitakrapo.birthdays.login.LoginComponentImpl
import com.nikitakrapo.birthdays.registration.RegistrationComponentImpl
import com.nikitakrapo.birthdays.utils.decompose.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable

class AuthorizationComponentImpl(
    componentContext: ComponentContext,
) : AuthorizationComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<AuthorizationConfig>()

    override val childValue: Value<ChildStack<*, AuthorizationChild>> = childStack(
        key = "AuthorizationStack",
        source = navigation,
        handleBackButton = true,
        serializer = AuthorizationConfig.serializer(),
        initialStack = { listOf(AuthorizationConfig.Landing) },
        childFactory = ::child,
    )
    override val child: StateFlow<ChildStack<*, AuthorizationChild>> = childValue.asStateFlow()

    override fun onBackClicked() {
        navigation.pop()
    }

    private fun child(
        config: AuthorizationConfig,
        componentContext: ComponentContext,
    ): AuthorizationChild {
        return when (config) {
            AuthorizationConfig.Landing -> AuthorizationChild.Landing(
                component = AuthLandingComponentImpl(
                    componentContext = componentContext,
                    navigateToLogin = {
                        navigation.bringToFront(AuthorizationConfig.Login)
                    },
                )
            )
            AuthorizationConfig.Login -> AuthorizationChild.Login(
                component = LoginComponentImpl(
                    componentContext = componentContext,
                    navigateToRegistration = {
                        navigation.bringToFront(AuthorizationConfig.Registration)
                    }
                )
            )
            AuthorizationConfig.Registration -> AuthorizationChild.Registration(
                component = RegistrationComponentImpl(
                    componentContext = componentContext,
                ),
            )
        }
    }

    @Serializable
    private sealed interface AuthorizationConfig {
        @Serializable
        data object Landing : AuthorizationConfig
        @Serializable
        data object Login : AuthorizationConfig
        @Serializable
        data object Registration : AuthorizationConfig
    }
}