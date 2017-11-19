package player;



import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MoviePlayer extends Application {
	
	MediaPlayer mp = null;

	@Override
	public void start(Stage stage) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("tubalayout.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Tuba");
		tubalayoutController tlc = new tubalayoutController();
		
		
		scene.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent doubleClicked) {
				if(doubleClicked.getClickCount() == 2) {
					stage.setFullScreen(true);
				}else if(doubleClicked.getClickCount() == 1)
											tlc.getPLAY();
			}
		
				
				
			
		});
		stage.setScene(scene);
		stage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	

}
