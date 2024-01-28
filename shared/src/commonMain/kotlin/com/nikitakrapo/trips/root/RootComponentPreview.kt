package com.nikitakrapo.trips.root

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.trips.mainscreen.MainComponentPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal object RootComponentPreview : RootComponent {
    override val stack: StateFlow<ChildStack<*, RootComponent.RootChild>> by lazy {
        MutableStateFlow(
            ChildStack(
                Child.Created(
                    configuration = "Main",
                    instance = RootComponent.RootChild.Main(
                        component = MainComponentPreview,
                    )
                )
            )
        )
    }
}