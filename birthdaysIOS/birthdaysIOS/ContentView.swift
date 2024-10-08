//
//  ContentView.swift
//  birthdaysIOS
//
//  Created by Nikita Krapotkin on 07.10.2024.
//

import SwiftUI
import shared

struct ContentView: View {
    
    private let root: RootComponent
    
    init(_ root: RootComponent) {
        self.root = root
    }
    
    var body: some View {
        StackView(
            stackValue: StateValue(root.stackValue),
            getTitle: { _ in "Root view" },
            onBack: { _ in }
        ) { child in
            switch child {
            case let child as RootComponentRootChild.Main: Text("Main")
            case let child as RootComponentRootChild.Authorization: AuthorizationScreen(child.component)
            default: EmptyView()
            }
        }
    }
}
