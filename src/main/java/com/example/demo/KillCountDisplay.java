package com.example.demo.view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * Represents a kill count display in the game, showing the number of kills the player has made
 * and the total number of kills required to advance to the next level.
 * This display is updated dynamically as the player kills enemies.
 */
public class KillCountDisplay extends StackPane {

    /**
     * The X position of the kill count display on the screen.
     */
    private static final int xPosition = 1080;

    /**
     * The Y position of the kill count display on the screen.
     */
    private static final int yPosition = 10;

    /**
     * The initial kill count required to advance to the next level.
     */
    private final int initialKillCount;

    /**
     * The label displaying the kill count text (e.g., "Kills: 0/10").
     */
    private final Label killCountText;

    /**
     * Constructs a `KillCountDisplay` object with the specified initial kill count.
     * The display will show the current kills and the initial kill count.
     *
     * @param initialKillCount the total number of kills required to advance to the next level.
     */
    public KillCountDisplay(int initialKillCount) {
        this.initialKillCount = initialKillCount;
        this.killCountText = new Label("Kills: 0/" + initialKillCount);
        this.killCountText.setStyle("-fx-font-size: 18; -fx-text-fill: black; -fx-font-weight: bold;");
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.getChildren().add(killCountText);
    }

    /**
     * Updates the kill count display with the current kill count.
     * This method updates the label to reflect the player's current progress towards the kill goal.
     *
     * @param killCount the current number of kills the player has achieved.
     */
    public void updateKillCount(int killCount) {
        this.killCountText.setText("Kills: " + killCount + "/" + initialKillCount);
    }
}
