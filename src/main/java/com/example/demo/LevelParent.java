package com.example.demo.level;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.example.demo.controller.EndMenu;
import com.example.demo.level.levelview.LevelView;
import com.example.demo.model.UserPlane;
import com.example.demo.model.ActiveActorDestructible;
import com.example.demo.model.FighterPlane;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;

/**
 * The parent class for all levels in the game. It manages the game loop, player and enemy units,
 * projectiles, collisions, and level transitions. It handles the core mechanics such as updating actors,
 * spawning enemies, handling collisions, and checking for game over or win conditions.
 */
public abstract class LevelParent extends Observable {

	/**
	 * Adjustment value for the screen height when determining the maximum Y position for enemies.
	 */
	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;

	/**
	 * Delay in milliseconds between each game loop update.
	 */
	private static final int MILLISECOND_DELAY = 50;

	/**
	 * The height of the screen.
	 */
	private final double screenHeight;

	/**
	 * The width of the screen.
	 */
	private final double screenWidth;

	/**
	 * End menu, used to display the game end interface
	 */
	private EndMenu endMenu;


	/**
	 * The maximum Y position that enemies can move to.
	 */
	private final double enemyMaximumYPosition;

	/**
	 * The root node of the scene, holding all game elements.
	 */
	private final Group root;

	/**
	 * The timeline that controls the game loop.
	 */
	private final Timeline timeline;

	/**
	 * The user plane (player's character).
	 */
	private final UserPlane user;

	/**
	 * The scene containing all the elements for the game.
	 */
	private final Scene scene;

	/**
	 * The background image of the level.
	 */
	private final ImageView background;

	/**
	 * A list of friendly units (e.g., the player's plane).
	 */
	private final List<ActiveActorDestructible> friendlyUnits;

	/**
	 * A list of enemy units in the level.
	 */
	private final List<ActiveActorDestructible> enemyUnits;

	/**
	 * A list of projectiles fired by the user.
	 */
	private final List<ActiveActorDestructible> userProjectiles;

	/**
	 * A list of projectiles fired by enemies.
	 */
	private final List<ActiveActorDestructible> enemyProjectiles;

	/**
	 * The current number of enemies in the level.
	 */
	private int currentNumberOfEnemies;

	/**
	 * The level view that displays the UI components such as health, score, and other game-related information.
	 */
	private LevelView levelView;

	/**
	 * A callback for handling level transitions.
	 */
	private Consumer<String> levelChangeHandler;

