# Square Repositories App

A professional Android application that displays a scrollable list of GitHub repositories for the "Square" organization, built with modern Android development practices.

## 🚀 Features
- **Modern UI**: 100% **Jetpack Compose** with **Material Design 3**.
- **Responsive Design**: Optimized for different screen resolutions and orientations.
- **State Management**: Robust handling of **Loading**, **Success**, **Error**, and **No Network** states.
- **Network Resilience**: 
    - **Automatic Refresh**: Refreshes data when returning from background if in an error state.
    - **Speed Optimization**: Configured timeouts (30s) to handle slow connections without crashing.
    - **Connectivity Check**: Real-time detection of network availability.
- **Dependency Injection**: Modular setup using **Koin**.
- **Architecture**: Clean **MVVM** pattern with a clear separation between UI, Business Logic, and Data.
- **Networking**: **Retrofit** with OkHttp logging for efficient API consumption.

## 🛠 Libraries Used
- **Jetpack Compose**: Modern UI toolkit.
- **Retrofit & Gson**: For API communication and JSON serialization.
- **Koin**: Lightweight dependency injection framework.
- **Kotlin Coroutines & Flow**: For asynchronous programming and reactive data streams.
- **MockK & Turbine**: Advanced testing libraries for Unit and Flow testing.

## 📁 Project Structure
- `SquareActivity.kt`: The single activity entry point containing the Compose UI.
- `ui/RepoViewModel.kt`: Manages UI state and business logic.
- `data/repository/`: Data layer abstraction (Repository Pattern).
- `data/api/`: Retrofit service interface.
- `data/model/`: Domain data models.
- `di/AppModule.kt`: Koin modules for dependency provisioning.
- `util/NetworkHelper.kt`: Utility for network status monitoring.

## ⚙️ Setup & Installation
1. Clone or open the project in **Android Studio (Ladybug or newer)**.
2. The project uses **Java 11** and **Kotlin 2.0.21**.
3. Sync Gradle and run the `:app` module.
4. Internet permission is required (added in `AndroidManifest.xml`).

## 🧪 Testing
The project includes a comprehensive test suite:
- **Unit Tests**: `SquareRepositoryTest` and `RepoViewModelTest` cover data and state logic.
- **UI Tests**: `SquareActivityUiTest` verifies the integrity of the main screen.

To run all tests:
```bash
./gradlew test
```

## 📝 Design Notes
- **User Experience**: Includes a "Try Again" mechanism for error recovery.
- **Visuals**: Uses custom gradients, rounded corners, and professional typography to provide a premium look and feel.
