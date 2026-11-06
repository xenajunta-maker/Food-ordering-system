package org.example.food_ordering_system;

public class FoodItem {

    private final String name;
    private final String description;
    private final double price;
    private final String category;
    private final String emoji;

    public FoodItem(String name, String description, double price, String category, String emoji) {
        this.name        = name;
        this.description = description;
        this.price       = price;
        this.category    = category;
        this.emoji       = emoji;
    }

    public String getName()        { return name; }
    public String getDescription() { return description; }
    public double getPrice()       { return price; }
    public String getCategory()    { return category; }
    public String getEmoji()       { return emoji; }

    public String getFormattedPrice() {
        return String.format("GH\u20B5 %.2f", price);
    }
}
