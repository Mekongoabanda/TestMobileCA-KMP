//
//  OperationsRow.swift
//  TestMobileCA
//
//  Created by Yannick Edouard MEKONGO ABANDA on 25/02/2026.
//

import SwiftUI
import shared

struct OperationsRow: View {
    let operation: shared.Operation

    var body: some View {
        HStack {
            VStack(alignment: .center, spacing: 4) {
                Text(operation.title)
                    .font(.subheadline)
                    .foregroundStyle(.bankAccountTitle)
                    .bold()
                Text(operation.date)  // Date is already a String in the Domain Entity!
                    .font(.caption)
                    .foregroundColor(.bankAccountTitle)
            }

            Spacer()

            Text(operation.amount)  // Amount is already a formatted String in the Domain Entity!
                .font(.subheadline)
                .foregroundColor(.bankAccountAmount)
        }
        .padding(.vertical, 4)
    }
}

#Preview {

    OperationsRow(
        operation: shared.Operation(
            id: "2", title: "Prélèvement Netflix", amount: "-15.99", date: "20/02/2026",
            category: OperationCategory.leisure))
}
