package com.nikitakrapo.trips.bottomnav

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BottomNavigationComponentImpl(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
) : BottomNavigationComponent {

    private val _state = MutableStateFlow(
        BottomNavigationState(
            items = items,
            selectedItem = selectedItem,
        )
    )
    override val state: StateFlow<BottomNavigationState> = _state.asStateFlow()

    override fun onTabSelected(index: Int) {
        _state.update {
            it.copy(selectedItem = index)
        }
    }
}