//
//  MainScreen.swift
//  birthdaysIOS
//
//  Created by Nikita Krapotkin on 08.10.2024.
//

import SwiftUI
import shared

struct MainScreen: View {
    
    private let component: MainComponent
    @StateValue
    private var child: ChildStack<AnyObject, MainComponentMainChild>
    
    init(_ component: MainComponent) {
        self.component = component
        _child = StateValue(component.stackValue)
    }
    
    var body: some View {
        VStack {
            if (MainComponentHelperKt.selectedTab(component)?.index == nil) {
                StackView(
                    stackValue: StateValue(component.stackValue),
                    getTitle: { _ in "Main view" },
                    onBack: { _ in }
                ) { child in
                    switch child {
                    case let child as MainComponentMainChildWizard: Text("Wizard")
                    case let child as MainComponentMainChildProfileEdit: Text("ProfileEdit")
                    case let child as MainComponentMainChildBottomBarChildProfile: Button(
                        action: {
                            child.component.onEditProfileClicked()
                        },
                        label: {
                            Text("Bottom Profile")
                        }
                    )
                    case let child as MainComponentMainChildBottomBarChildWishlist: Text("Bottom Wishlist")
                    case let child as MainComponentMainChildBottomBarChildBirthdaysFeed: Text("Bottom Feed")
                    default: EmptyView()
                    }
                }
            }
            let activeInstance = child.active.instance
            switch activeInstance {
            case let activeInstance as MainComponentMainChildBottomBarChildProfile: Button(
                action: { activeInstance.component.onEditProfileClicked() },
                label: { Text("Bottom Profile") }
            )
            case let activeInstance as MainComponentMainChildBottomBarChildWishlist: Text("Bottom Wishlist")
            case let activeInstance as MainComponentMainChildBottomBarChildBirthdaysFeed: Text("Bottom Feed")
            default: EmptyView()
            }
            
            let selectedTab = MainComponentHelperKt.selectedTab(component)?.index
            if (selectedTab != nil) {
                HStack {
                    Button(
                        action: { component.onFeedClicked() },
                        label: {
                            let color: Color = if (selectedTab == MainComponentHelperKt.FeedIndex) { .teal } else { .black }
                            Text("Feed")
                                .foregroundColor(color)
                        }
                    )
                    Button(
                        action: { component.onWishlistClicked() },
                        label: {
                            let color: Color = if (selectedTab == MainComponentHelperKt.WishlistIndex) { .teal } else { .black }
                            Text("Wishlist")
                                .foregroundColor(color)
                        }
                    )
                    Button(
                        action: { component.onProfileClicked() },
                        label: {
                            let color: Color = if (selectedTab == MainComponentHelperKt.ProfileIndex) { .teal } else { .black }
                            Text("Profile")
                                .foregroundColor(color)
                        }
                    )
                }
            }
        }
    }
}
