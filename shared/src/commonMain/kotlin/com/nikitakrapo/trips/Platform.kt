package com.nikitakrapo.trips

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform