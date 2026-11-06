package org.example.food_ordering_system;

public class CartItem {

    private final FoodItem foodItem;
    private int quantity;

    public CartItem(FoodItem foodItem) {
        this.foodItem = foodItem;
        this.quantity = 1;
    }

    public FoodItem getFoodItem() { return foodItem; }
    public int getQuantity()      { return quantity; }

    public void increment() { quantity++; }
    public void decrement() { if (quantity > 1) quantity--; }

    public double getSubtotal() {
        return foodItem.getPrice() * quantity;
    }

    public String getName()              { return foodItem.getName(); }
    public String getEmoji()             { return foodItem.getEmoji(); }
    public String getFormattedPrice()    { return foodItem.getFormattedPrice(); }
    public String getQuantityString()    { return String.valueOf(quantity); }
    public String getFormattedSubtotal() { return String.format("GH\u20B5 %.2f", getSubtotal()); }
}
