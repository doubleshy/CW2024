package com.example.demo.model;

/**
 * Represents an object that can be destroyed and take damage.
 * This interface is implemented by any class that has destructible behavior,
 * such as enemies, the player's plane, and other destructible objects in the game.
 */
public interface Destructible {

	/**
	 * Handles the logic when the object takes damage.
	 * The exact behavior will depend on the object (e.g., losing health or being destroyed).
	 */
	void takeDamage();

	/**
	 * Destroys the object. This typically involves marking it as destroyed,
	 * and removing it from the game world.
	 */
	void destroy();
}
