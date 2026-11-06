package org.example.food_ordering_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        URL fxmlUrl = getClass().getResource("/org/example/food_ordering_system/dashboard.fxml");
        if (fxmlUrl == null) {
            throw new RuntimeException(
                "Cannot find dashboard.fxml. It must be at:\n" +
                "src/main/resources/org/example/food_ordering_system/dashboard.fxml");
        }

        Parent root = new FXMLLoader(fxmlUrl).load();
        Scene scene = new Scene(root, 1050, 660);

        URL cssUrl = getClass().getResource("/org/example/food_ordering_system/styles.css");
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        }

        primaryStage.setTitle("QuickBite - Food Ordering");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
