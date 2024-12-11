package com.example.demo.level.levelview;

import com.example.demo.view.KillCountDisplay;
import com.example.demo.view.PetPower;
import javafx.scene.Group;

/**
 * Represents the view for Level 3 in the game. It handles the display of the kill count,
 * the pet power-up, and manages the logic for dropping a power-up based on the number of kills.
 */
public class LevelViewLevelThree extends LevelView {

	/**
	 * The root container for the scene’s UI elements.
	 */
	private final Group root;

	/**
	 * The kill count display showing the number of kills required to advance to the next level.
	 */
	private final KillCountDisplay killCountDisplay;

	/**
	 * The pet power-up display, which represents a power-up that can be dropped.
	 */
	private final PetPower petPower;

	/**
	 * The last kill count at which a power-up was dropped. This is used to avoid dropping multiple power-ups at the same kill count.
	 */
	private int lastDroppedKillCount;

	/**
	 * Constructor to initialize the level view for Level 3.
	 * It initializes the heart display, kill count display, and the pet power-up.
	 *
	 * @param root the root container where all UI components are added.
	 * @param heartsToDisplay the initial number of hearts to display for the player.
	 * @param killsToAdvance the number of kills required to advance to the next level.
	 */
	public LevelViewLevelThree(Group root, int heartsToDisplay, int killsToAdvance) {
		super(root, heartsToDisplay);
		this.root = root;
		this.killCountDisplay = new KillCountDisplay(killsToAdvance);
		this.petPower = new PetPower();
		this.lastDroppedKillCount = 0;
	}

	/**
	 * Displays the kill count display on the screen.
	 */
	public void showKillCount() {
		root.getChildren().add(killCountDisplay);
	}

	/**
	 * Updates the kill count display with the current number of kills.
	 *
	 * @param killCount the current number of kills.
	 */
	public void updateKillCount(int killCount) {
		killCountDisplay.updateKillCount(killCount);
	}

	/**
	 * Displays the power-up (pet power) on the screen.
	 */
	public void displayPowerUp() {
		root.getChildren().add(petPower);
	}

	/**
	 * Drops a power-up when the kill count is a multiple of 10.
	 * The power-up will be positioned randomly within defined bounds.
	 *
	 * @param killCount the current number of kills.
	 */
	public void dropPowerUp(int killCount) {
		if (killCount % 10 == 0 && killCount != 0) {
			positionPowerup(killCount); // Position the power-up
			petPower.showPowerUp(); // Show the power-up on screen
		}
	}

	/**
	 * Positions the power-up at a random location within defined bounds when a power-up is dropped.
	 * Updates the last dropped kill count to avoid dropping power-ups at the same kill count.
	 *
	 * @param killCount the current number of kills.
	 */
	public void positionPowerup(int killCount) {
		if (killCount > lastDroppedKillCount) {
			// Define the bounds for the power-up's position
			lastDroppedKillCount = killCount; // Update the last dropped kill count
			double minX = 100; // Minimum x position
			double maxX = 100; // Maximum x position
			double minY = 100; // Minimum y position
			double maxY = 100; // Maximum y position

			// Generate random positions within the defined bounds
			double randomX = minX + Math.random() * (maxX - minX);
			double randomY = minY + Math.random() * (maxY - minY);
			petPower.setLayout(randomX, randomY); // Set the power-up's position
		}
	}
}
