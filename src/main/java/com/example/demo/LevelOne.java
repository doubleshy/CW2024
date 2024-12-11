package com.example.demo.level;

import com.example.demo.level.levelview.LevelView;
import com.example.demo.level.levelview.LevelViewLevelOne;
import com.example.demo.model.ActiveActorDestructible;
import com.example.demo.model.EnemyPlane;
import javafx.scene.Scene;

/**
 * Represents the first level in the game. This level manages the spawn of enemy planes, tracks the player's kill count,
 * and handles transitions to the next level. The player advances to the next level once the target kill count is reached.
 */
public class LevelOne extends LevelParent {

	/**
	 * The background image for the level.
	 */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";

	/**
	 * The class name for the next level (Level Two).
	 */
	private static final String NEXT_LEVEL = "com.example.demo.level.LevelTwo";

	/**
	 * The total number of enemies to spawn in the level.
	 */
	private static final int TOTAL_ENEMIES = 5;

	/**
	 * The number of kills required for the player to advance to the next level.
	 */
	private static final int KILLS_TO_ADVANCE = 8;

	/**
	 * The probability that an enemy will spawn on each attempt.
	 */
	private static final double ENEMY_SPAWN_PROBABILITY = .20;

	/**
	 * The initial health of the player at the start of the level.
	 */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * The level view for displaying the player's health, kill count, and other visual elements.
	 */
	private LevelViewLevelOne levelView;

	/**
	 * Constructor to initialize the level with the specified screen dimensions.
	 * It calls the parent class constructor to set up the background image, screen size, and player health.
	 *
	 * @param screenHeight the height of the screen.
	 * @param screenWidth the width of the screen.
	 */
	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the game is over. The game ends if the player is destroyed or if the player has reached the target kill count.
	 */

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (userHasReachedKillTarget()) {
			goToNextLevel(NEXT_LEVEL);
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
	 * Spawns enemy units based on the defined spawn probability and total enemies.
	 * Enemies are spawned at random vertical positions.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy); // Add the newly spawned enemy to the level
			}
		}
	}

	/**
	 * Instantiates the level view, which handles the visual components such as the kill count and player health.
	 *
	 * @return the instantiated level view for Level 1.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		this.levelView = new LevelViewLevelOne(getRoot(), PLAYER_INITIAL_HEALTH, KILLS_TO_ADVANCE);
		return levelView;
	}

	/**
	 * Checks if the player has reached the required kill target to advance to the next level.
	 *
	 * @return true if the player has reached or exceeded the kill target, false otherwise.
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

	/**
	 * Updates the level view with the current kill count of the player.
	 */
	@Override
	protected void updateLevelView() {
		super.updateLevelView();
		int currentKillCount = getUser().getNumberOfKills();
		levelView.updateKillCount(currentKillCount); // Update the kill count on the level view
	}

	/**
	 * Initializes the scene for Level 1, including displaying the kill count on the screen.
	 *
	 * @return the initialized scene for Level 1.
	 */
	@Override
	public Scene initializeScene() {
		Scene scene = super.initializeScene();
		levelView.showKillCount(); // Show the kill count display on the scene
		return scene;
	}
}
