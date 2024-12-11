package com.example.demo.controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.level.LevelParent;
import com.example.demo.controller.TransitionScreen;

import java.lang.reflect.Constructor;

import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.util.Duration;
import com.example.demo.controller.EndMenu;

/**
 * The Controller class manages the game flow, including transitions between levels,
 * handling errors, and managing game states like pausing and ending the game.
 */
public class Controller {
	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.level.LevelOne";
	private static final String LEVEL_TWO_CLASS_NAME = "com.example.demo.level.LevelTwo";
	private static final String LEVEL_THREE_CLASS_NAME = "com.example.demo.level.LevelThree";

	private final Stage stage;
	private boolean isPaused = false;

	/**
	 * Constructs the Controller object with the given stage.
	 *
	 * @param stage the primary stage for the game
	 */
	public Controller(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Launches the game by starting with the first level.
	 * Shows the game stage after initializing the first level.
	 */
	public void launchGame() {
		try {
			goToLevelWithTransition(LEVEL_ONE_CLASS_NAME);
		} catch (Exception e) {
			showError(e);
		}
		stage.show();
	}

	/**
	 * Handles transitioning to the specified level with a loading animation.
	 *
	 * @param className the fully qualified class name of the level to transition to
	 */
	private void goToLevelWithTransition(String className) {
		// Determine the loading text based on the class name
		String levelText = "Loading...";
		if (className.equals(LEVEL_ONE_CLASS_NAME)) {
			levelText = "Level loading";
		} else if (className.equals(LEVEL_TWO_CLASS_NAME)) {
			levelText = "Level loading";
		} else if (className.equals(LEVEL_THREE_CLASS_NAME)) { // Support for third level
			levelText = "Level loading";
		}

		// Use TransitionScreen to display a loading animation
		Scene transitionScene = new TransitionScreen(stage.getWidth(), stage.getHeight(), levelText, () -> {
			try {
				// Dynamically load the level class
				Class<?> myClass = Class.forName(className);
				Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
				LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
				myLevel.setOnLevelChange(this::goToLevelWithTransition); // Set callback for level transitions

				// Initialize the scene for the level
				Scene gameScene = myLevel.initializeScene();

				// Apply fade-in animation for the new scene
				Group root = (Group) gameScene.getRoot();
				root.setOpacity(0);

				FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), root);
				fadeIn.setFromValue(0);
				fadeIn.setToValue(1);
				fadeIn.setOnFinished(event -> myLevel.startGame());

				// Switch to the new scene
				stage.setScene(gameScene);
				fadeIn.play();
			} catch (Exception e) {
				showError(e);
			}
		}).getScene();

		stage.setScene(transitionScene);
	}

	/**
	 * Displays an error alert with details about the exception.
	 *
	 * @param e the exception to display
	 */
	private void showError(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("An error occurred");
		alert.setContentText(e.getMessage());
		alert.show();
	}

	/**
	 * Toggles the paused state of the game.
	 * When paused, the game logic should stop; when resumed, it should continue.
	 */
	public void togglePause() {
		isPaused = !isPaused;
	}

	/**
	 * Checks if the game is currently paused.
	 *
	 * @return true if the game is paused, false otherwise
	 */
	public boolean isPaused() {
		return isPaused;
	}

	/**
	 * Ends the game and displays the EndMenu with the given result message.
	 *
	 * @param result the result message to display on the end menu (e.g., "You Win!" or "Game Over")
	 */
	public void endGame(String result) {
		EndMenu endMenu = new EndMenu();
		endMenu.showEndScreen(result); // Display the result message
	}
}
