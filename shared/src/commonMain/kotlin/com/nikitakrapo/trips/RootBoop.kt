package com.nikitakrapo.trips

import org.koin.mp.KoinPlatformTools

class RootBoop {
    val a: String by KoinPlatformTools.defaultContext().get().inject()
}