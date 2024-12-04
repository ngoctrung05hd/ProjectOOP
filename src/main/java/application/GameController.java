package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameController {

	
    @FXML
    private VBox playerList[];

    @FXML
    private TextArea gameLog;

    @FXML
    private Button playButton;

    @FXML
    private Button passButton;
    @FXML
    private Button resumeButton;

    @FXML
    private Button quitButton;
    @FXML
    private Button pauseButton;
    
    @FXML
    private VBox pauseMenu;
    


    public void handlePickAction(MouseEvent e) {
    	System.out.println("This card was picked!");
    }
    	
    
    public void handlePlayAction() {
        // Xử lý logic khi nhấn nút Play
    	System.out.println("Player played a card!");
    }

    public void handlePassAction() {
        // Xử lý logic khi nhấn nút Pass
       System.out.println("Player passed!");
    }
    
	/*
	 * public void openPauseScene(ActionEvent event) throws IOException { FXMLLoader
	 * loader = new FXMLLoader(getClass().getResource("Pause.fxml")); Parent root =
	 * loader.load();
	 * 
	 * // Lấy PauseController và thiết lập tham chiếu GameController PauseController
	 * pauseController = loader.getController();
	 * pauseController.setGameController(this);
	 * 
	 * Stage newStage = new Stage(); newStage.setScene(new Scene(root));
	 * newStage.setTitle("Pause Menu"); newStage.show(); }
	 */
    
    public void handlePauseAction() {
        pauseMenu.setVisible(true); // Hiển thị giao diện tạm dừng
    }

    // Hàm xử lý khi nhấn nút Resume
    public void handleResumeAction() {
        pauseMenu.setVisible(false); // Ẩn giao diện tạm dừng
    }

    public void switchToMenu(ActionEvent event) throws IOException {
  		Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
  		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  		Scene scene = new Scene(root);
  		stage.setScene(scene);
  		stage.show();
        System.out.println("Switching to menu...");
    }
    
    
    public void closeGameScene(ActionEvent event) {
    	Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
		  }
    }

