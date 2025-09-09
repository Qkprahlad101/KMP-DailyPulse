//
//  AboutListView.swift
//  iosApp
//
//  Created by Prahlad Kumar on 08/09/25.
//

import SwiftUI
import Shared
struct AboutListView: View {
    
    private struct RowItem: Hashable {
        let title: String
        let subtitle: String
    }
    
    private let items: [RowItem] = {
        let platform = Platform()
        platform.logSystemInfo()
        
        var result: [RowItem]  = [
            .init(title: "Operating System", subtitle: "\(platform.osName) \(platform.osVersion)"),
            .init(title: "Device Model", subtitle: "\(platform.deviceModel)"),
            .init(title: "Density", subtitle: "\(platform.density)")]
        return result
    }()
    
    var body: some View {
        List{
            ForEach(items, id: \.self){ item in
                VStack(alignment: .leading){
                    Text(item.title)
                        .font(.footnote)
                        .foregroundStyle(.secondary)
                    Text(item.subtitle)
                        .font(.body)
                        .foregroundColor(.primary)
                }
                
            }
        }
    }
}

#Preview {
    AboutListView()
}
