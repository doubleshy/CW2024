package com.example.demo.level.levelview;

import com.example.demo.view.WinImage;
import com.example.demo.view.GameOverImage;
import com.example.demo.view.HeartDisplay;
import javafx.scene.Group;

/**
 * This class handles the visual elements of the level, including the display of hearts (player health),
 * win image, and game over image.
 * It manages adding and removing hearts, showing the win screen, and displaying the game over screen.
 */
public class LevelView {

	/**
	 * The X position of the heart display on the screen.
	 */
	private static final double HEART_DISPLAY_X_POSITION = 5;

	/**
	 * The Y position of the heart display on the screen.
	 */
	private static final double HEART_DISPLAY_Y_POSITION = 25;

	/**
	 * The X position of the win image on the screen.
	 */
	private static final int WIN_IMAGE_X_POSITION = 435;

	/**
	 * The Y position of the win image on the screen.
	 */
	private static final int WIN_IMAGE_Y_POSITION = 175;

	/**
	 * The X position of the game over image on the screen.
	 */
	private static final int LOSS_SCREEN_X_POSITION = 375;

	/**
	 * The Y position of the game over image on the screen.
	 */
	private static final int LOSS_SCREEN_Y_POSITION = 155;

	/**
	 * The root node of the scene where all UI components are added.
	 */
	private final Group root;

	/**
	 * The win image display.
	 */
	private final WinImage winImage;

	/**
	 * The game over image display.
	 */
	private final GameOverImage gameOverImage;

	/**
	 * The heart display showing the player's remaining health.
	 */
	private final HeartDisplay heartDisplay;

	/**
	 * Constructor that initializes the view elements (hearts, win image, game over image) with their positions.
	 *
	 * @param root the root container for the scene's UI elements.
	 * @param heartsToDisplay the initial number of hearts to display for the player.
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION);
	}

	/**
	 * Displays the heart display on the screen.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Displays the win image on the screen and animates it.
	 */
	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}

	/**
	 * Displays the game over image on the screen and animates it.
	 */
	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
		gameOverImage.showGameOverImage();
	}

	/**
	 * Removes hearts from the heart display based on the remaining number of hearts.
	 *
	 * @param heartsRemaining the number of hearts to remain on the screen.
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}
}
