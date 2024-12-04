package AtDe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class gameMenu extends Application {
    // Số người chơi tối thiểu và tối đa
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;

    private boolean isGraphicMode = true; // Chế độ mặc định là đồ họa
    private int selectedPlayers = MIN_PLAYERS; // Giá trị mặc định

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chọn số người chơi");

        // Tạo và thiết lập giao diện ban đầu
        primaryStage.setScene(createScene());
        primaryStage.show();
    }

    private Scene createScene() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20;");

        // Tiêu đề
        Label titleLabel = new Label("Chọn số người chơi");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        layout.getChildren().add(titleLabel);

        // Chế độ đồ họa
        if (isGraphicMode) {
            ImageView imageView = new ImageView(new Image("https://via.placeholder.com/150"));
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);
            layout.getChildren().add(imageView);
        }

        // Bộ chọn số người chơi
        ComboBox<Integer> playerSelector = new ComboBox<>();
        for (int i = MIN_PLAYERS; i <= MAX_PLAYERS; i++) {
            playerSelector.getItems().add(i);
        }
        playerSelector.setValue(selectedPlayers);
        playerSelector.setOnAction(e -> selectedPlayers = playerSelector.getValue());
        layout.getChildren().add(playerSelector);

        // Các nút bấm
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button confirmButton = new Button("Xác nhận");
        confirmButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Bạn đã chọn " + selectedPlayers + " người chơi!",
                    ButtonType.OK);
            alert.showAndWait();
        });

        Button cancelButton = new Button("Hủy");
        cancelButton.setOnAction(e -> ((Stage) cancelButton.getScene().getWindow()).close());

        Button toggleModeButton = new Button("Chuyển chế độ");
        toggleModeButton.setOnAction(e -> {
            isGraphicMode = !isGraphicMode;
            ((Stage) toggleModeButton.getScene().getWindow()).setScene(createScene());
        });

        buttonBox.getChildren().addAll(confirmButton, cancelButton, toggleModeButton);
        layout.getChildren().add(buttonBox);

        return new Scene(layout, 400, 300);
    }
}
