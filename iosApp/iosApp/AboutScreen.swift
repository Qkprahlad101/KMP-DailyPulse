//
//  AboutScreen.swift
//  iosApp
//
//  Created by Prahlad Kumar on 09/09/25.
//

import SwiftUI

struct AboutScreen: View {
    var body: some View {
        NavigationStack{
            AboutListView().navigationTitle("About Screen")
        }
    }
}

#Preview {
    AboutScreen()
}
