package com.nikitakrapo.trips.mainscreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.nikitakrapo.trips.bottomnav.BottomNavigationComponent
import com.nikitakrapo.trips.bottomnav.BottomNavigationComponentImpl
import com.nikitakrapo.trips.bottomnav.BottomNavigationItem
import com.nikitakrapo.trips.coroutines.collectIn
import com.nikitakrapo.trips.decompose.asStateFlow
import com.nikitakrapo.trips.decompose.coroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable

class MainComponentImpl(
    componentContext: ComponentContext
) : MainComponent, ComponentContext by componentContext {

    private val componentScope = coroutineScope()

    private val navigation = StackNavigation<MainConfig>()

    override val stack: StateFlow<ChildStack<*, MainComponent.MainChild>> = childStack(
        key = "MainStack",
        source = navigation,
        serializer = MainConfig.serializer(),
        initialStack = { listOf(MainConfig.Trips) },
        childFactory = ::child,
    ).asStateFlow()

    override val bottomNavigationComponent: BottomNavigationComponent =
        BottomNavigationComponentImpl(
            items = listOf(BottomNavigationItem.Trips, BottomNavigationItem.Profile),
            selectedItem = 0,
        )

    init {
        bottomNavigationComponent.state.collectIn(componentScope) {
            when (it.selectedNavigationItem) {
                BottomNavigationItem.Profile -> navigation.bringToFront(MainConfig.Profile)
                BottomNavigationItem.Trips -> navigation.bringToFront(MainConfig.Trips)
            }
        }
    }

    private fun child(config: MainConfig, componentContext: ComponentContext): MainComponent.MainChild {
        return when (config) {
            MainConfig.Trips -> MainComponent.MainChild.Trips
            MainConfig.Profile -> MainComponent.MainChild.Profile
        }
    }

    @Serializable
    private sealed interface MainConfig {
        @Serializable
        data object Trips : MainConfig

        @Serializable
        data object Profile : MainConfig
    }
}