//
//  BanksListView.swift
//  TestMobileCA
//
//  Created by Yannick Edouard MEKONGO ABANDA on 25/02/2026.
//

import SwiftUI
import shared

struct BanksListView: View {
    @StateObject var viewModel = BanksListObservableViewModel(
        viewModel: KoinHelper().getBanksListViewModel()
    )

    var body: some View {

        Group {
            // SKIE converts Kotlin sealed classes to Swift enums → native switch/case
            switch onEnum(of: viewModel.state) {
            case .loading:
                ProgressView(String(localized: "loadingBank"))
                    .controlSize(ControlSize.large)

            case .failure(let failure):
                failureView(errorMessage: failure.error)

            case .success(let success):
                let caBanks = success.banks.filter { $0.isCA }
                let otherBanks = success.banks.filter { !$0.isCA }
                List {
                    // Credit Agricole
                    if !caBanks.isEmpty {
                        bankView(banks: caBanks, isCA: true)
                    }
                    // OtherBanks
                    if !otherBanks.isEmpty {
                        bankView(banks: otherBanks, isCA: false)
                    }

                }.listStyle(.plain)
            }
        }.task {
            viewModel.fetchBanks()
        }
        .background(Color(.backgroundScreen))
    }

    @ViewBuilder
    func bankView(banks: [Bank], isCA: Bool) -> some View {
        Section {
            ForEach(banks) { bank in
                DisclosureGroup {
                    ForEach(bank.accounts) { account in
                        NavigationLink {
                            OperationsListView(account: account)
                        } label: {
                            AccountRow(account: account)
                        }
                    }
                } label: {
                    BankRow(bank: bank)
                }
            }
        } header: {
            HStack {
                Image(systemName: "building.columns.circle.fill")
                    .foregroundColor(isCA ? .green : .gray)
                    .font(.title2)
                Text(isCA ? String(localized: "creditAgricole") : String(localized: "othersBanks"))
                    .font(.headline)
                    .foregroundStyle(.section)
            }
        }
    }

    @ViewBuilder
    func failureView(errorMessage: String) -> some View {
        VStack(spacing: 16) {
            Image(systemName: "exclamationmark.triangle.fill")
                .font(.system(size: 50))
                .foregroundColor(.red)
            Text(String(localized: "unableToReadData"))
                .font(.headline)
            Text(errorMessage)
                .multilineTextAlignment(.center)
                .foregroundColor(.secondary)

            Button(String(localized: "retry")) {
                viewModel.fetchBanks()
            }
            .buttonStyle(.borderedProminent)
        }
        .padding()
    }
}

#Preview() {
    BanksListView()
}
