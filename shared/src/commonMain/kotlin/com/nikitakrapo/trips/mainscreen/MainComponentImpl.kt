package com.nikitakrapo.trips.mainscreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.nikitakrapo.birthdays.wizard.WizardComponentImpl
import com.nikitakrapo.trips.feed.TripsFeedComponentImpl
import com.nikitakrapo.trips.profile.ProfileComponentImpl
import com.nikitakrapo.trips.utils.decompose.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable

class MainComponentImpl(
    componentContext: ComponentContext
) : MainComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<MainConfig>()

    override val stack: StateFlow<ChildStack<*, MainComponent.MainChild>> = childStack(
        key = "MainStack",
        source = navigation,
        serializer = MainConfig.serializer(),
        initialStack = { listOf(MainConfig.BirthdaysFeed) },
        childFactory = ::child,
        handleBackButton = true,
    ).asStateFlow()

    override fun onFeedClicked() {
        navigation.bringToFront(MainConfig.BirthdaysFeed)
    }

    override fun onTripsClicked() {
        navigation.bringToFront(MainConfig.TripsFeed)
    }

    override fun onProfileClicked() {
        navigation.bringToFront(MainConfig.Profile)
    }

    private fun child(config: MainConfig, componentContext: ComponentContext): MainComponent.MainChild {
        return when (config) {
            MainConfig.BirthdaysFeed -> MainComponent.MainChild.BottomBarChild.BirthdaysFeed(
                component = Unit,
            )
            MainConfig.TripsFeed -> MainComponent.MainChild.BottomBarChild.TripsFeed(
                component = TripsFeedComponentImpl(componentContext = componentContext),
            )
            MainConfig.Profile -> MainComponent.MainChild.BottomBarChild.Profile(
                component = ProfileComponentImpl(componentContext = componentContext),
            )
            MainConfig.Wizard -> MainComponent.MainChild.Wizard(
                component = WizardComponentImpl(componentContext = componentContext),
            )
        }
    }

    @Serializable
    private sealed interface MainConfig {

        @Serializable
        data object BirthdaysFeed : MainConfig

        @Serializable
        data object TripsFeed : MainConfig

        @Serializable
        data object Profile : MainConfig

        @Serializable
        data object Wizard : MainConfig
    }
}