package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents the display of hearts, which shows the player's health in the game.
 * Each heart is represented as an image, and the number of hearts is based on the player's health.
 */
public class HeartDisplay {

	/**
	 * The path to the heart image.
	 */
	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart5.png";

	/**
	 * The height of each heart image.
	 */
	private static final int HEART_HEIGHT = 50;

	/**
	 * The index of the first item in the heart container.
	 */
	private static final int INDEX_OF_FIRST_ITEM = 0;

	/**
	 * The container that holds the heart images (HBox).
	 */
	private HBox container;

	/**
	 * The X position of the container.
	 */
	private double containerXPosition;

	/**
	 * The Y position of the container.
	 */
	private double containerYPosition;

	/**
	 * The number of hearts to display, representing the player's health.
	 */
	private int numberOfHeartsToDisplay;

	/**
	 * Constructs a `HeartDisplay` object with the specified position and number of hearts.
	 *
	 * @param xPosition the X position of the heart display container.
	 * @param yPosition the Y position of the heart display container.
	 * @param heartsToDisplay the number of hearts to display, representing the player's health.
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	/**
	 * Initializes the container (HBox) for the heart display and sets its position.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);
	}

	/**
	 * Initializes the hearts by creating `ImageView` objects for each heart and adding them to the container.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));

			heart.setFitHeight(HEART_HEIGHT);  // Set the height of the heart image
			heart.setPreserveRatio(true);      // Maintain aspect ratio of the image
			container.getChildren().add(heart); // Add the heart to the container
		}
	}

	/**
	 * Removes one heart from the display (i.e., when the player takes damage).
	 * The first heart in the container is removed.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty()) {
			container.getChildren().remove(INDEX_OF_FIRST_ITEM); // Remove the first heart from the container
		}
	}

	/**
	 * Returns the container that holds the heart images.
	 *
	 * @return the container (HBox) holding the heart images.
	 */
	public HBox getContainer() {
		return container;
	}
}
