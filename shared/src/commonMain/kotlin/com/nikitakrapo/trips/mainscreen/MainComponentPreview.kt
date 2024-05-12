package com.nikitakrapo.trips.mainscreen

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal object MainComponentPreview : MainComponent {
    override val stack: StateFlow<ChildStack<*, MainComponent.MainChild>> =
        MutableStateFlow(
            ChildStack(
                Child.Created(
                    configuration = "Main",
                    instance = MainComponent.MainChild.BottomBarChild.BirthdaysFeed(Unit)
                )
            )
        )

    override fun onFeedClicked() {}

    override fun onTripsClicked() {}

    override fun onProfileClicked() {}
}