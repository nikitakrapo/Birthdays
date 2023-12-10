package com.nikitakrapo.trips.bottomnav

import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.StateFlow

@Stable
interface BottomNavigationComponent {

    val state: StateFlow<BottomNavigationState>

    fun onTabSelected(index: Int)
}
