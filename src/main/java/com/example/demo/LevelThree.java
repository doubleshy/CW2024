package com.example.demo.level;

import com.example.demo.level.levelview.LevelView;
import com.example.demo.level.levelview.LevelViewLevelThree;
import com.example.demo.model.ActiveActorDestructible;
import com.example.demo.model.SecondEnemyPlane;
import javafx.scene.Scene;

/**
 * Represents the third level in the game. This level manages the spawn of second type of enemy planes,
 * tracks the player's kill count, and handles transitions to the next level once the player reaches the target kill count.
 */
public class LevelThree extends LevelParent {

	/**
	 * The background image for the level.
	 */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background8.jpg";

	/**
	 * The class name for the next level (Level Four).
	 */
	private static final String NEXT_LEVEL = "com.example.demo.level.LevelFour";

	/**
	 * The total number of enemies to spawn in the level.
	 */
	private static final int TOTAL_ENEMIES = 5;

	/**
	 * The number of kills required for the player to advance to the next level.
	 */
	private static final int KILLS_TO_ADVANCE = 15;

	/**
	 * The probability that an enemy will spawn on each attempt.
	 */
	private static final double ENEMY_SPAWN_PROBABILITY = .05;

	/**
	 * The initial health of the player at the start of the level.
	 */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * The level view for displaying the player's health, kill count, power-up, and other visual elements.
	 */
	private LevelViewLevelThree levelView;

	/**
	 * Constructor to initialize the level with the specified screen dimensions.
	 * It calls the parent class constructor to set up the background image, screen size, and player health.
	 *
	 * @param screenHeight the height of the screen.
	 * @param screenWidth the width of the screen.
	 */
	public LevelThree(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the game is over. The game ends if the player is destroyed or if the player has reached the target kill count.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame(); // The game is lost if the player is destroyed
		} else if (userHasReachedKillTarget()) {
			winGame(); // The player wins if the target kill count is reached
		}
	}

	/**
	 * Initializes the friendly units (the player) in the level.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Spawns enemy units in the level. The enemies are of type `SecondEnemyPlane` and are spawned at random vertical positions.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new SecondEnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy); // Add the newly spawned enemy to the level
			}
		}
	}

	/**
	 * Instantiates the level view for Level 3, which handles the display of the player's health, kill count, and other UI elements.
	 *
	 * @return the instantiated level view for Level 3.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		this.levelView = new LevelViewLevelThree(getRoot(), PLAYER_INITIAL_HEALTH, KILLS_TO_ADVANCE);
		return levelView;
	}

	/**
	 * Checks if the player has reached the required kill count to advance to the next level.
	 *
	 * @return true if the player has reached or exceeded the kill target, false otherwise.
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

	/**
	 * Updates the level view with the current kill count and handles power-up logic based on the kill count.
	 */
	@Override
	protected void updateLevelView() {
		super.updateLevelView();
		int currentKillCount = getUser().getNumberOfKills();
		levelView.updateKillCount(currentKillCount); // Update the kill count on the level view
		levelView.dropPowerUp(currentKillCount); // Drop power-ups if conditions are met
	}

	/**
	 * Initializes the scene for Level 3, including displaying the kill count and power-up on the screen.
	 *
	 * @return the initialized scene for Level 3.
	 */
	@Override
	public Scene initializeScene() {
		Scene scene = super.initializeScene();
		levelView.showKillCount(); // Show the kill count display on the scene
		levelView.displayPowerUp(); // Show the power-up display on the scene
		return scene;
	}
}
