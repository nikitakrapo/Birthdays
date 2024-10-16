package com.nikitakrapo.birthdays.mainscreen

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.nikitakrapo.birthdays.feed.BirthdaysFeedComponentPreview
import kotlinx.coroutines.flow.MutableStateFlow

object MainComponentPreview : MainComponent {

    private val stackInstance = ChildStack(
        Child.Created(
            configuration = "Main",
            instance = MainComponent.MainChild.BottomBarChild.BirthdaysFeed(
                component = BirthdaysFeedComponentPreview,
            )
        )
    )

    override val stackValue = MutableValue(stackInstance)
    override val stack = MutableStateFlow(stackInstance)

    override fun onFeedClicked() {}

    override fun onWishlistClicked() {}

    override fun onProfileClicked() {}
}