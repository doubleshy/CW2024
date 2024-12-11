package com.example.demo.view;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;

/**
 * Represents the health display of the boss in the game. This class includes a progress bar to represent the
 * boss's health and a label that shows the current and maximum health values.
 */
public class BossHealthDisplay extends StackPane {

    /**
     * The initial health of the boss.
     */
    private final int bossInitialHealth;

    /**
     * The progress bar that displays the boss's health.
     */
    private final ProgressBar healthBar;

    /**
     * The label that displays the current health and maximum health of the boss.
     */
    private final Label healthText;

    /**
     * The width of the health bar.
     */
    private static final int BAR_WIDTH = 370;

    /**
     * The height of the health bar.
     */
    private static final int BAR_HEIGHT = 20;

    /**
     * Constructs a `BossHealthDisplay` object with the specified initial health of the boss.
     * The progress bar is initially set to full (100% health), and the health label is updated to show the boss's health.
     *
     * @param bossHealth the initial health of the boss.
     */
    public BossHealthDisplay(int bossHealth) {
        this.bossInitialHealth = bossHealth;
        this.healthBar = new ProgressBar(1.0);
        this.healthText = new Label(bossHealth + "/" + bossHealth);

        // Style the label
        this.healthText.setStyle("-fx-font-size: 16; -fx-text-fill: white;");

        // Style the progress bar
        this.healthBar.setPrefWidth(BAR_WIDTH);
        this.healthBar.setPrefHeight(BAR_HEIGHT);
        this.healthBar.setStyle("-fx-accent: red; -fx-control-inner-background: transparent;");

        // Add the health bar and label to the layout
        this.getChildren().addAll(healthBar, healthText);
    }

    /**
     * Updates the health bar and label based on the current health of the boss.
     * The health bar is updated to reflect the percentage of remaining health,
     * and the label displays the current health and maximum health.
     *
     * @param bossHealth the current health of the boss.
     */
    public void updateBossHealth(int bossHealth) {
        double progress = (double) bossHealth / bossInitialHealth;
        this.healthBar.setProgress(progress);
        this.healthText.setText(bossHealth + "/" + bossInitialHealth);
    }

    /**
     * Sets the position of the health display on the screen.
     *
     * @param x the X position of the health display.
     * @param y the Y position of the health display.
     */
    public void setLayout(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    /**
     * Hides the health display (progress bar and label).
     */
    public void hideBossHealth() {
        this.setVisible(false);
    }
}
