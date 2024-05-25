package com.nikitakrapo.trips.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.navigate
import com.nikitakrapo.birthdays.account.Account
import com.nikitakrapo.birthdays.account.AccountManager
import com.nikitakrapo.birthdays.account.isAuthorized
import com.nikitakrapo.trips.AuthorizationComponentImpl
import com.nikitakrapo.trips.mainscreen.MainComponentImpl
import com.nikitakrapo.trips.utils.coroutines.collectIn
import com.nikitakrapo.trips.utils.decompose.asStateFlow
import com.nikitakrapo.trips.utils.decompose.coroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable

class RootComponentImpl(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val coroutineScope = coroutineScope()

    private val accountManager: AccountManager by com.nikitakrapo.trips.di.Di.inject()

    private val navigation = StackNavigation<RootConfig>()

    override val stack: StateFlow<ChildStack<*, RootComponent.RootChild>> = childStack(
        key = "RootStack",
        source = navigation,
        serializer = RootConfig.serializer(),
        initialStack = {
            val config = if (accountManager.isAuthorized) RootConfig.Main else RootConfig.Authorization
            listOf(config)
        },
        childFactory = ::child,
    ).asStateFlow()

    init {
        accountManager.account
            .collectIn(coroutineScope, ::handleNewAccount)
    }

    private fun handleNewAccount(account: Account?) {
        if (account == null) {
            navigation.navigate { listOf(RootConfig.Authorization) }
        } else {
            navigation.navigate { listOf(RootConfig.Main) }
        }
    }

    private fun child(
        config: RootConfig,
        componentContext: ComponentContext
    ): RootComponent.RootChild {
        return when (config) {
            RootConfig.Main -> RootComponent.RootChild.Main(
                component = MainComponentImpl(componentContext)
            )
            RootConfig.Authorization -> RootComponent.RootChild.Authorization(
                component = AuthorizationComponentImpl(componentContext)
            )
        }
    }

    @Serializable
    private sealed interface RootConfig {
        @Serializable
        data object Main : RootConfig
        @Serializable
        data object Authorization : RootConfig
    }
}