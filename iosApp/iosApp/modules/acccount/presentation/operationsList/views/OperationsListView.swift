//
//  OperationsListView.swift
//  TestMobileCA
//
//  Created by Yannick Edouard MEKONGO ABANDA on 25/02/2026.
//

import SwiftUI
import shared

struct OperationsListView: View {
    @Environment(\.dismiss) private var dismiss
    var account: Account
    var body: some View {

        VStack {
            VStack(spacing: 10) {
                Text(account.balance, format: .currency(code: "EUR"))
                    .font(.largeTitle)
                    .padding()
                Text("\(account.label)")
                    .font(.title)
                    .padding()

                VStack {
                    List(account.operations) { operation in
                        OperationsRow(operation: operation)
                            .padding(.horizontal, 20)
                    }.listStyle(.plain)
                }
            }
            Spacer()
        }.background(.backgroundScreen)
            .navigationBarBackButtonHidden(true)
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    Button {
                        dismiss()
                    } label: {
                        HStack(spacing: 4) {
                            Image(systemName: "chevron.left")
                                .fontWeight(.medium)
                            Text(String(localized: "myAccounts"))
                        }
                    }
                }
            }
    }
}

#Preview {
    OperationsListView(
        account: Account(
            id: "151515151151", order: 1, holder: "Corinne Martin", role: 1,
            contractNumber: "32216549871", label: "Compte de dépôt", productCode: "00004",
            balance: 2031.84, operations: []))
}
