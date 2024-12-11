package com.example.demo.model;

import com.example.demo.projectile.UserProjectile;

/**
 * Represents the player's plane in the game. The plane can move in all four directions (up, down, left, right),
 * fire projectiles, and track the number of kills. It inherits from the FighterPlane class and implements specific
 * behavior for user-controlled planes.
 */
public class UserPlane extends FighterPlane {

	/**
	 * The image of the user's plane.
	 */
	private static final String IMAGE_NAME = "userplane.png";

	/**
	 * The upper Y position boundary for the user plane.
	 */
	private static final double Y_UPPER_BOUND = 5;

	/**
	 * The lower Y position boundary for the user plane.
	 */
	private static final double Y_LOWER_BOUND = 560.0;

	/**
	 * The upper X position boundary for the user plane.
	 */
	private static final double X_UPPER_BOUND = 0;

	/**
	 * The lower X position boundary for the user plane.
	 */
	private static final double X_LOWER_BOUND = 600.0;

	/**
	 * The initial X position of the user plane.
	 */
	private static final double INITIAL_X_POSITION = 5.0;

	/**
	 * The initial Y position of the user plane.
	 */
	private static final double INITIAL_Y_POSITION = 300.0;

	/**
	 * The height of the user's plane image.
	 */
	private static final int IMAGE_HEIGHT = 40;

	/**
	 * The vertical velocity of the user's plane.
	 */
	private static final int VERTICAL_VELOCITY = 10;

	/**
	 * The horizontal velocity of the user's plane.
	 */
	private static final int HORIZONTAL_VELOCITY = 10;

	/**
	 * The X position for the projectile spawn.
	 */
	private static final int PROJECTILE_X_POSITION = 140;

	/**
	 * The vertical offset for the projectile spawn.
	 */
	private static final int PROJECTILE_Y_POSITION_OFFSET = 21;

	/**
	 * The multiplier for the vertical velocity (used to determine the speed of vertical movement).
	 */
	private int verticalVelocityMultiplier;

	/**
	 * The multiplier for the horizontal velocity (used to determine the speed of horizontal movement).
	 */
	private int horizontalVelocityMultiplier;

	/**
	 * The number of kills the user plane has achieved.
	 */
	private int numberOfKills;

	/**
	 * Constructor to initialize the user plane with a given initial health.
	 *
	 * @param initialHealth the initial health of the user plane.
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
	}

	/**
	 * Updates the position of the user plane. The plane moves based on the current velocity multipliers
	 * for vertical and horizontal movement. The plane's position is constrained within the defined boundaries.
	 */
	@Override
	public void updatePosition() {
		if (isMovingVertically()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * verticalVelocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
		if (isMovingHorizontally()) {
			double initialTranslateX = getTranslateX();
			this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);
			double newPosition = getLayoutX() + getTranslateX();
			if (newPosition < X_UPPER_BOUND || newPosition > X_LOWER_BOUND) {
				this.setTranslateX(initialTranslateX);
			}
		}
	}

	/**
	 * Updates the state of the user plane by updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Fires a projectile from the user plane.
	 *
	 * @return a new `UserProjectile` object representing the projectile fired by the user plane.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION);
		double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
		return new UserProjectile(projectileXPosition, projectileYPosition);
	}

	/**
	 * Checks if the user plane is moving vertically (i.e., if the vertical velocity multiplier is not zero).
	 *
	 * @return true if the user plane is moving vertically, false otherwise.
	 */
	private boolean isMovingVertically() {
		return verticalVelocityMultiplier != 0;
	}

	/**
	 * Checks if the user plane is moving horizontally (i.e., if the horizontal velocity multiplier is not zero).
	 *
	 * @return true if the user plane is moving horizontally, false otherwise.
	 */
	private boolean isMovingHorizontally() {
		return horizontalVelocityMultiplier != 0;
	}

	/**
	 * Moves the user plane upwards by setting the vertical velocity multiplier to -1.
	 */
	public void moveUp() {
		verticalVelocityMultiplier = -1;
	}

	/**
	 * Moves the user plane downwards by setting the vertical velocity multiplier to 1.
	 */
	public void moveDown() {
		verticalVelocityMultiplier = 1;
	}

	/**
	 * Moves the user plane left by setting the horizontal velocity multiplier to -1.
	 */
	public void moveLeft() {
		horizontalVelocityMultiplier = -1;
	}

	/**
	 * Moves the user plane right by setting the horizontal velocity multiplier to 1.
	 */
	public void moveRight() {
		horizontalVelocityMultiplier = 1;
	}

	/**
	 * Stops the vertical movement of the user plane by setting the vertical velocity multiplier to 0.
	 */
	public void stopVertically() {
		verticalVelocityMultiplier = 0;
	}

	/**
	 * Stops the horizontal movement of the user plane by setting the horizontal velocity multiplier to 0.
	 */
	public void stopHorizontally() {
		horizontalVelocityMultiplier = 0;
	}

	/**
	 * Gets the current number of kills made by the user plane.
	 *
	 * @return the number of kills.
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increments the number of kills by one.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}

	/**
	 * Sets the horizontal velocity multiplier of the user plane.
	 *
	 * @param horizontalVelocityMultiplier the new horizontal velocity multiplier.
	 */
	public void setHorizontalVelocityMultiplier(int horizontalVelocityMultiplier) {
		this.horizontalVelocityMultiplier = horizontalVelocityMultiplier;
	}

	/**
	 * Gets the current horizontal velocity multiplier of the user plane.
	 *
	 * @return the horizontal velocity multiplier.
	 */
	public int getHorizontalVelocityMultiplier() {
		return horizontalVelocityMultiplier;
	}

	/**
	 * Sets the vertical velocity multiplier of the user plane.
	 *
	 * @param verticalVelocityMultiplier the new vertical velocity multiplier.
	 */
	public void setVerticalVelocityMultiplier(int verticalVelocityMultiplier) {
		this.verticalVelocityMultiplier = verticalVelocityMultiplier;
	}

	/**
	 * Gets the current vertical velocity multiplier of the user plane.
	 *
	 * @return the vertical velocity multiplier.
	 */
	public int getVerticalVelocityMultiplier() {
		return verticalVelocityMultiplier;
	}
}
