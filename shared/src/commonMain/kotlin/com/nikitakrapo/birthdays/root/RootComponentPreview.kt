package com.nikitakrapo.birthdays.root

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.nikitakrapo.birthdays.mainscreen.MainComponentPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object RootComponentPreview : RootComponent {

    private val stackInstance = ChildStack(
        Child.Created(
            configuration = "Main",
            instance = RootComponent.RootChild.Main(
                component = MainComponentPreview,
            )
        )
    )


    override val stackValue: Value<ChildStack<*, RootComponent.RootChild>> = MutableValue(stackInstance)
    override val stack: StateFlow<ChildStack<*, RootComponent.RootChild>> by lazy {
        MutableStateFlow(stackInstance)
    }
}