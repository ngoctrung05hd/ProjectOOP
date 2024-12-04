package ittn.projectoop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PlayerSelectionApp extends Application {
    private int numPlayers;

    @Override
    public void start(Stage primaryStage) {
        // Giao diện cho việc chọn số người chơi
        TextField playerCountField = new TextField();
        playerCountField.setPromptText("Nhập số người chơi (1-4)");

        Button submitButton = new Button("Chọn");
        submitButton.setOnAction(e -> {
            try {
                numPlayers = Integer.parseInt(playerCountField.getText());
                if (numPlayers < 1 || numPlayers > 4) {
                    showAlert("Lỗi", "Số người chơi phải từ 1 đến 4.");
                } else {
                    for (int i = 0; i < numPlayers; i++) {
                        // Khởi tạo một client mới và bắt đầu kết nối
                        GameClient client = new GameClient(i + 1);
                        client.startClient(); // Gọi phương thức startClient()
                    }
                }
            } catch (NumberFormatException ex) {
                showAlert("Lỗi", "Vui lòng nhập một số hợp lệ.");
            }
        });

        VBox layout = new VBox(10, new Label("Nhập số người chơi:"), playerCountField, submitButton);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chọn số người chơi");
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
