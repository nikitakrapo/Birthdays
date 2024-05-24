package com.nikitakrapo.birthdays.wizard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.NavigateNext
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.components.logo.BirthdaysLogo
import com.nikitakrapo.birthdays.components.pager.HorizontalPagerIndicator
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import kotlinx.coroutines.launch
import kotlin.math.min

@Composable
internal fun WizardMainScreen(
    modifier: Modifier = Modifier,
    component: WizardComponent,
) {
    val state by component.state.collectAsState()

    Box(
        modifier = modifier,
    ) {
        val availablePagesCount = min(state.screens.size, state.lastAvailableScreen + 1)
        val pagerState = rememberPagerState { availablePagesCount }
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize(),
            state = pagerState,
        ) { page ->
            val screen = state.screens[page]
            when (screen) {
                WizardScreenState.WizardScreen.Landing -> {
                    WizardScreenLanding()
                }
                WizardScreenState.WizardScreen.BirthdayChooser -> {
                    WizardScreenBirthdayScreen(component)
                }
                WizardScreenState.WizardScreen.Final -> {
                    Text("Final")
                }
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .statusBarsPadding()
                .navigationBarsPadding(),
        ) {
            HorizontalPagerIndicator(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                pageCount = state.screens.size,
                currentPage = pagerState.currentPage,
                pageLimit = state.lastAvailableScreen,
            )
            AnimatedVisibility(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                visible = pagerState.currentPage != state.screens.size - 1,
            ) {
                val scope = rememberCoroutineScope()
                IconButton(
                    enabled = pagerState.currentPage < state.lastAvailableScreen,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.NavigateNext,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

private val WizardTextAlignment = BiasAlignment(0f, 0.7f)

@Composable
private fun WizardScreenLanding() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        BirthdaysLogo(
            modifier = Modifier
                .align(Alignment.Center),
        )
        Column(
            modifier = Modifier
                .align(WizardTextAlignment)
                .navigationBarsPadding()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(strings.R.string.birthdays),
                style = BirthdaysTheme.typography.headlineSmall,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(strings.R.string.birthdays_onboarding_slogan),
                style = BirthdaysTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
private fun WizardScreenBirthdayScreen(
    component: WizardComponent,
) {
    val state by component.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        BirthdaysLogo(
            modifier = Modifier
                .align(Alignment.Center)
                .background(BirthdaysTheme.colorScheme.onTertiary),
        )
        Column(
            modifier = Modifier
                .align(WizardTextAlignment)
                .navigationBarsPadding()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(strings.R.string.birthdays_onboarding_add_birthday_title),
                style = BirthdaysTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(strings.R.string.birthdays_onboarding_add_birthday_subtitle),
                style = BirthdaysTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(16.dp))
            FilledTonalButton(
                onClick = component::onSelectBirthdayClicked
            ) {
                val text = if (state.isBirthdaySelected) {
                    stringResource(strings.R.string.edit_birthday)
                } else {
                    stringResource(strings.R.string.select_birthday)
                }
                val icon = if (state.isBirthdaySelected) {
                    Icons.Outlined.Edit
                } else {
                    Icons.Outlined.CalendarMonth
                }
                Text(text = text)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = icon,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720,
)
@Composable
private fun WizardMainScreenPreview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            WizardMainScreen(
                component = WizardComponentPreview,
            )
        }
    }
}