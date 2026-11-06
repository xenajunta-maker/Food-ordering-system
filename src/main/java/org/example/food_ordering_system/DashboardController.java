package org.example.food_ordering_system;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    // ── Sidebar / navigation ──────────────────────────────────
    @FXML private Button btnMenu;
    @FXML private Button btnCart;
    @FXML private Button btnOrders;

    // ── Category chips ────────────────────────────────────────
    @FXML private Button chipAll;
    @FXML private Button chipRice;
    @FXML private Button chipSnacks;
    @FXML private Button chipDrinks;
    @FXML private Button chipSoups;

    // ── Menu ──────────────────────────────────────────────────
    @FXML private FlowPane  menuGrid;
    @FXML private TextField searchField;

    // ── Cart ──────────────────────────────────────────────────
    @FXML private VBox   cartItemsBox;
    @FXML private Label  lblCartCount;
    @FXML private Label  lblSubtotal;
    @FXML private Label  lblServiceFee;
    @FXML private Label  lblTotal;
    @FXML private Button btnPlaceOrder;
    @FXML private Label  lblCartBadge;

    // ── State ─────────────────────────────────────────────────
    private final List<FoodItem> allItems = new ArrayList<>();
    private final List<CartItem> cart     = new ArrayList<>();
    private final List<Order>    orders   = new ArrayList<>();

    private String currentCategory = "All";
    private String currentSearch   = "";

    private static final double SERVICE_FEE = 2.00;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateMenu();
        renderMenu();
        renderCart();

        searchField.textProperty().addListener((o, ov, nv) -> {
            currentSearch = nv == null ? "" : nv.trim();
            renderMenu();
        });
    }

    // ── Menu data ─────────────────────────────────────────────
    private void populateMenu() {
        allItems.add(new FoodItem("Jollof Rice",     "Spiced tomato rice with choice of protein", 25.00, "Rice",   "🍛"));
        allItems.add(new FoodItem("Fried Rice",      "Vegetable fried rice with egg or chicken", 22.00, "Rice",   "🍚"));
        allItems.add(new FoodItem("Waakye",          "Rice and beans with stew and extras",      20.00, "Rice",   "🍱"));
        allItems.add(new FoodItem("Fufu & Soup",     "Pounded fufu with light or palm soup",     30.00, "Soups",  "🍲"));
        allItems.add(new FoodItem("Banku & Tilapia", "Fermented banku with grilled tilapia",     35.00, "Soups",  "🐟"));
        allItems.add(new FoodItem("Groundnut Soup",  "Rich peanut soup with choice of meat",     28.00, "Soups",  "🥣"));
        allItems.add(new FoodItem("Kelewele",        "Spiced fried plantain cubes, crispy",      10.00, "Snacks", "🍌"));
        allItems.add(new FoodItem("Meat Pie",        "Flaky pastry filled with spiced beef",      8.00, "Snacks", "🥧"));
        allItems.add(new FoodItem("Spring Rolls",    "Crispy rolls with vegetable filling",      12.00, "Snacks", "🌯"));
        allItems.add(new FoodItem("Fresh Juice",     "Sobolo, pineapple or mango, chilled",       8.00, "Drinks", "🧃"));
        allItems.add(new FoodItem("Soft Drink",      "Coke, Fanta or Sprite, chilled",            5.00, "Drinks", "🥤"));
        allItems.add(new FoodItem("Bottled Water",   "Still or sparkling, 500ml",                 3.00, "Drinks", "💧"));
    }

    // ── Menu rendering ────────────────────────────────────────
    private void renderMenu() {
        menuGrid.getChildren().clear();

        List<FoodItem> filtered = allItems.stream()
                .filter(f -> currentCategory.equals("All") || f.getCategory().equals(currentCategory))
                .filter(f -> currentSearch.isEmpty()
                        || f.getName().toLowerCase().contains(currentSearch.toLowerCase()))
                .toList();

        if (filtered.isEmpty()) {
            Label empty = new Label("No items found.");
            empty.setStyle("-fx-text-fill: #B4B2A9; -fx-font-size: 13px;");
            menuGrid.getChildren().add(empty);
            return;
        }

        for (FoodItem item : filtered) {
            menuGrid.getChildren().add(buildFoodCard(item));
        }
    }

    private VBox buildFoodCard(FoodItem item) {
        VBox card = new VBox(0);
        card.setPrefWidth(168);
        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #E8E6F0;" +
            "-fx-border-width: 0.5;" +
            "-fx-border-radius: 10;" +
            "-fx-background-radius: 10;");

        Label banner = new Label(item.getEmoji());
        banner.setAlignment(Pos.CENTER);
        banner.setTextAlignment(TextAlignment.CENTER);
        banner.setMaxWidth(Double.MAX_VALUE);
        banner.setPrefHeight(68);
        banner.setStyle(
            "-fx-font-size: 30px;" +
            "-fx-background-radius: 10 10 0 0;" +
            "-fx-background-color: " + categoryBg(item.getCategory()) + ";");

        VBox info = new VBox(4);
        info.setPadding(new Insets(10, 12, 12, 12));

        Label name = new Label(item.getName());
        name.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #2C2C2A;");
        name.setWrapText(true);

        Label desc = new Label(item.getDescription());
        desc.setStyle("-fx-font-size: 10px; -fx-text-fill: #888780;");
        desc.setWrapText(true);

        HBox footer = new HBox();
        footer.setAlignment(Pos.CENTER_LEFT);
        footer.setPadding(new Insets(6, 0, 0, 0));

        Label price = new Label(item.getFormattedPrice());
        price.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #D85A30;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button addBtn = new Button("+");
        addBtn.setStyle(
            "-fx-background-color: #D85A30; -fx-text-fill: white;" +
            "-fx-font-size: 15px; -fx-font-weight: bold; -fx-background-radius: 50;" +
            "-fx-min-width: 26px; -fx-min-height: 26px;" +
            "-fx-max-width: 26px; -fx-max-height: 26px;" +
            "-fx-padding: 0; -fx-cursor: hand;");
        addBtn.setOnAction(e -> addToCart(item));

        footer.getChildren().addAll(price, spacer, addBtn);
        info.getChildren().addAll(name, desc, footer);
        card.getChildren().addAll(banner, info);
        return card;
    }

    private String categoryBg(String category) {
        return switch (category) {
            case "Rice"   -> "#FAEEDA";
            case "Soups"  -> "#FAECE7";
            case "Snacks" -> "#E1F5EE";
            case "Drinks" -> "#E6F1FB";
            default       -> "#F4F3F8";
        };
    }

    // ── Cart logic ────────────────────────────────────────────
    private void addToCart(FoodItem item) {
        for (CartItem ci : cart) {
            if (ci.getName().equals(item.getName())) {
                ci.increment();
                renderCart();
                return;
            }
        }
        cart.add(new CartItem(item));
        renderCart();
    }

    private void renderCart() {
        cartItemsBox.getChildren().clear();

        if (cart.isEmpty()) {
            Label empty = new Label("Cart is empty.\nAdd items from the menu.");
            empty.setStyle("-fx-text-fill: #B4B2A9; -fx-font-size: 11px;");
            empty.setTextAlignment(TextAlignment.CENTER);
            empty.setAlignment(Pos.CENTER);
            empty.setMaxWidth(Double.MAX_VALUE);
            empty.setPadding(new Insets(24, 0, 24, 0));
            cartItemsBox.getChildren().add(empty);

            lblCartCount.setText("0 items");
            lblCartBadge.setVisible(false);
            lblSubtotal.setText("GH\u20B5 0.00");
            lblServiceFee.setText(String.format("GH\u20B5 %.2f", SERVICE_FEE));
            lblTotal.setText("GH\u20B5 0.00");
            btnPlaceOrder.setDisable(true);
            return;
        }

        for (CartItem ci : cart) {
            cartItemsBox.getChildren().add(buildCartRow(ci));
        }

        int    totalQty = cart.stream().mapToInt(CartItem::getQuantity).sum();
        double subtotal = cart.stream().mapToDouble(CartItem::getSubtotal).sum();
        double total    = subtotal + SERVICE_FEE;

        lblCartCount.setText(totalQty + " item" + (totalQty == 1 ? "" : "s"));
        lblCartBadge.setText(String.valueOf(cart.size()));
        lblCartBadge.setVisible(true);
        lblSubtotal.setText(String.format("GH\u20B5 %.2f", subtotal));
        lblServiceFee.setText(String.format("GH\u20B5 %.2f", SERVICE_FEE));
        lblTotal.setText(String.format("GH\u20B5 %.2f", total));
        btnPlaceOrder.setDisable(false);
    }

    private HBox buildCartRow(CartItem ci) {
        HBox row = new HBox(8);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(8, 0, 8, 0));
        row.setStyle("-fx-border-color: #F1EFE8; -fx-border-width: 0 0 1 0;");

        Label emoji = new Label(ci.getEmoji());
        emoji.setStyle("-fx-font-size: 18px; -fx-min-width: 28px;");

        VBox info = new VBox(2);
        HBox.setHgrow(info, Priority.ALWAYS);
        Label nameL = new Label(ci.getName());
        nameL.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #2C2C2A;");
        Label priceL = new Label(ci.getFormattedPrice());
        priceL.setStyle("-fx-font-size: 10px; -fx-text-fill: #888780;");
        info.getChildren().addAll(nameL, priceL);

        HBox qty = new HBox(4);
        qty.setAlignment(Pos.CENTER);

        Button minus = new Button("-");
        minus.setStyle(qtyStyle());
        minus.setOnAction(e -> { ci.decrement(); renderCart(); });

        Label num = new Label(ci.getQuantityString());
        num.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-min-width: 16px; -fx-alignment: center;");

        Button plus = new Button("+");
        plus.setStyle(qtyStyle());
        plus.setOnAction(e -> { ci.increment(); renderCart(); });

        Button del = new Button("x");
        del.setStyle(
            "-fx-background-color: transparent; -fx-text-fill: #E24B4A;" +
            "-fx-font-size: 11px; -fx-cursor: hand; -fx-border-color: transparent;" +
            "-fx-padding: 2 4 2 4;");
        del.setOnAction(e -> { cart.remove(ci); renderCart(); });

        qty.getChildren().addAll(minus, num, plus, del);
        row.getChildren().addAll(emoji, info, qty);
        return row;
    }

    private String qtyStyle() {
        return "-fx-background-color: #F1EFE8; -fx-text-fill: #2C2C2A;" +
               "-fx-font-size: 12px; -fx-background-radius: 4;" +
               "-fx-border-color: #D3D1C7; -fx-border-width: 0.5;" +
               "-fx-min-width: 20px; -fx-min-height: 20px;" +
               "-fx-max-width: 20px; -fx-max-height: 20px;" +
               "-fx-padding: 0; -fx-cursor: hand;";
    }

    // ── Place order ───────────────────────────────────────────
    @FXML
    private void placeOrder() {
        if (cart.isEmpty()) return;   // validation: no empty orders

        Order order = new Order(cart);
        orders.add(order);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText("Order " + order.getOrderIdString() + " confirmed!");
        alert.setContentText(
            "Subtotal:    " + order.getFormattedSubtotal() + "\n" +
            "Service fee: " + order.getFormattedFee() + "\n" +
            "Total:       " + order.getFormattedTotal() + "\n\n" +
            "Thank you for your order!");
        alert.showAndWait();

        cart.clear();
        renderCart();
    }

    // ── Category filters ──────────────────────────────────────
    @FXML private void filterAll()    { setCategory("All",    chipAll); }
    @FXML private void filterRice()   { setCategory("Rice",   chipRice); }
    @FXML private void filterSnacks() { setCategory("Snacks", chipSnacks); }
    @FXML private void filterDrinks() { setCategory("Drinks", chipDrinks); }
    @FXML private void filterSoups()  { setCategory("Soups",  chipSoups); }

    private void setCategory(String category, Button active) {
        currentCategory = category;
        for (Button b : List.of(chipAll, chipRice, chipSnacks, chipDrinks, chipSoups)) {
            b.getStyleClass().remove("chip-active");
            if (!b.getStyleClass().contains("chip")) b.getStyleClass().add("chip");
        }
        active.getStyleClass().add("chip-active");
        renderMenu();
    }

    // ── Navigation placeholders ───────────────────────────────
    @FXML private void showMenu()   { }
    @FXML private void showCart()   { }
    @FXML private void showOrders() { }
}
