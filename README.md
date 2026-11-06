# QuickBite - Food Ordering System

A desktop food ordering application built with **Java** and **JavaFX**. Users browse a menu of Ghanaian dishes, filter by category, search by name, add items to a cart, adjust quantities, and place an order with an automatically calculated total.

## Features

- Browse 12 menu items across four categories: Rice, Soups, Snacks and Drinks
- Category filter chips and sidebar category links
- Live search by food name
- Add items to a cart, increase or decrease quantity, or remove an item
- Automatic subtotal, service fee (GH₵ 2.00) and total calculation
- Place Order button with a confirmation popup showing the order number and totals
- Validation: the Place Order button is disabled while the cart is empty

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 17+ | Core language |
| JavaFX 21 | GUI framework |
| FXML | UI layout (opens in Scene Builder) |
| JavaFX CSS | Styling |
| Maven | Build and dependency management |

## Project Structure

```
food-ordering-system/
├── pom.xml
├── README.md
├── docs/
│   └── FoodOrderingSystem_Documentation.docx
└── src/main/
    ├── java/
    │   ├── module-info.java
    │   └── org/example/food_ordering_system/
    │       ├── Launcher.java              # Run this class
    │       ├── MainApp.java               # JavaFX Application
    │       ├── DashboardController.java   # UI logic (menu, cart, orders)
    │       ├── FoodItem.java              # Menu item model
    │       ├── CartItem.java              # Cart line (item + quantity)
    │       └── Order.java                 # Confirmed order
    └── resources/org/example/food_ordering_system/
        ├── dashboard.fxml                 # UI layout
        └── styles.css                     # Theme
```

## Getting Started

**Prerequisites:** JDK 17 or higher, IntelliJ IDEA, Maven (bundled with IntelliJ). JavaFX is downloaded automatically by Maven.

1. Clone the repository
   ```bash
   git clone https://github.com/YOUR_USERNAME/food-ordering-system.git
   ```
2. Open the folder in IntelliJ IDEA (`File > Open`) and let Maven finish importing.
3. Open `src/main/java/org/example/food_ordering_system/Launcher.java`, right-click it and choose **Run 'Launcher.main()'**.

Run `Launcher`, not `MainApp`. Starting a JavaFX `Application` subclass directly on the module path can fail with module access errors.

## How It Works

The app follows the MVC pattern:

- **Model:** `FoodItem`, `CartItem`, `Order`
- **View:** `dashboard.fxml` and `styles.css`
- **Controller:** `DashboardController`

The menu and cart cards are built in code from the model objects, so adding a dish is a single line in `populateMenu()`.

## Limitations

- Data is held in memory only; orders are not saved after the app closes
- No payment or delivery tracking (out of scope for this project)
- "My Cart" and "My Orders" sidebar links are placeholders

## Possible Improvements

- Save orders to a file or MySQL database
- Orders history screen
- Login for customers and an admin screen to edit the menu
- Mobile Money payment integration

## Author

Xena, Computer Engineering student, University of Mines and Technology (UMaT), Tarkwa, Ghana.
