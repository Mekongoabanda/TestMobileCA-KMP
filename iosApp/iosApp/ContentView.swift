import SwiftUI

struct ContentView: View {
    @State var currentTab: Int = 0
    var body: some View {
        TabView(selection: $currentTab) {
            // 0
            myAccountTab().tag(0)
            // 1
            simulationTab().tag(1)
            // 2
            upToYouTab().tag(2)
        }
    }

    @ViewBuilder
    func myAccountTab() -> some View {
        NavigationStack {
            BanksListView().navigationTitle(String(localized: "myAccounts"))
        }.tabItem {
            Label(String(localized: "myAccounts"), systemImage: "person.fill")
        }
    }

    @ViewBuilder
    func simulationTab() -> some View {
        NavigationStack {
            Text(String(localized: "simulation")).navigationTitle(String(localized: "simulation"))
        }.tabItem {
            Label(String(localized: "simulation"), systemImage: "star.fill")
        }
    }

    @ViewBuilder
    func upToYouTab() -> some View {
        NavigationStack {
            Text(String(localized: "upToYou")).navigationTitle(String(localized: "upToYou"))
        }
        .tabItem {
            Label(String(localized: "upToYou"), systemImage: "star.fill")
        }
    }
}

#Preview {
    ContentView()
}
