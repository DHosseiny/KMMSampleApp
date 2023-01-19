import SwiftUI
import shared

@main
struct iOSApp: App {
    let component = SharedComponent.companion.create()
    let spaceXRepository : SpaceXRepository

    init() {
        self.spaceXRepository = component.spaceXRepository
    }
    var body: some Scene {
        WindowGroup {
            ContentView(viewModel: .init(spaceXRepository: spaceXRepository))
        }
    }
}
