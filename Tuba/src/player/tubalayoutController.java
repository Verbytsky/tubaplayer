package player;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;

public class tubalayoutController implements Initializable{
	
	
	public MediaPlayer mediaplayer;
	
	@FXML
	private MediaView mediaView;
	
	private String filePath;
	
	@FXML
	private Slider volumeSlider;
	
	@FXML
	private Slider seekSlider;
	

    @FXML
    void handleButtonAction(ActionEvent event) {
		FileChooser filechooser = new FileChooser();
//		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a file (*.mp4)", "mp4");
//			filechooser.getExtensionFilters().add(filter);
			File file = filechooser.showOpenDialog(null);
			filePath = file.toURI().toString();
			
			if(filePath != null) {
			Media media = new Media(filePath);
			mediaplayer = new MediaPlayer(media);
			mediaView.setMediaPlayer(mediaplayer);
				DoubleProperty width = mediaView.fitWidthProperty();
				DoubleProperty height = mediaView.fitHeightProperty();
				
				width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
				height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
				
				volumeSlider.setValue(mediaplayer.getVolume() * 100);
				volumeSlider.valueProperty().addListener(new InvalidationListener(){

					@Override
					public void invalidated(Observable arg0) {
						mediaplayer.setVolume(volumeSlider.getValue()/100);
						
					}
					
				});
				
				mediaplayer.currentTimeProperty().addListener(new ChangeListener<Duration>(){

					@Override
					public void changed(ObservableValue<? extends Duration> observable, Duration oldduration, Duration newduration) {
						seekSlider.maxProperty().bind(Bindings.createDoubleBinding(
							    () -> mediaplayer.getTotalDuration().toSeconds(),
							    mediaplayer.totalDurationProperty()));
//						seekSlider.setValue(newduration.toSeconds());

						
					}
					
				});
				seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent mouseEvent) {
						mediaplayer.seek(Duration.seconds(seekSlider.getValue()));

					}
				});
				
				
				mediaplayer.play();
			
			}
	}
    
    public MediaPlayer getPLAY() {
      	mediaplayer.getStatus(); 
        	mediaplayer.pause();
        	
//    	else {
//    		mediaplayer.play();
//    		mediaplayer.setRate(1.0);
//    		
//    	}
    	return mediaplayer;
    }
    
    @FXML
    public void playVideo(ActionEvent event) {
    	
    	if (mediaplayer.getStatus().equals(Status.PLAYING)) {
        	mediaplayer.pause();
        	
    	}else {
    		mediaplayer.play();
    		mediaplayer.setRate(1.0);
    		
    	}

    }
    
    @FXML
    private void stopVideo(ActionEvent event) {
    	
    	mediaplayer.stop();
    	
    }
    
    @FXML
    private void normalVideo(ActionEvent event) {
    	
    	mediaplayer.setRate(1.0);
    }
    
    @FXML
    private void fastVideo(ActionEvent event) {
    	
    	mediaplayer.setRate(1.25);
    }
    
    @FXML
    private void fasterVideo(ActionEvent event) {
    	
    	mediaplayer.setRate(1.5);
    	
    }
    
    @FXML
    private void fastestVideo(ActionEvent event) {
    	
    	mediaplayer.setRate(2.0);
    }
    
    @FXML
    private void slowVideo(ActionEvent event) {
    	
    	mediaplayer.setRate(.75);
    	
    }
    
    @FXML
    private void slowerVideo(ActionEvent event) {
    	
    	mediaplayer.setRate(.5);
    	
    }
    
    @FXML
    private void exitVideo(ActionEvent event) {
    	
    	System.exit(0);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
		
}
