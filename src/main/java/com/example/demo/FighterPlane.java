package com.example.demo.model;

/**
 * Represents a fighter plane in the game. This class is the base for both player-controlled and enemy-controlled planes.
 * It manages the plane's health, damage-taking behavior, and projectile firing.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	/**
	 * The health of the fighter plane.
	 */
	private int health;

	/**
	 * Constructs a FighterPlane with the given image, size, position, and health.
	 *
	 * @param imageName the name of the image to be displayed for the fighter plane.
	 * @param imageHeight the height of the fighter plane's image.
	 * @param initialXPos the initial X position of the fighter plane.
	 * @param initialYPos the initial Y position of the fighter plane.
	 * @param health the initial health of the fighter plane.
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Fires a projectile from the fighter plane. This method is abstract and should be implemented by subclasses
	 * to define how the fighter plane fires projectiles.
	 *
	 * @return the projectile fired by the fighter plane.
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Handles taking damage for the fighter plane. The health is decreased, and if the health reaches zero,
	 * the plane is destroyed.
	 */
	@Override
	public void takeDamage() {
		health--; // Decrease health
		if (healthAtZero()) {
			this.destroy(); // Destroy the plane if health reaches zero
		}
	}

	/**
	 * Gets the X position of the projectile based on the fighter plane's position and the provided offset.
	 *
	 * @param xPositionOffset the X offset for the projectile.
	 * @return the calculated X position for the projectile.
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset; // Calculate X position based on current position and offset
	}

	/**
	 * Gets the Y position of the projectile based on the fighter plane's position and the provided offset.
	 *
	 * @param yPositionOffset the Y offset for the projectile.
	 * @return the calculated Y position for the projectile.
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset; // Calculate Y position based on current position and offset
	}

	/**
	 * Checks if the health of the fighter plane has reached zero.
	 *
	 * @return true if the health is zero, false otherwise.
	 */
	private boolean healthAtZero() {
		return health == 0; // Return true if health is zero
	}

	/**
	 * Gets the current health of the fighter plane.
	 *
	 * @return the current health of the fighter plane.
	 */
	public int getHealth() {
		return health;
	}
}
