//
//  BankRow.swift
//  TestMobileCA
//
//  Created by Yannick Edouard MEKONGO ABANDA on 25/02/2026.
//
import SwiftUI
import shared

struct BankRow: View {
    let bank: Bank
    
    var body: some View {
        HStack {
            Text(bank.name)
                .font(.subheadline)
                .foregroundColor(.bankAccountTitle)
        }
        .padding(.vertical, 4)
        .padding(.horizontal, 10)
    }
}

#Preview {
    BankRow(bank: Bank( name: "Crédit Agricole", isCA: true, accounts: [Account]()))
}
