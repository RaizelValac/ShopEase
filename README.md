# 🛒 ShopEase

A modern, offline-first Android E-Commerce application built with the "Big 5" architecture.

## 🏗️ Tech Stack
* **Language:** Kotlin
* **Architecture:** MVVM + Clean Architecture
* **Network:** Retrofit + Moshi + OkHttp Logging (DummyJSON API)
* **Local Data:** Room Database (Dual-Table System: Catalog & Cart)
* **Dependency Injection:** Dagger Hilt
* **Navigation:** Jetpack Compose Navigation (Type-Safe)
* **UI:** Jetpack Compose (Material3)

## 💡 Key Features
* **Dual-Stream Data:** Merges Network data (Catalog) and Local data (Cart) into a single UI state using `combine`.
* **Smart Cart Logic:** * Add/Remove items.
    * Adjust quantities.
    * **Auto-Calculation:** Uses custom SQL queries (`SUM(price * quantity)`) to calculate total price instantly in the Database.
* **Offline First:** The entire catalog is cached. The cart persists across app restarts.
* **Robust Error Handling:** Custom Logging Interceptor and Repository error catching.