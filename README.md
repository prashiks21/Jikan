# Jikan API Android App

A modern Android application built with Jetpack Compose that interfaces with the **Jikan API** (Unofficial MyAnimeList API) to fetch and display anime information. 
This app demonstrates the use of Clean Architecture, MVVM pattern, and offline caching capabilities.

## 📱 Features

-   **Top Anime List**: browse a list of the top-rated anime.
-   **Anime Details**: View detailed information about specific anime series.
-   **Offline Support**: Caches data locally using Room to allow viewing previously fetched data without an internet connection.
-   **Modern UI**: Built completely with Jetpack Compose.

## 🛠 Tech Stack

-   **Language**: [Kotlin](https://kotlinlang.org/)
-   **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
-   **Architecture**: Clean Architecture (Presentation, Domain, Data) + MVVM
-   **Dependency Injection**: [Hilt](https://dagger.dev/hilt/)
-   **Network**: [Retrofit](https://square.github.io/retrofit/) & [Moshi](https://github.com/square/moshi/)
-   **Local Storage**: [Room Database](https://developer.android.com/training/data-storage/room)
-   **Image Loading**: [Coil](https://coil-kt.github.io/coil/)
-   **Concurrency**: Coroutines & Flow
-   **Navigation**: Navigation Compose

## 📂 Project Structure

The project follows the principles of Clean Architecture:

-   **`presentation`**: UI components (Screens, ViewModels, Composables).
-   **`domain`**: Business logic, Use Cases, and Repository interfaces. Pure Kotlin code.
-   **`data`**: Repository implementations, API services (Retrofit), and Database (Room).
-   **`di`**: Dependency Injection modules.

## 🚀 Getting Started

### Prerequisites

-   **Android Studio** (Latest version recommended)
-   **JDK 11** or higher

### Installation

1.  **Clone the repository**:
    ```bash
    git clone https://github.com/prashiks21/Jikan.git
    ```
2.  **Open in Android Studio**:
    -   Open Android Studio and select "Open an existing Android Studio project".
    -   Navigate to the cloned directory and select it.
3.  **Build the project**:
    -   Let Gradle sync and build the dependencies.
4.  **Run the app**:
    -   Select an emulator or connect a physical device.
    -   Click the "Run" button (Shift+F10).
