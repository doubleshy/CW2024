package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the "Game Over" image displayed when the player loses in the game.
 * This image appears at the specified position on the screen.
 */
public class GameOverImage extends ImageView {

	/**
	 * The path to the "Game Over" image.
	 */
	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";

	/**
	 * The height of the "Game Over" image.
	 */
	private static final int HEIGHT = 300;

	/**
	 * The width of the "Game Over" image.
	 */
	private static final int WIDTH = 400;

	/**
	 * Constructs a `GameOverImage` object with the specified position.
	 * This constructor sets up the "Game Over" image with a fixed size and position.
	 *
	 * @param xPosition the X position of the "Game Over" image.
	 * @param yPosition the Y position of the "Game Over" image.
	 */
	public GameOverImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm())); // Load the "Game Over" image
		this.setFitHeight(HEIGHT); // Set the height of the image
		this.setFitWidth(WIDTH); // Set the width of the image
		this.setLayoutX(xPosition); // Set the X position of the image
		this.setLayoutY(yPosition); // Set the Y position of the image
	}

	/**
	 * Makes the "Game Over" image visible.
	 * This method is called when the game is over, displaying the image to the player.
	 */
	public void showGameOverImage() {
		this.setVisible(true); // Makes the "Game Over" image visible on the screen
	}
}

