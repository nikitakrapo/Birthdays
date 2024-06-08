package com.nikitakrapo.birthdays.mainscreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.birthdays.design.components.BottomBarItem
import com.nikitakrapo.birthdays.design.components.BottomNavigationBar
import com.nikitakrapo.birthdays.mainscreen.MainComponent.MainChild
import com.nikitakrapo.birthdays.profile.ProfileEditScreen
import com.nikitakrapo.birthdays.profile.ProfileScreen
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import com.nikitakrapo.birthdays.wizard.WizardScreen
import strings.R

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainComponent: MainComponent,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        val child by mainComponent.stack.collectAsState()
        Children(
            modifier = Modifier.weight(1f),
            stack = child,
            animation = mainScreenChildrenAnimation(),
        ) {
            when (val instance = it.instance) {
                is MainChild.BottomBarChild.BirthdaysFeed -> Box(
                    modifier = Modifier
                        .statusBarsPadding(),
                ) {
                    Text(text = "$instance")
                }

                is MainChild.BottomBarChild.Wishlist -> Box(
                    modifier = Modifier
                        .statusBarsPadding(),
                ) {
                    Text(text = "$instance")
                }

                is MainChild.BottomBarChild.Profile -> ProfileScreen(
                    modifier = Modifier
                        .statusBarsPadding(),
                    component = instance.component,
                )

                is MainChild.ProfileEdit -> ProfileEditScreen(
                    modifier = Modifier
                        .statusBarsPadding(),
                    component = instance.component,
                )

                is MainChild.Wizard -> WizardScreen(
                    modifier = Modifier,
                    component = instance.component,
                )
            }
        }
        MainScreenBottomBar(
            child = child,
            mainComponent = mainComponent,
        )
    }
}

@Composable
private fun MainScreenBottomBar(
    child: ChildStack<*, MainChild>,
    mainComponent: MainComponent,
) {
    AnimatedContent(
        targetState = child,
        label = "MainScreen BottomBar",
        contentKey = { it.active.instance is MainChild.BottomBarChild },
    ) { child ->
        when (val instance = child.active.instance) {
            is MainChild.BottomBarChild -> {
                val context = LocalContext.current
                BottomNavigationBar(
                    items = listOf(
                        BottomBarItem(
                            title = context.getString(R.string.birthdays),
                            icon = Icons.Outlined.CalendarMonth,
                        ),
                        BottomBarItem(
                            title = context.getString(R.string.wishlist),
                            icon = Icons.Outlined.StarOutline,
                        ),
                        BottomBarItem(
                            title = context.getString(R.string.profile),
                            icon = Icons.Outlined.Person,
                        )
                    ),
                    selectedItem = when (instance) {
                        is MainChild.BottomBarChild.BirthdaysFeed -> 0
                        is MainChild.BottomBarChild.Wishlist -> 1
                        is MainChild.BottomBarChild.Profile -> 2
                    },
                    onItemClick = {
                        if (child.active.instance !is MainChild.BottomBarChild) return@BottomNavigationBar
                        when (it) {
                            0 -> mainComponent.onFeedClicked()
                            1 -> mainComponent.onWishlistClicked()
                            2 -> mainComponent.onProfileClicked()
                            else -> error("Unhandled bottom bar click with $it index")
                        }
                    },
                )
            }
            else -> {}
        }
    }
}

@Preview
@Composable
private fun MainScreen_Preview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            MainScreen(mainComponent = MainComponentPreview)
        }
    }
}