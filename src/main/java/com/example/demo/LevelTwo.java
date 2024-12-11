package com.example.demo.level;

import com.example.demo.model.Boss;
import com.example.demo.level.levelview.LevelView;
import com.example.demo.level.levelview.LevelViewLevelTwo;
import javafx.scene.Scene;

/**
 * Represents the second level in the game, where the player faces the Boss.
 * It includes logic for spawning the Boss, checking if the game is over,
 * and transitioning to the next level.
 */
public class LevelTwo extends LevelParent {

	/**
	 * The background image for the level.
	 */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background13.png";

	/**
	 * The class name for the next level.
	 */
	private static final String NEXT_LEVEL = "com.example.demo.level.LevelThree";

	/**
	 * The initial health for the player.
	 */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * The Boss unit for this level.
	 */
	private final Boss boss;

	/**
	 * The health of the Boss.
	 */
	private static final int bossHealth = 15;

	/**
	 * The level view that handles the visual components of the level.
	 */
	private LevelViewLevelTwo levelView;

	/**
	 * Constructor to initialize the second level with the screen dimensions.
	 * It also creates a Boss with the specified health.
	 *
	 * @param screenHeight the height of the screen.
	 * @param screenWidth  the width of the screen.
	 */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss(bossHealth);
	}

	/**
	 * Initializes the friendly units for this level, including the player character.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Checks if the game is over, either because the player is destroyed or the Boss is defeated.
	 * If the player is destroyed, the game ends.
	 * If the Boss is defeated, the game proceeds to the next level.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame(); // Game over if the player is destroyed
			levelView.hideBossHealth(); // Hide the Boss health display
		} else if (boss.isDestroyed()) {
			levelView.hideBossHealth(); // Hide the Boss health display
			goToNextLevel(NEXT_LEVEL); // Move to the next level if the Boss is defeated
		}
	}

	/**
	 * Spawns enemy units for the level. In this case, it spawns the Boss if there are no other enemies.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss); // Add the Boss as the enemy unit
		}
	}

	/**
	 * Instantiates the level view, which handles the display of the shield and health of the Boss.
	 *
	 * @return the instantiated level view.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH, bossHealth);
		return levelView;
	}

	/**
	 * Initializes the scene for the level, including the shield and the Boss's health display.
	 *
	 * @return the initialized scene for this level.
	 */
	@Override
	public Scene initializeScene() {
		Scene scene = super.initializeScene();
		levelView.displayShield(); // Display the shield
		levelView.showBossHealth(); // Show the Boss health
		return scene;
	}

	/**
	 * Updates the level view, including the shield position and the Boss health display.
	 * This method is called regularly to update the game state visually.
	 */
	@Override
	protected void updateLevelView() {
		super.updateLevelView();

		// Update shield position relative to Boss
		levelView.updateShieldPosition(boss);

		if (boss.isShielded()) {
			levelView.showShield(); // Show shield if Boss is shielded
		} else {
			levelView.hideShield(); // Hide shield if Boss is not shielded
		}

		// Update Boss health display
		levelView.updateBossHealth(boss.getHealth());
		levelView.updateHealthDisplayPosition(boss);
	}
}
