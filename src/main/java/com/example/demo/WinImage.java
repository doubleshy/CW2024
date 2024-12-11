package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a "You Win" image displayed when the player wins the game.
 * The image is initially hidden and is shown when the player wins.
 */
public class WinImage extends ImageView {

	/**
	 * The path to the "You Win" image.
	 */
	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";

	/**
	 * The height of the "You Win" image.
	 */
	private static final int HEIGHT = 200;

	/**
	 * The width of the "You Win" image.
	 */
	private static final int WIDTH = 300;

	/**
	 * Constructs a `WinImage` object with the specified X and Y position.
	 * The image is loaded and positioned at the provided coordinates.
	 * The image is initially hidden.
	 *
	 * @param xPosition the X position of the image on the screen.
	 * @param yPosition the Y position of the image on the screen.
	 */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm())); // Load the "You Win" image
		this.setVisible(false); // Initially hidden
		this.setFitHeight(HEIGHT); // Set the height of the image
		this.setFitWidth(WIDTH); // Set the width of the image
		this.setLayoutX(xPosition); // Set the X position
		this.setLayoutY(yPosition); // Set the Y position
	}

	/**
	 * Makes the "You Win" image visible on the screen.
	 * This method is called when the player wins the game.
	 */
	public void showWinImage() {
		this.setVisible(true); // Show the "You Win" image
	}
}
