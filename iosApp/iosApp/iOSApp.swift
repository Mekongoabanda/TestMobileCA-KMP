import SwiftUI
import shared

@main
struct iOSApp: App {  // swiftlint:disable:this type_name
    init() {
        KoinKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
