//
//  birthdaysIOSApp.swift
//  birthdaysIOS
//
//  Created by Nikita Krapotkin on 07.10.2024.
//

import SwiftUI
import Firebase
import shared

@main
struct birthdaysIOSApp: App {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate
    
    var body: some Scene {
        WindowGroup {
            ContentView(appDelegate.root)
        }
    }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    
    override init() {
        FirebaseApp.configure()
        AppDi().start(platformContext: PlatformPlatformContext())
    }
    
    private var stateKeeper = StateKeeperDispatcherKt.StateKeeperDispatcher(savedState: nil)
    
    lazy var root: RootComponent = RootComponentImpl(
        componentContext: DefaultComponentContext(
            lifecycle: ApplicationLifecycle(),
            stateKeeper: stateKeeper,
            instanceKeeper: nil,
            backHandler: nil
        )
    )
}
