package com.nikitakrapo.trips

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.nikitakrapo.trips.AuthorizationComponent.AuthorizationChild
import com.nikitakrapo.trips.login.LoginComponentImpl
import com.nikitakrapo.trips.registration.RegistrationComponentImpl
import com.nikitakrapo.trips.utils.decompose.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable

class AuthorizationComponentImpl(
    componentContext: ComponentContext,
) : AuthorizationComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<AuthorizationConfig>()

    override val child: StateFlow<ChildStack<*, AuthorizationChild>> = childStack(
        key = "AuthorizationStack",
        source = navigation,
        handleBackButton = true,
        serializer = AuthorizationConfig.serializer(),
        initialStack = { listOf(AuthorizationConfig.Login) },
        childFactory = ::child,
    ).asStateFlow()

    private fun child(
        config: AuthorizationConfig,
        componentContext: ComponentContext,
    ): AuthorizationChild {
        return when (config) {
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
                    navigateBack = {
                        navigation.pop()
                    }
                ),
            )
        }
    }

    @Serializable
    private sealed interface AuthorizationConfig {
        @Serializable
        data object Login : AuthorizationConfig
        @Serializable
        data object Registration : AuthorizationConfig
    }
}