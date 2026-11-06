module org.example.food_ordering_system {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.food_ordering_system to javafx.fxml;
    exports org.example.food_ordering_system;
}
