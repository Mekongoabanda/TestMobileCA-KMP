//
//  AccountRow.swift
//  TestMobileCA
//
//  Created by Yannick Edouard MEKONGO ABANDA on 25/02/2026.
//
import SwiftUI
import shared

struct AccountRow: View {
    let account: Account

    var body: some View {
        HStack {
            VStack(alignment: .leading, spacing: 4) {
                Text(account.label)
                    .font(.subheadline)
                    .foregroundStyle(.accountTitle)
            }

            Spacer()

            Text(account.balance, format: .currency(code: "EUR"))
                .font(.subheadline)
                .foregroundColor(.bankAccountAmount)
        }
        .padding(.vertical, 4)
    }
}

#Preview {
    AccountRow(
        account: .init(
            id: "1", order: 1, holder: "Yannick", role: 1,
            contractNumber: "123456789", label: "Compte courant",
            productCode: "01", balance: 2031.84, operations: []
        )
    )
}
