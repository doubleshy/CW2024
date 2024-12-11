package com.example.demo.projectile;

/**
 * Represents a projectile fired by the user plane in the game. This projectile moves horizontally across the screen
 * with a specified velocity. It inherits from the `Projectile` class and implements specific behavior for the
 * user's projectiles.
 */
public class UserProjectile extends Projectile {

	/**
	 * The image of the user's projectile.
	 */
	private static final String IMAGE_NAME = "userfire.png";

	/**
	 * The height of the user's projectile image.
	 */
	private static final int IMAGE_HEIGHT = 12;

	/**
	 * The horizontal velocity of the user's projectile, which determines its speed.
	 */
	private static final int HORIZONTAL_VELOCITY = 15;

	/**
	 * Constructs a `UserProjectile` object at the specified X and Y position.
	 *
	 * @param initialXPos the initial X position of the projectile.
	 * @param initialYPos the initial Y position of the projectile.
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the user's projectile. This method moves the projectile horizontally across the screen
	 * based on the defined horizontal velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY); // Moves the projectile horizontally
	}

	/**
	 * Updates the state of the user's projectile. This method calls `updatePosition()` to move the projectile.
	 */
	@Override
	public void updateActor() {
		updatePosition(); // Updates the projectile's position
	}
}
