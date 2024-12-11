package com.example.demo.controller;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.demo.controller.EndMenu;


public class Main extends Application {


	private static final int SCREEN_WIDTH = 1200;


	private static final int SCREEN_HEIGHT = 650;


	private static final String TITLE = "Sky Battle";


	private static final String BACKGROUND_IMAGE = "/com/example/demo/images/background.png";


	private Controller myController;


	@Override
	public void start(Stage stage) {
		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);

		myController = new Controller(stage);

		Scene mainMenuScene = initializeMainMenu(stage);
		stage.setScene(mainMenuScene);
		stage.show();
	}


	private Scene initializeMainMenu(Stage stage) {
		Image backgroundImage = new Image(getClass().getResource(BACKGROUND_IMAGE).toExternalForm());
		ImageView backgroundView = new ImageView(backgroundImage);
		backgroundView.setFitWidth(SCREEN_WIDTH);
		backgroundView.setFitHeight(SCREEN_HEIGHT);

		Text titleLabel = new Text("SKY BATTLE");
		titleLabel.setFont(new Font("Arial", 100));
		titleLabel.setStyle("-fx-fill: white; -fx-font-weight: bold;");

		Button startButton = new Button("START");
		startButton.setOnAction(e -> myController.launchGame());
		styleButton(startButton, "#4caf50", "#45a049");

		Button exitButton = new Button("EXIT");
		exitButton.setOnAction(e -> stage.close());
		styleButton(exitButton, "#f44336", "#e53935");

		HBox buttonLayout = new HBox(300, startButton, exitButton);
		buttonLayout.setAlignment(Pos.CENTER);

		VBox layout = new VBox(100, titleLabel, buttonLayout);
		layout.setAlignment(Pos.CENTER);

		StackPane root = new StackPane(backgroundView, layout);
		return new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
	}


	private void styleButton(Button button, String backgroundColor, String hoverColor) {
		button.setStyle("-fx-font-size: 20px; " +
				"-fx-padding: 10px 20px; " +
				"-fx-background-color: " + backgroundColor + "; " +
				"-fx-text-fill: white; " +
				"-fx-border-radius: 5px; " +
				"-fx-background-radius: 5px;");
		button.setOnMouseEntered(e -> button.setStyle("-fx-font-size: 20px; " +
				"-fx-padding: 10px 20px; " +
				"-fx-background-color: " + hoverColor + "; " +
				"-fx-text-fill: white; " +
				"-fx-border-radius: 5px; " +
				"-fx-background-radius: 5px;"));
		button.setOnMouseExited(e -> button.setStyle("-fx-font-size: 20px; " +
				"-fx-padding: 10px 20px; " +
				"-fx-background-color: " + backgroundColor + "; " +
				"-fx-text-fill: white; " +
				"-fx-border-radius: 5px; " +
				"-fx-background-radius: 5px;"));
	}

	public void endGame(String result) {
		EndMenu endMenu = new EndMenu();
		endMenu.showEndScreen(result);
	}


	public static void main(String[] args) {
		launch();
	}
}
