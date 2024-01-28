package com.nikitakrapo.trips.mainscreen

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.trips.bottomnav.BottomNavigationComponent
import com.nikitakrapo.trips.bottomnav.BottomNavigationComponentImpl
import com.nikitakrapo.trips.bottomnav.BottomNavigationItem
import com.nikitakrapo.trips.feed.TripsFeedComponentPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal object MainComponentPreview : MainComponent {
    override val stack: StateFlow<ChildStack<*, MainComponent.MainChild>> =
        MutableStateFlow(
            ChildStack(
                Child.Created(
                    configuration = "Main",
                    instance = MainComponent.MainChild.TripsFeed(TripsFeedComponentPreview)
                )
            )
        )

    override val bottomNavigationComponent: BottomNavigationComponent =
        BottomNavigationComponentImpl(
            items = listOf(
                BottomNavigationItem.Trips,
                BottomNavigationItem.Profile,
            ),
            selectedItem = 0,
        )
}