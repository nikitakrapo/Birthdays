package com.nikitakrapo.birthdays

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.nikitakrapo.birthdays.login.LoginComponentPreview
import kotlinx.coroutines.flow.MutableStateFlow

object AuthorizationComponentPreview : AuthorizationComponent {

    private val childInstance = ChildStack(
        Child.Created(
            configuration = "Main",
            instance = AuthorizationComponent.AuthorizationChild.Login(
                component = LoginComponentPreview,
            )
        )
    )

    override val childValue = MutableValue(childInstance)
    override val child = MutableStateFlow(childInstance)
}
