package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PauseController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private GameController gameController;
	
	
	public void setGameController(GameController gameController) {
		  this.gameController = gameController;
		
	}
	public void exitPause(ActionEvent event) throws IOException {
		  stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
          stage.close();
		  }
	public void switchToMenu(ActionEvent event) throws IOException {
		  if (gameController != null) {	
		  gameController.closeGameScene(event);
          }
		  root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		  	  
		 }
	
	
}