	/**
	 * Constructs a LevelParent with the given screen dimensions, background image, and initial player health.
	 *
	 * @param backgroundImageName the name of the background image for the level.
	 * @param screenHeight the height of the screen.
	 * @param screenWidth the width of the screen.
	 * @param playerInitialHealth the initial health of the player.
	 */
	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		initializeTimeline();
		friendlyUnits.add(user);
	}

	/**
	 * Sets the callback for level transitions. This callback will be called when the level needs to change.
	 *
	 * @param levelChangeHandler the callback to handle level transitions.
	 */
	public void setOnLevelChange(Consumer<String> levelChangeHandler) {
		this.levelChangeHandler = levelChangeHandler;
	}

	/**
	 * Transitions to the next level by stopping the current timeline and calling the level change handler.
	 *
	 * @param nextLevelClassName the name of the next level class.
	 */
	protected void goToNextLevel(String nextLevelClassName) {
		if (levelChangeHandler != null) {
			timeline.stop();
			levelChangeHandler.accept(nextLevelClassName);
		}
	}

	/**
	 * Initializes the friendly units for the level. This method should be implemented in the child classes.
	 */
	protected abstract void initializeFriendlyUnits();

	/**
	 * Checks if the game is over. This method should be implemented in the child classes.
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Spawns enemy units in the level. This method should be implemented in the child classes.
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Instantiates the level view, which manages the display of the player's health, score, and other UI elements.
	 *
	 * @return the instantiated level view for the current level.
	 */
	protected abstract LevelView instantiateLevelView();

	/**
	 * Initializes the scene for the level by setting up the background, friendly units, and UI components.
	 *
	 * @return the initialized scene for the level.
	 */
	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	/**
	 * Starts the game by playing the game loop timeline.
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	/**
	 * Updates the scene by spawning new enemies, updating actors, handling collisions, and removing destroyed actors.
	 */
	private void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handleFireProjectileCollisions();
		handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	/**
	 * Initializes the game loop timeline. This timeline runs continuously, calling the updateScene method at regular intervals.
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	/**
	 * Initializes the background image and sets up the key and mouse event handlers for player movement and projectile firing.
	 */
	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP || kc == KeyCode.W) user.moveUp();
				if (kc == KeyCode.DOWN || kc == KeyCode.S) user.moveDown();
				if (kc == KeyCode.LEFT || kc == KeyCode.A) user.moveLeft();
				if (kc == KeyCode.RIGHT || kc == KeyCode.D) user.moveRight();
				if (kc == KeyCode.SPACE) fireProjectile();
			}
		});
		background.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP || kc == KeyCode.DOWN || kc == KeyCode.W || kc == KeyCode.S) user.stopVertically();
				if (kc == KeyCode.LEFT || kc == KeyCode.RIGHT || kc == KeyCode.A || kc == KeyCode.D) user.stopHorizontally();
			}
		});
		background.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getButton() == MouseButton.PRIMARY) {
					fireProjectile();
				}
			}
		});
		root.getChildren().add(background);
	}

	/**
	 * Fires a projectile from the player's plane.
	 */
	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

	/**
	 * Generates enemy projectiles and adds them to the scene.
	 */
	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	/**
	 * Spawns an enemy projectile and adds it to the scene.
	 *
	 * @param projectile the projectile fired by the enemy.
	 */
	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	/**
	 * Updates the positions and states of all actors (player, enemies, projectiles).
	 */
	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
	}

	/**
	 * Removes all destroyed actors from the scene.
	 */
	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	/**
	 * Removes destroyed actors from a specified list of actors.
	 *
	 * @param actors the list of actors to check and remove destroyed actors from.
	 */
	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(ActiveActorDestructible::isDestroyed)
				.collect(Collectors.toList());
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	/**
	 * Handles collisions between planes (both friendly and enemy).
	 */
	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	/**
	 * Handles collisions between user projectiles and enemy units.
	 */
	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	/**
	 * Handles collisions between enemy projectiles and friendly units.
	 */
	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	/**
	 * Handles collisions between enemy projectiles and user projectiles.
	 */
	private void handleFireProjectileCollisions() {
		handleCollisions(enemyProjectiles, userProjectiles);
	}

	/**
	 * Handles collisions between two sets of actors.
	 *
	 * @param actors1 the first set of actors to check for collisions.
	 * @param actors2 the second set of actors to check for collisions.
	 */
	private void handleCollisions(List<ActiveActorDestructible> actors1,
								  List<ActiveActorDestructible> actors2) {
		for (ActiveActorDestructible actor : actors2) {
			for (ActiveActorDestructible otherActor : actors1) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					actor.takeDamage();
					otherActor.takeDamage();
				}
			}
		}
	}

	/**
	 * Handles the penetration of enemy units through the player's defenses.
	 */
	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	/**
	 * Updates the level view to reflect the player's current health.
	 */
	protected void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	/**
	 * Updates the player's kill count based on the number of enemies destroyed.
	 */
	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	/**
	 * Checks if the enemy has penetrated the player's defenses by moving past the screen's width.
	 *
	 * @param enemy the enemy to check.
	 * @return true if the enemy has penetrated the defenses, false otherwise.
	 */
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	/**
	 * Ends the game with a win and displays the win image.
	 */
	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
		showEndMenu(" Yeah You Win ！");
	}


	/**
	 * Ends the game with a loss and displays the game over image.
	 */
	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
		showEndMenu("Sorry You Lose");
	}

	/**
	 * Show end menu
	 * @param result Game result, "Win" or "Lose"
	 */
	private void showEndMenu(String result) {
		if (endMenu == null) {
			endMenu = new EndMenu();
		}
		endMenu.showEndScreen(result);
	}

	/**
	 * Gets the user plane (player's character).
	 *
	 * @return the user plane.
	 */
	protected UserPlane getUser() {
		return user;
	}

	/**
	 * Gets the root container for the scene's UI elements.
	 *
	 * @return the root container.
	 */
	protected Group getRoot() {
		return root;
	}

	/**
	 * Gets the current number of enemies in the level.
	 *
	 * @return the current number of enemies.
	 */
	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	/**
	 * Adds an enemy unit to the level.
	 *
	 * @param enemy the enemy unit to add.
	 */
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	/**
	 * Gets the maximum Y position that enemies can move to.
	 *
	 * @return the maximum Y position for enemies.
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * Gets the screen width.
	 *
	 * @return the screen width.
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Checks if the user plane has been destroyed.
	 *
	 * @return true if the user plane is destroyed, false otherwise.
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	/**
	 * Updates the current number of enemies in the level.
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}
}
