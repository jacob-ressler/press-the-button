import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This is a small JavaFX application I made for one of my
 * college courses. The goal is to press the "PRESS ME" button
 * 10 times as quickly as possible. Every time the button is 
 * pressed, it will be moved to a new location in the scene.
 * After 10 presses, the time will be displayed. The replay button
 * will start a new game when pressed.
 * 
 * Created on April 5, 2017
 * @author Jacob Ressler
 * 
 */
public class ButtonGame extends Application {
	long startTime;
	long endTime;
	int count = 0;
	Random random = new Random();
	int width = 720;
	int height = 480;
	Button gameButton;
	Button retryButton;
	Pane pane;
	Text score;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		pane = new Pane();
		score = new Text();
		gameButton = new Button("PRESS ME");
		retryButton = new Button("RETRY");
		relocate(retryButton, width/2, height/2 + 30);
		relocate(gameButton, random.nextInt(width - 80) + 40, random.nextInt(height - 40) + 20);
		hide(retryButton);
		score.setStyle("-fx-font: 72 impact; -fx-alignment: center;");
		relocate(score, width/4, height/2 - 30);
		
		// Game logic for the "PRESS ME" button
		gameButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (count == 0) startTime = System.currentTimeMillis();
				if (count == 9) {
					endTime = System.currentTimeMillis();
					hide(gameButton);
					show(retryButton);
					score.setText("Time: " + getSeconds(startTime, endTime) + "s");
				}
				relocate(gameButton, random.nextInt(width - 80) + 40, random.nextInt(height - 40) + 20);
				count++;
			}
		});
		
		// Reset the game when the retry button is pressed.
		retryButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				hide(retryButton);
				show(gameButton);
				score.setText("");
				count = 0;
			}
		});
		
		// Application window set-up
		pane.getChildren().addAll(gameButton, retryButton, score);
		
		Scene scene = new Scene(pane, width, height);
		primaryStage.setTitle("Button Game");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	// Move the "PRESS ME" button to the specified (x,y)
	void relocate(Button b, int x, int y) {
		b.setTranslateX(x);
		b.setTranslateY(y);
	}
	
	// Move the time text to the specified (x,y)
	void relocate(Text t, int x, int y) {
		t.setX(x);
		t.setY(y);
	}
	
	// Hide the button
	void hide(Button b) {
		b.setVisible(false);
		b.setDisable(true);
	}
	
	// Show the button
	void show(Button b) {
		b.setVisible(true);
		b.setDisable(false);
	}
	
	// Get the duration of the game as seconds.
	float getSeconds(long s, long e) {
		return ((float)(e - s) / 1000);
	}
	
	// Main
	public static void main (String[] args) {
		launch(args);
	}

}
