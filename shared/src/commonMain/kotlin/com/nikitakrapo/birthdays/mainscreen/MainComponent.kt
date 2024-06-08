package com.nikitakrapo.birthdays.mainscreen

import androidx.compose.runtime.Stable
import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.birthdays.profile.ProfileComponent
import com.nikitakrapo.birthdays.profile.ProfileEditComponent
import com.nikitakrapo.birthdays.wizard.WizardComponent
import kotlinx.coroutines.flow.StateFlow

/**
 * Application's main screen. Basically is a bottom navigation host
 */
@Stable
interface MainComponent {

    val stack: StateFlow<ChildStack<*, MainChild>>

    fun onFeedClicked()
    fun onWishlistClicked()
    fun onProfileClicked()

    sealed interface MainChild {

        sealed interface BottomBarChild : MainChild {
            class BirthdaysFeed(val component: Unit) : BottomBarChild
            class Wishlist(val component: Unit) : BottomBarChild
            class Profile(val component: ProfileComponent) : BottomBarChild
        }

        class ProfileEdit(val component: ProfileEditComponent) : MainChild

        class Wizard(val component: WizardComponent) : MainChild
    }
}