package com.nikitakrapo.trips.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.nikitakrapo.trips.decompose.asStateFlow
import com.nikitakrapo.trips.mainscreen.MainComponentImpl
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable

class RootComponentImpl(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<RootConfig>()

    override val stack: StateFlow<ChildStack<*, RootComponent.RootChild>> = childStack(
        key = "RootStack",
        source = navigation,
        serializer = RootConfig.serializer(),
        initialStack = { listOf(RootConfig.Main) },
        childFactory = ::child,
    ).asStateFlow()

    private fun child(
        config: RootConfig,
        componentContext: ComponentContext
    ): RootComponent.RootChild {
        return when (config) {
            RootConfig.Main -> RootComponent.RootChild.Main(
                component = MainComponentImpl(componentContext)
            )
        }
    }

    @Serializable
    private sealed interface RootConfig {
        @Serializable
        data object Main : RootConfig
    }
}