package com.nikitakrapo.birthdays.helpers.mainscreen

import com.nikitakrapo.birthdays.mainscreen.MainComponent

class BottomBarItem(
    val index: Int,
    val instance: MainComponent.MainChild.BottomBarChild,
)

const val FeedIndex = 0
const val WishlistIndex = 1
const val ProfileIndex = 2

val MainComponent.selectedTab: BottomBarItem?
    get() {
        return when (val instance = stackValue.value.active.instance) {
            is MainComponent.MainChild.BottomBarChild.BirthdaysFeed -> BottomBarItem(
                index = FeedIndex,
                instance = instance,
            )
            is MainComponent.MainChild.BottomBarChild.Wishlist -> BottomBarItem(
                index = WishlistIndex,
                instance = instance,
            )
            is MainComponent.MainChild.BottomBarChild.Profile -> BottomBarItem(
                index = ProfileIndex,
                instance = instance,
            )
            is MainComponent.MainChild.ProfileEdit,
            is MainComponent.MainChild.Wizard -> null
        }
    }

fun MainComponent.onTabSelected(index: Int) {
    when (index) {
        FeedIndex -> onFeedClicked()
        WishlistIndex -> onWishlistClicked()
        ProfileIndex -> onProfileClicked()
        else -> {}
    }
}