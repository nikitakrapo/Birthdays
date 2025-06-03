package com.nikitakrapo.birthdays.mainscreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import com.nikitakrapo.birthdays.AddBirthdayComponentImpl
import com.nikitakrapo.birthdays.di.Di
import com.nikitakrapo.birthdays.feed.BirthdaysFeedComponentImpl
import com.nikitakrapo.birthdays.feed.BirthdaysFeedRefreshTrigger
import com.nikitakrapo.birthdays.model.ProfileInfo
import com.nikitakrapo.birthdays.profile.ProfileComponentImpl
import com.nikitakrapo.birthdays.profile.ProfileEditComponentImpl
import com.nikitakrapo.birthdays.repositories.birthdays.BirthdaysRepository
import com.nikitakrapo.birthdays.utils.decompose.asStateFlow
import com.nikitakrapo.birthdays.wizard.WizardComponentImpl
import kotlinx.serialization.Serializable

class MainComponentImpl(
    componentContext: ComponentContext,
) : MainComponent, ComponentContext by componentContext {

    private val feedRefreshTrigger = BirthdaysFeedRefreshTrigger()

    private val navigation = StackNavigation<MainConfig>()

    override val stackValue: Value<ChildStack<*, MainComponent.MainChild>> = childStack(
        key = "MainStack",
        source = navigation,
        serializer = MainConfig.serializer(),
        initialStack = { listOf(MainConfig.BirthdaysFeed) },
        childFactory = ::child,
        handleBackButton = true,
    )
    override val stack = stackValue.asStateFlow()

    override fun onFeedClicked() {
        navigation.bringToFront(MainConfig.BirthdaysFeed)
    }

    override fun onWishlistClicked() {
        navigation.bringToFront(MainConfig.Wishlist)
    }

    override fun onProfileClicked() {
        navigation.bringToFront(MainConfig.Profile)
    }

    private fun child(config: MainConfig, componentContext: ComponentContext): MainComponent.MainChild {
        return when (config) {
            MainConfig.BirthdaysFeed -> MainComponent.MainChild.BottomBarChild.BirthdaysFeed(
                component = BirthdaysFeedComponentImpl(
                    componentContext = componentContext,
                    refreshTrigger = feedRefreshTrigger,
                    openAddBirthday = {
                        navigation.bringToFront(MainConfig.AddBirthday)
                    }
                ),
            )
            MainConfig.AddBirthday -> MainComponent.MainChild.AddBirthday(
                component = AddBirthdayComponentImpl(
                    componentContext = componentContext,
                    repository = Di.inject<BirthdaysRepository>().value,
                    closeScreen = {
                        feedRefreshTrigger.triggerRefresh()
                        navigation.pop()
                    },
                ),
            )
            MainConfig.Wishlist -> MainComponent.MainChild.BottomBarChild.Wishlist(
                component = Unit,
            )
            MainConfig.Profile -> MainComponent.MainChild.BottomBarChild.Profile(
                component = ProfileComponentImpl(
                    componentContext = componentContext,
                    navigateToProfileEdit = { profileInfo ->
                        navigation.bringToFront(MainConfig.ProfileEdit(profileInfo))
                    }
                ),
            )
            is MainConfig.ProfileEdit -> MainComponent.MainChild.ProfileEdit(
                component = ProfileEditComponentImpl(
                    componentContext = componentContext,
                    initialProfileInfo = config.profileInfo,
                    onProfileUpdated = { navigation.pop() },
                    navigateBack = { navigation.pop() },
                ),
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
        data object AddBirthday : MainConfig

        @Serializable
        data object Wishlist : MainConfig

        @Serializable
        data object Profile : MainConfig

        @Serializable
        data class ProfileEdit(
            val profileInfo: ProfileInfo,
        ) : MainConfig

        @Serializable
        data object Wizard : MainConfig
    }
}