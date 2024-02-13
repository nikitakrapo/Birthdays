package com.nikitakrapo.trips

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.trips.login.LoginComponentPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object AuthorizationComponentPreview : AuthorizationComponent {

    override val child: StateFlow<ChildStack<*, AuthorizationComponent.AuthorizationChild>> =
        MutableStateFlow(
            ChildStack(
                Child.Created(
                    configuration = "Main",
                    instance = AuthorizationComponent.AuthorizationChild.Login(
                        component = LoginComponentPreview,
                    )
                )
            )
        )
}
