package com.nikitakrapo.trips.bottomnav

sealed interface BottomNavigationItem {
    data object Trips : BottomNavigationItem
    data object Profile : BottomNavigationItem
}