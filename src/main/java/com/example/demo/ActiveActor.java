package com.example.demo.model;

import javafx.scene.image.*;

/**
 * Represents an active actor in the game, such as a player plane, enemy plane, or projectile.
 * This class is responsible for displaying the actor's image, handling its position on the screen,
 * and providing basic movement functionality.
 */
public abstract class ActiveActor extends ImageView {

	/**
	 * The directory where all images are stored.
	 */
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Constructs an ActiveActor with the given image name, height, and initial position.
	 * The image is loaded from the specified location and the actor is positioned at the given coordinates.
	 *
	 * @param imageName the name of the image to be displayed.
	 * @param imageHeight the height of the image.
	 * @param initialXPos the initial X position of the actor.
	 * @param initialYPos the initial Y position of the actor.
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		// Load the image from the specified path and set it on the ImageView
		this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
		this.setLayoutX(initialXPos); // Set the X position
		this.setLayoutY(initialYPos); // Set the Y position
		this.setFitHeight(imageHeight); // Set the height of the image
		this.setPreserveRatio(true); // Maintain aspect ratio of the image
	}

	/**
	 * Abstract method that must be implemented by subclasses to update the position of the actor.
	 * The exact behavior will depend on the type of actor (e.g., a moving plane or stationary object).
	 */
	public abstract void updatePosition();

	/**
	 * Moves the actor horizontally by the specified amount.
	 *
	 * @param horizontalMove the amount to move the actor horizontally.
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove); // Adjust the X position
	}

	/**
	 * Moves the actor vertically by the specified amount.
	 *
	 * @param verticalMove the amount to move the actor vertically.
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove); // Adjust the Y position
	}
}
