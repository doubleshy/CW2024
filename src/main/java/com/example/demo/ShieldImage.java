package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a shield image in the game. This image is used to display a shield that can be shown or hidden
 * depending on the state of the unit (e.g., when a boss has a shield).
 */
public class ShieldImage extends ImageView {

	/**
	 * The path to the shield image.
	 */
	private static final String IMAGE_NAME = "/com/example/demo/images/shield6.png";

	/**
	 * The size of the shield image.
	 */
	public static final int SHIELD_SIZE = 60;

	/**
	 * Constructs a `ShieldImage` object and initializes the shield image.
	 * The shield image is initially hidden.
	 */
	public ShieldImage() {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm())); // Load the shield image
		this.setVisible(false); // Initially hidden
		this.setFitHeight(SHIELD_SIZE); // Set the height of the image
		this.setFitWidth(SHIELD_SIZE); // Set the width of the image
	}

	/**
	 * Makes the shield visible on the screen.
	 * This method is called when the shield needs to be shown.
	 */
	public void showShield() {
		this.setVisible(true); // Show the shield
	}

	/**
	 * Hides the shield by making it invisible.
	 * This method is called when the shield needs to be hidden.
	 */
	public void hideShield() {
		this.setVisible(false); // Hide the shield
	}

	/**
	 * Sets the layout position of the shield on the screen.
	 *
	 * @param x the X position of the shield on the screen.
	 * @param y the Y position of the shield on the screen.
	 */
	public void setLayout(double x, double y) {
		this.setLayoutX(x); // Set the X position
		this.setLayoutY(y); // Set the Y position
	}
}
