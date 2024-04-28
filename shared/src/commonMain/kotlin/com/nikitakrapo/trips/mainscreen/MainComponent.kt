package com.nikitakrapo.trips.mainscreen

import androidx.compose.runtime.Stable
import com.arkivanov.decompose.router.stack.ChildStack
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

    sealed interface MainChild {
        class BirthdaysFeed(val component: Unit) : MainChild
        class TripsFeed(val component: TripsFeedComponent) : MainChild
        class Profile(val component: ProfileComponent) : MainChild
    }
}