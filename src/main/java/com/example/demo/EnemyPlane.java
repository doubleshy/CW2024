package com.example.demo.model;

import com.example.demo.projectile.EnemyProjectile;

/**
 * Represents an enemy plane in the game. The enemy plane moves horizontally across the screen
 * and fires projectiles at the player at a specified rate. It has a health pool and can be destroyed.
 */
public class EnemyPlane extends FighterPlane {

	/**
	 * The image of the enemy plane.
	 */
	private static final String IMAGE_NAME = "enemyplane.png";

	/**
	 * The height of the enemy plane image.
	 */
	private static final int IMAGE_HEIGHT = 50;

	/**
	 * The horizontal velocity at which the enemy plane moves.
	 */
	private static final int HORIZONTAL_VELOCITY = -6;

	/**
	 * The horizontal offset for the projectile spawn.
	 */
	private static final double PROJECTILE_X_POSITION_OFFSET = -50.0;

	/**
	 * The vertical offset for the projectile spawn.
	 */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 22.0;

	/**
	 * The initial health of the enemy plane.
	 */
	private static final int INITIAL_HEALTH = 1;

	/**
	 * The fire rate of the enemy plane, representing the probability of firing in each frame.
	 */
	private static final double FIRE_RATE = .02;

	/**
	 * Constructor to initialize the enemy plane at a given position with the initial health.
	 *
	 * @param initialXPos the initial X position of the enemy plane.
	 * @param initialYPos the initial Y position of the enemy plane.
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Updates the position of the enemy plane. The enemy plane moves horizontally based on its velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY); // Moves the enemy horizontally
	}

	/**
	 * Fires a projectile from the enemy plane. The projectile is fired based on the fire rate.
	 *
	 * @return a new `EnemyProjectile` if the projectile is fired, otherwise null.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) { // Fire the projectile based on the fire rate probability
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPosition); // Create and return a new projectile
		}
		return null;
	}

	/**
	 * Updates the state of the enemy plane. This includes updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition(); // Update the position of the enemy plane
	}
}
