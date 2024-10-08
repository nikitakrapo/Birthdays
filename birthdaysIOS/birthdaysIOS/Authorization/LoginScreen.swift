//
//  LoginScreen.swift
//  birthdaysIOS
//
//  Created by Nikita Krapotkin on 08.10.2024.
//

import SwiftUI
import shared

struct LoginScreen: View {
    
    private let component: LoginComponent
    @StateValue
    private var state: LoginScreenState
    
    init(_ component: LoginComponent) {
        self.component = component
        _state = StateValue(component.stateValue)
    }
    
    var body: some View {
        VStack {
            Text(state.error ?? "")
            TextField(
                "Email",
                text: Binding(get: { state.email }, set: { text in component.onEmailTextChanged(text: text) })
            )
            .textFieldStyle(.roundedBorder)
            TextField(
                "Password",
                text: Binding(get: { state.password }, set: { text in component.onPasswordTextChanged(text: text) })
            )
            .textFieldStyle(.roundedBorder)
            Button(action: { component.onDoneClicked() }, label: {
                Text("Done")
            })
        }
    }
}
