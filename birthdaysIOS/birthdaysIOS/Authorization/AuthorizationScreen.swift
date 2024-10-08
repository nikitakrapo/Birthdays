//
//  AuthorizationScreen.swift
//  birthdaysIOS
//
//  Created by Nikita Krapotkin on 08.10.2024.
//

import SwiftUI
import shared

struct AuthorizationScreen: View {
    
    private let component: AuthorizationComponent
    
    init(_ component: AuthorizationComponent) {
        self.component = component
    }
    
    var body: some View {
        StackView(
            stackValue: StateValue(component.childValue),
            getTitle: { _ in "Authorization view" },
            onBack: { _ in }
        ) { child in
            switch child {
            case let child as AuthorizationComponentAuthorizationChildLogin: LoginScreen(child.component)
            case let child as AuthorizationComponentAuthorizationChildRegistration: Text("Reg")
            default: EmptyView()
            }
        }
    }
}
