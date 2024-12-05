package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
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
    private ImageView imageView = new ImageView();
    private Image[] cardImages = new Image[52];
    @FXML
    private VBox pauseMenu;
    Image image1 = new Image("file:/C:/Users/ADMIN/eclipse-workspace/ProjectOOP/src/image/card/b.gif");
    
    public int getRandomNumber() {
        Random random = new Random(); 
        return random.nextInt(52); 
    }
    public void initialize() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "t", "j", "q", "k", "a"};
        String[] suits = {"c", "d", "s", "h"};
        int index = 0;
        for (String suit : suits) {
            for (String rank : ranks) {
                String fileName = rank + suit + ".gif"; // Tạo tên file
                String filePath = "file:/C:/Users/ADMIN/eclipse-workspace/ProjectOOP/src/image/card/" + fileName;
                cardImages[index] = new Image(filePath); // Gán hình ảnh vào mảng
                index++;
            }
        }
    }

    public void handlePickAction(MouseEvent e) {
    	System.out.println("This card was picked!");
    }
    public void pickTopCard(MouseEvent e) {
    	if (imageView.getImage() == image1) {
            imageView.setImage(cardImages[getRandomNumber()]); // Thay đổi thành hình ảnh 2
        } else {
            imageView.setImage(image1); // Quay lại hình ảnh 1
        }
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

