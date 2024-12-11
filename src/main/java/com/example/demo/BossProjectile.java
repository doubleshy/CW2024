package com.example.demo.projectile;

/**
 * Represents a projectile fired by the boss in the game. This projectile moves horizontally across the screen
 * with a specified velocity. It inherits from the `Projectile` class and implements specific behavior for the
 * boss's projectiles.
 */
public class BossProjectile extends Projectile {

	/**
	 * The image of the boss's projectile.
	 */
	private static final String IMAGE_NAME = "fireball.png";

	/**
	 * The height of the boss's projectile image.
	 */
	private static final int IMAGE_HEIGHT = 50;

	/**
	 * The horizontal velocity of the boss's projectile, which determines its speed.
	 */
	private static final int HORIZONTAL_VELOCITY = -15;

	/**
	 * The initial X position of the boss's projectile when it is spawned.
	 */
	private static final int INITIAL_X_POSITION = 700;

	/**
	 * Constructs a `BossProjectile` object at the specified Y position.
	 *
	 * @param initialYPos the initial Y position of the projectile.
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
	}

	/**
	 * Updates the position of the boss's projectile. This method moves the projectile horizontally across the screen
	 * based on the defined horizontal velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY); // Moves the projectile horizontally
	}

	/**
	 * Updates the state of the boss's projectile. This method calls `updatePosition()` to move the projectile.
	 */
	@Override
	public void updateActor() {
		updatePosition(); // Updates the projectile's position
	}
}
