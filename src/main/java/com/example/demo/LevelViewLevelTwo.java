package com.example.demo.level.levelview;

import com.example.demo.model.Boss;
import com.example.demo.view.BossHealthDisplay;
import com.example.demo.view.ShieldImage;
import javafx.scene.Group;

/**
 * Represents the view for Level 2 in the game.
 * It manages the display of the Boss's shield, health, and other related UI components.
 */
public class LevelViewLevelTwo extends LevelView {

	/**
	 * The root container for the scene's UI elements.
	 */
	private final Group root;

	/**
	 * The shield image representing the Boss's shield.
	 */
	private final ShieldImage shieldImage;

	/**
	 * The display showing the health of the Boss.
	 */
	private final BossHealthDisplay bossHealthDisplay;

	/**
	 * Constructor to initialize the level view for Level 2.
	 * It initializes the heart display, shield, and the Boss health display.
	 *
	 * @param root the root container where all UI components are added.
	 * @param heartsToDisplay the initial number of hearts to display for the player.
	 * @param bossHealth the initial health of the Boss.
	 */
	public LevelViewLevelTwo(Group root, int heartsToDisplay, int bossHealth) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage();
		this.bossHealthDisplay = new BossHealthDisplay(bossHealth);
	}

	/**
	 * Displays the shield image on the screen.
	 * It also brings the shield image to the front to ensure it's visible.
	 */
	public void showShield() {
		shieldImage.showShield();
		shieldImage.toFront();
	}

	/**
	 * Hides the shield image from the screen.
	 */
	public void hideShield() {
		shieldImage.hideShield();
	}

	/**
	 * Adds the shield image to the root container for display.
	 */
	public void displayShield() {
		root.getChildren().add(shieldImage);
	}

	/**
	 * Updates the position of the shield image based on the Boss's position.
	 *
	 * @param boss the Boss object whose position is used to update the shield position.
	 */
	public void updateShieldPosition(Boss boss) {
		double bossPositionX = boss.getLayoutX() + boss.getTranslateX() - 10;
		double bossPositionY = boss.getLayoutY() + boss.getTranslateY() + 20;
		shieldImage.setLayout(bossPositionX, bossPositionY);
	}

	/**
	 * Displays the Boss's health display on the screen.
	 */
	public void showBossHealth() {
		root.getChildren().add(bossHealthDisplay);
	}

	/**
	 * Hides the Boss's health display from the screen.
	 */
	public void hideBossHealth() {
		bossHealthDisplay.hideBossHealth();
	}

	/**
	 * Updates the position of the Boss's health display based on the Boss's position.
	 *
	 * @param boss the Boss object whose position is used to update the health display position.
	 */
	public void updateHealthDisplayPosition(Boss boss) {
		double positionX = boss.getLayoutX() + boss.getTranslateX() + 3; // Center it below the boss
		double positionY = boss.getLayoutY() + boss.getTranslateY() + 85; // Adjust the Y position
		bossHealthDisplay.setLayout(positionX, positionY);
	}

	/**
	 * Updates the Boss's health value on the display.
	 *
	 * @param bossHealth the current health of the Boss.
	 */
	public void updateBossHealth(int bossHealth) {
		bossHealthDisplay.updateBossHealth(bossHealth);
	}
}
