package com.example.demo.model;

import com.example.demo.projectile.BossProjectile;

import java.util.*;

/**
 * Represents a Boss enemy in the game. The Boss moves vertically, fires projectiles at the player,
 * and can activate a shield at random intervals. The Boss has a health pool and takes damage unless shielded.
 * The Boss follows a predefined movement pattern and can be destroyed once its health reaches zero.
 */
public class Boss extends FighterPlane {

	/**
	 * The image of the Boss.
	 */
	private static final String IMAGE_NAME = "bossplane41.png";

	/**
	 * The initial X position of the Boss on the screen.
	 */
	private static final double INITIAL_X_POSITION = 800.0;

	/**
	 * The initial Y position of the Boss on the screen.
	 */
	private static final double INITIAL_Y_POSITION = 400;

	/**
	 * The vertical offset for projectile spawn.
	 */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 60.0;

	/**
	 * The Boss' fire rate.
	 */
	private static final double BOSS_FIRE_RATE = .04;

	/**
	 * The probability that the Boss will activate its shield.
	 */
	private static final double BOSS_SHIELD_PROBABILITY = .002;

	/**
	 * The height of the Boss image.
	 */
	private static final int IMAGE_HEIGHT = 400;

	/**
	 * The vertical velocity of the Boss' movement.
	 */
	private static final int VERTICAL_VELOCITY = 8;

	/**
	 * The frequency with which the Boss' movement pattern is cycled.
	 */
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;

	/**
	 * The value representing no movement in the vertical direction.
	 */
	private static final int ZERO = 0;

	/**
	 * The maximum number of consecutive frames in the same direction before the Boss changes direction.
	 */
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;

	/**
	 * The upper bound for the Y position of the Boss.
	 */
	private static final int Y_POSITION_UPPER_BOUND = 50;

	/**
	 * The lower bound for the Y position of the Boss.
	 */
	private static final int Y_POSITION_LOWER_BOUND = 510;

	/**
	 * The maximum number of frames the shield can stay activated.
	 */
	private static final int MAX_FRAMES_WITH_SHIELD = 200;

	/**
	 * The movement pattern for the Boss. The Boss moves up and down in a randomized pattern.
	 */
	private final List<Integer> movePattern;

	/**
	 * Flag to indicate if the Boss is shielded.
	 */
	private boolean isShielded;

	/**
	 * The number of consecutive frames that the Boss has moved in the same direction.
	 */
	private int consecutiveMovesInSameDirection;

	/**
	 * The index of the current move in the move pattern.
	 */
	private int indexOfCurrentMove;

	/**
	 * The number of frames that the shield has been activated.
	 */
	private int framesWithShieldActivated;

	/**
	 * Constructor to initialize the Boss with a given health value.
	 *
	 * @param health the initial health of the Boss.
	 */
	public Boss(int health) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, health);
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		isShielded = false;
		initializeMovePattern();
	}

	/**
	 * Updates the position of the Boss. The Boss moves vertically according to the movement pattern.
	 */
	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY); // Reset position if out of bounds
		}
	}

	/**
	 * Updates the state of the Boss, including its position and shield status.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
	}

	/**
	 * Fires a projectile from the Boss. The projectile is only fired based on the Boss' fire rate.
	 *
	 * @return a new BossProjectile if the Boss fires, otherwise null.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
	}

	/**
	 * Takes damage if the Boss is not shielded. If the Boss is shielded, damage is not applied.
	 */
	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
		}
	}

	/**
	 * Initializes the movement pattern of the Boss. The pattern consists of vertical moves (up, down, or no movement).
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	/**
	 * Updates the shield status. If the shield is not activated, there is a chance for the Boss to activate it.
	 * If the shield is activated, it is gradually deactivated after a certain number of frames.
	 */
	void updateShield() {
		if (isShielded) {
			framesWithShieldActivated++;
		} else if (shieldShouldBeActivated()) {
			activateShield(); // Activate shield with probability
		}
		if (shieldExhausted()) {
			deactivateShield(); // Deactivate shield after the max frame limit
		}
	}

	/**
	 * Gets the next vertical move for the Boss based on its movement pattern.
	 *
	 * @return the next vertical move (positive, negative, or zero).
	 */
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	/**
	 * Determines if the Boss fires a projectile in the current frame based on its fire rate.
	 *
	 * @return true if the Boss fires, false otherwise.
	 */
	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	/**
	 * Gets the Y position of the Boss at which the projectile should be fired.
	 *
	 * @return the Y position for the projectile.
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
	 * Determines if the shield should be activated based on a probability.
	 *
	 * @return true if the shield should be activated, false otherwise.
	 */
	private boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	/**
	 * Determines if the shield has been active for the maximum number of frames.
	 *
	 * @return true if the shield has been active for too long, false otherwise.
	 */
	private boolean shieldExhausted() {
		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	/**
	 * Activates the Boss' shield.
	 */
	void activateShield() {
		isShielded = true;
	}

	/**
	 * Deactivates the Boss' shield.
	 */
	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
	}

	/**
	 * Checks if the Boss is currently shielded.
	 *
	 * @return true if the Boss is shielded, false otherwise.
	 */
	public boolean isShielded() {
		return isShielded;
	}


}
