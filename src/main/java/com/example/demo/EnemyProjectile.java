package com.example.demo.projectile;

/**
 * Represents a projectile fired by an enemy plane in the game. This projectile moves horizontally across the screen
 * with a specified velocity. It inherits from the `Projectile` class and implements specific behavior for the
 * enemy's projectiles.
 */
public class EnemyProjectile extends Projectile {

	/**
	 * The image of the enemy's projectile.
	 */
	private static final String IMAGE_NAME = "enemyFire.png";

	/**
	 * The height of the enemy's projectile image.
	 */
	private static final int IMAGE_HEIGHT = 18;

	/**
	 * The horizontal velocity of the enemy's projectile, which determines its speed.
	 */
	private static final int HORIZONTAL_VELOCITY = -10;

	/**
	 * Constructs an `EnemyProjectile` object at the specified X and Y position.
	 *
	 * @param initialXPos the initial X position of the projectile.
	 * @param initialYPos the initial Y position of the projectile.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the enemy's projectile. This method moves the projectile horizontally across the screen
	 * based on the defined horizontal velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY); // Moves the projectile horizontally
	}

	/**
	 * Updates the state of the enemy's projectile. This method calls `updatePosition()` to move the projectile.
	 */
	@Override
	public void updateActor() {
		updatePosition(); // Updates the projectile's position
	}
}
