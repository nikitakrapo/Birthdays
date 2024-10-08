package com.nikitakrapo.birthdays.root

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.birthdays.mainscreen.MainComponentPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object RootComponentPreview : RootComponent {
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