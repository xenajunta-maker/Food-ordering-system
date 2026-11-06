package org.example.food_ordering_system;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private static int orderCounter = 1000;

    private final int orderId;
    private final List<CartItem> items;
    private final double serviceFee;
    private final double totalAmount;
    private final LocalDateTime orderTime;

    public Order(List<CartItem> items) {
        this.orderId     = ++orderCounter;
        this.items       = new ArrayList<>(items);
        this.serviceFee  = 2.00;
        this.totalAmount = items.stream().mapToDouble(CartItem::getSubtotal).sum() + serviceFee;
        this.orderTime   = LocalDateTime.now();
    }

    public int           getOrderId()     { return orderId; }
    public List<CartItem> getItems()      { return items; }
    public double        getServiceFee()  { return serviceFee; }
    public double        getTotalAmount() { return totalAmount; }
    public double        getSubtotal()    { return totalAmount - serviceFee; }
    public LocalDateTime getOrderTime()   { return orderTime; }

    public String getOrderIdString()     { return "#" + orderId; }
    public String getFormattedSubtotal() { return String.format("GH\u20B5 %.2f", getSubtotal()); }
    public String getFormattedFee()      { return String.format("GH\u20B5 %.2f", serviceFee); }
    public String getFormattedTotal()    { return String.format("GH\u20B5 %.2f", totalAmount); }
}
