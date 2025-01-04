# User-Manager-Info

The **User-Manager-Info** provides users of app for managing user data.

- [Features](#features)
- [Installation](#installation)
- [Architecture](#architecture)
- [Technologies](#technologies)

## Features

- **Save Users Data**: Provides store user data.
- **Offline Mode**: Caches data for offline access.
- **User-Friendly UI**: Intuitive and responsive design.

## Installation

Clone the repository:
[git clone https://github.com/mostafa4mohamed/User-Manager-Info.git](https://github.com/mostafa4mohamed/User-Manager-Info.git)

## Architecture

The project follows **Clean Architecture**, ensuring clear separation of concerns and modularity. The app is structured into distinct layers to keep the core business logic isolated from external dependencies. The core business logic is placed in the **Domain Layer**, which is independent of other layers.

The app also follows the **MVVM (Model-View-ViewModel)** design pattern, where:
- **Model** represents the data and business logic.
- **View** represents the UI components.
- **ViewModel** acts as a bridge, managing UI-related data and handling logic between the **Model** and **View** layers.

The layers are as follows:
- **Domain Layer**: Contains entities and use cases, representing the core logic.
- **Data Layer**: Handles data operations, including API interactions via **Retrofit** and local storage via **Room**.
- **App Layer**: to create the UI and **ViewModel** to manage UI-related data and handle interactions with the domain layer.

This architecture ensures that each layer is testable, independent, and maintainable.

## Technologies

- **Clean Architecture**: The app is built using **Clean Architecture**, ensuring clear separation of concerns. The core business logic is isolated in the domain layer, while other layers (data and app) interact with it through well-defined interfaces.
- **Kotlin**: The primary programming language.
- **Room**: For offline caching and local storage.
- **Coroutines**: For handling asynchronous tasks.
- **Hilt**: For dependency injection.
