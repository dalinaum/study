//
//  simpleLoginIOSApp.swift
//  simpleLoginIOS
//
//  Created by L Kim on 2023/08/28.
//

import SwiftUI
import shared

@main
struct simpleLoginIOSApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView(viewModel: .init(loginRepository: LoginRepository(dataSource: LoginDataSource()), loginValidator: LoginDataValidator()))
        }
    }
}
