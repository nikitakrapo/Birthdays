package com.nikitakrapo.trips.bottomnav

data class BottomNavigationState(
    val items: List<BottomNavigationItem>,
    val selectedItem: Int,
) {
    init {
        if (items.isNotEmpty()) {
            require(selectedItem in items.indices) {
                "Selected Item index is out of bounds"
            }
        }
    }

    val selectedNavigationItem get() = items[selectedItem]
}