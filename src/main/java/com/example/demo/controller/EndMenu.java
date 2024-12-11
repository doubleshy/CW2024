package com.example.demo.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The EndMenu class creates a customizable end game menu.
 * It displays a message (e.g., "You Win!" or "Game Over") and a button to exit or restart the game.
 */
public class EndMenu {

    private final Stage stage;

    /**
     * Constructor to create the EndMenu.
     */
    public EndMenu() {
        this.stage = new Stage();
    }

    /**
     * Displays the end screen with a customizable message.
     *
     * @param result The result message to display, e.g., "You Win!" or "Game Over".
     */
    public void showEndScreen(String result) {
        // Create a dynamic message based on the result
        Text resultText = new Text(result);
        resultText.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-fill: black;");

        // Create the "Exit" button
        Button exitButton = new Button("EXIT");
        exitButton.setOnAction(event -> handleExitButton());

        // Arrange the result message and exit button in a vertical box
        VBox layout = new VBox(10); // 10px spacing between elements
        layout.getChildren().addAll(resultText, exitButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 10;");

        // Add a background image
        String backgroundImagePath = getClass().getResource("/com/example/demo/images/endbackground.png").toExternalForm();
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(backgroundImagePath),
                BackgroundRepeat.NO_REPEAT, // Do not repeat horizontally
                BackgroundRepeat.NO_REPEAT, // Do not repeat vertically
                BackgroundPosition.CENTER,  // Center the image
                new BackgroundSize(
                        BackgroundSize.DEFAULT.getWidth(), // Use default width
                        BackgroundSize.DEFAULT.getHeight(), // Use default height
                        true, true, false, false // Cover the layout
                )
        );
        layout.setBackground(new Background(backgroundImage));

        // Create a smaller scene and set it on the stage
        Scene scene = new Scene(layout, 400, 250); // 200x150 window size
        stage.setTitle("Game Over");
        stage.setScene(scene);


        stage.setX(0); // Set X coordinate to 0
        stage.setY(0); // Set Y coordinate to 0

        // Show the stage
        stage.show();
    }

    /**
     * Handles the action when the "Exit" button is clicked.
     * This will close the application.
     */
    private void handleExitButton() {
        stage.close(); // Close the end menu window
        System.exit(0); // Exit the application
    }
}
