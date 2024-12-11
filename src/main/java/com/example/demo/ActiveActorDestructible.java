package com.example.demo.model;

/**
 * Represents an active actor that can be destroyed in the game. This class extends the ActiveActor class
 * and implements the Destructible interface, allowing it to be damaged and destroyed.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	/**
	 * Flag to indicate whether the actor is destroyed.
	 */
	private boolean isDestroyed;

	/**
	 * Constructs an ActiveActorDestructible with the given image, size, and initial position.
	 * The actor is initially not destroyed.
	 *
	 * @param imageName the name of the image to be displayed for the actor.
	 * @param imageHeight the height of the actor's image.
	 * @param initialXPos the initial X position of the actor.
	 * @param initialYPos the initial Y position of the actor.
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false; // Actor is initially not destroyed
	}

	/**
	 * Updates the position of the actor. This method must be implemented by subclasses.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Updates the state of the actor. This method must be implemented by subclasses.
	 */
	public abstract void updateActor();

	/**
	 * Handles the logic when the actor takes damage. This method must be implemented by subclasses.
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Destroys the actor by setting its `isDestroyed` flag to true.
	 */
	@Override
	public void destroy() {
		setDestroyed(true); // Mark the actor as destroyed
	}

	/**
	 * Sets the `isDestroyed` flag to the given value.
	 *
	 * @param isDestroyed the value to set the `isDestroyed` flag to.
	 */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * Returns whether the actor is destroyed.
	 *
	 * @return true if the actor is destroyed, false otherwise.
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
}
