package com.nikitakrapo.trips.mainscreen

import androidx.compose.runtime.Stable
import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.birthdays.wizard.WizardComponent
import com.nikitakrapo.trips.feed.TripsFeedComponent
import com.nikitakrapo.trips.profile.ProfileComponent
import kotlinx.coroutines.flow.StateFlow

/**
 * Application's main screen. Basically is a bottom navigation host
 */
@Stable
interface MainComponent {

    val stack: StateFlow<ChildStack<*, MainChild>>

    fun onFeedClicked()
    fun onTripsClicked()
    fun onProfileClicked()

    sealed class MainChild(
        val hasBottomSheet: Boolean,
    ) {
        class BirthdaysFeed(val component: Unit) : MainChild(hasBottomSheet = true)
        class TripsFeed(val component: TripsFeedComponent) : MainChild(hasBottomSheet = true)
        class Profile(val component: ProfileComponent) : MainChild(hasBottomSheet = true)
        class Wizard(val component: WizardComponent) : MainChild(hasBottomSheet = false)
    }
}