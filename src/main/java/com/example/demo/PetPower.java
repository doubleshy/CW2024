package com.example.demo.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Represents a power-up (PetPower) in the game. This object is shown on the screen for a limited time
 * and then hidden. It uses a `Timeline` to control the display duration and visibility.
 */
public class PetPower extends ImageView {

    /**
     * The path to the power-up image.
     */
    private static final String IMAGE_NAME = "/com/example/demo/images/PetPower.png";

    /**
     * The size of the power-up image.
     */
    public static final int IMAGE_SIZE = 30;

    /**
     * The timer used to control the duration for which the power-up is visible.
     */
    private Timeline timer;

    /**
     * Constructs a `PetPower` object and initializes the power-up image and timer.
     * The power-up is initially hidden.
     */
    public PetPower() {
        this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm())); // Load the power-up image
        this.setVisible(false); // Initially hidden
        this.setFitHeight(IMAGE_SIZE); // Set the height of the image
        this.setFitWidth(IMAGE_SIZE); // Set the width of the image
        initializeTimer(); // Initialize the timer for visibility control
    }

    /**
     * Initializes the timer for the power-up. The power-up will be visible for 3 seconds before being hidden.
     */
    private void initializeTimer() {
        timer = new Timeline(new KeyFrame(Duration.seconds(3), e -> hidePowerUp())); // Set the duration to 3 seconds
        timer.setCycleCount(1); // The timer runs only once
    }

    /**
     * Makes the power-up visible on the screen and starts the timer.
     * The power-up will be visible for a short period before being automatically hidden.
     */
    public void showPowerUp() {
        this.setVisible(true); // Show the power-up
        timer.playFromStart(); // Start the timer when the power-up is shown
    }

    /**
     * Hides the power-up by making it invisible and stopping the timer.
     * This method is called when the timer ends or when the power-up is no longer needed.
     */
    public void hidePowerUp() {
        this.setVisible(false); // Hide the power-up
        timer.stop(); // Stop the timer
    }

    /**
     * Sets the layout position of the power-up on the screen.
     *
     * @param xBound the X position of the power-up on the screen.
     * @param yBound the Y position of the power-up on the screen.
     */
    public void setLayout(double xBound, double yBound) {
        this.setLayoutX(xBound); // Set the X position
        this.setLayoutY(yBound); // Set the Y position
    }
}
