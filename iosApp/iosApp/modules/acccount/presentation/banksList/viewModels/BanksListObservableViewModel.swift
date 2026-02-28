//
//  BanksListObservableViewModel.swift
//  iosApp
//
//  Created by Yannick Edouard MEKONGO ABANDA on 28/02/2026.
//

import Foundation
import SwiftUI
import shared

@MainActor
class BanksListObservableViewModel: ObservableObject {
    private let viewModel: BanksListViewModel

    @Published var state: BanksListState = .Loading()

    init(viewModel: BanksListViewModel) {
        self.viewModel = viewModel

        // SKIE converts StateFlow to AsyncSequence, no Collector needed
        Task {
            for await newState in viewModel.viewState {
                self.state = newState
            }
        }
    }

    func fetchBanks() {
        viewModel.fetchBanks()
    }
}
