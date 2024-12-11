package com.example.demo.projectile;

import com.example.demo.model.ActiveActorDestructible;

/**
 * Represents a generic projectile in the game. This is an abstract class that serves as a base for all projectiles,
 * including those fired by the player and enemies. The projectile moves across the screen and can be destroyed when
 * it collides with an object.
 */
public abstract class Projectile extends ActiveActorDestructible {

	/**
	 * Constructor to initialize a projectile with a specified image, size, and initial position.
	 *
	 * @param imageName the name of the image representing the projectile.
	 * @param imageHeight the height of the projectile's image.
	 * @param initialXPos the initial X position of the projectile.
	 * @param initialYPos the initial Y position of the projectile.
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	/**
	 * Handles damage taken by the projectile. When a projectile takes damage, it is destroyed.
	 */
	@Override
	public void takeDamage() {
		this.destroy(); // Destroys the projectile when it takes damage
	}

	/**
	 * Abstract method for updating the position of the projectile. Each type of projectile (e.g., player's or enemy's)
	 * will implement its own version of this method.
	 */
	@Override
	public abstract void updatePosition();
}
