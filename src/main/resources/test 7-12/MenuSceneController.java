package application;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class MenuSceneController {	
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private ComboBox<String> numPlayersBox;

	@FXML
	private ComboBox<String> gameModeBox;
	@FXML
	private Button startGameButton; // Nút Start Game
	

	@FXML
	public void initialize() {
	    // Thêm danh sách giá trị vào numPlayersBox
	    numPlayersBox.getItems().addAll("2 Players", "3 Players", "4 Players");

	    // Thêm danh sách giá trị vào gameModeBox
	    gameModeBox.getItems().addAll("Mode 1", "Mode 2");

	    // Gán giá trị mặc định (tùy chọn)
	    numPlayersBox.setOnAction(event -> validateOptions());
        
	    gameModeBox.setOnAction(event -> handleGameModeSelection());
	}
	@FXML
	public void showOptions(ActionEvent event) {
	    // Hiển thị VBox khi nhấn nút Play
	    gameModeBox.setVisible(true);
	    // initialize();
	}
	@FXML
    public void handleGameModeSelection() {
		System.out.print(1);
		String selectedMode = gameModeBox.getValue();
	    if ("Mode 1".equals(selectedMode)) {
	        // Nếu chọn Mode 1, hiển thị ngay nút Start Game
	    	numPlayersBox.setVisible(true);
	        startGameButton.setVisible(false);

	    } else if ("Mode 2".equals(selectedMode)) {
	        // Nếu chọn Mode 2, hiển thị numPlayersBox
	    	numPlayersBox.setVisible(false);
	        startGameButton.setVisible(true);
	    }
    }

	@FXML
    public void validateOptions() {
        // Kiểm tra nếu chọn đủ số người chơi thì hiển thị nút Start Game
        if ("Mode 1".equals(gameModeBox.getValue()) && numPlayersBox.getValue() != null) {
            startGameButton.setVisible(true);
        }
    }

	
	
	public void switchToSceneCardBoard(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("NewCardBoard.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		  System.out.print(numPlayersBox.getValue());
		 }
	public void exit(ActionEvent event) {
    	Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
		  }
    
	
}
