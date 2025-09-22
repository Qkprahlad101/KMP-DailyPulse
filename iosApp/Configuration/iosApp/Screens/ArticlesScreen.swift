//
//  ArticlesScreen.swift
//  iosApp
//
//  Created by Prahlad Kumar on 13/09/25.
//

import Shared
import SwiftUI

@MainActor
class ArticlesViewModelWrapper: ObservableObject {
    let articlesViewModel: ArticlesViewModel
    @Published var articlesState: ArticlesState
    
    init() {
        articlesViewModel = ArticlesViewModel()
        if let initialState = articlesViewModel.articlesState.value as? ArticlesState {
            articlesState = initialState
        } else {
            articlesState = ArticlesState(articles: [], loading: false, error: nil)
        }
    }
    
    func startObserving() {
        Task {
            for await state in articlesViewModel.articlesState {
                self.articlesState = state
            }
        }
    }
}

public struct ArticlesScreen: View {
    @ObservedObject private var viewModel: ArticlesViewModelWrapper
    
    public init() {
        self.viewModel = ArticlesViewModelWrapper()
    }
    
    public var body: some View {
        VStack {
            AppBar()
            
            if viewModel.articlesState.loading {
                Loader()
            }
            if let error = viewModel.articlesState.error {
                ErrorMessage(message: error)
            }
            if let articles = viewModel.articlesState.articles, !articles.isEmpty {
                ScrollView {
                    LazyVStack(spacing: 10) {
                        ForEach(articles, id: \.self) { article in
                            ArticleItemView(article: article)
                        }
                    }
                }
            }
        }
        .onAppear {
            self.viewModel.startObserving()
        }
    }
    
    struct AppBar: View {
        var body: some View {
            Text("Articles")
                .font(.largeTitle)
                .fontWeight(.bold)
        }
    }
    
    struct Loader: View {
        var body: some View {
            ProgressView()
        }
    }
    
    struct ErrorMessage: View {
        var message: String
        var body: some View {
            Text(message).font(.title)
        }
    }
    
    struct ArticleItemView: View {
        var article: Article
        
        var body: some View {
            VStack(alignment: .leading, spacing: 8) {
                AsyncImage(url: URL(string: article.imgUrl)) { phase in
                    switch phase {
                    case .empty:
                        ProgressView()
                    case .success(let image):
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fit)
                    case .failure:
                        Text("Image Load Error")
                    @unknown default:
                        EmptyView()
                    }
                }
                Text(article.title)
                    .font(.title)
                    .fontWeight(.bold)
                Text(article.des)
                Text(article.date)
                    .frame(maxWidth: .infinity, alignment: .trailing)
                    .foregroundStyle(.gray)
            }
            .padding(16)
        }
    }
}
