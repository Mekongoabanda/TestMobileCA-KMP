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
                Text(operation.formattedDate)
                    .font(.caption)
                    .foregroundColor(.bankAccountTitle)
            }

            Spacer()

            Text(operation.amount)
                .font(.subheadline)
                .foregroundColor(.bankAccountAmount)
        }
        .padding(.vertical, 4)
    }
}
