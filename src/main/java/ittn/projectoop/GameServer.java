package ittn.projectoop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GameServer extends Application {
    private int playerCount = 0;
    private TextField[] playerNames;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game Server");

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new javafx.geometry.Insets(20));

        // ComboBox để chọn số người chơi
        ComboBox<Integer> playerCountComboBox = new ComboBox<>();
        for (int i = 1; i <= 4; i++) {
            playerCountComboBox.getItems().add(i);
        }
        playerCountComboBox.setPromptText("Chọn số người chơi");

        // Button để xác nhận
        Button confirmButton = new Button("Xác nhận");
        confirmButton.setOnAction(e -> {
            playerCount = playerCountComboBox.getValue();

            showPlayerNameInput(primaryStage);

        });

        layout.getChildren().addAll(playerCountComboBox, confirmButton);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showPlayerNameInput(Stage primaryStage) {
        Stage nameInputStage = new Stage();
        nameInputStage.setTitle("Nhập tên người chơi");

        VBox layout = new VBox(10);
        layout.setPadding(new javafx.geometry.Insets(20));

        playerNames = new TextField[playerCount];
        for (int i = 0; i < playerCount; i++) {
            playerNames[i] = new TextField();
            playerNames[i].setPromptText("Tên người chơi " + (i + 1));
            layout.getChildren().add(playerNames[i]);
        }

        Button startGameButton = new Button("Bắt đầu trò chơi");
        startGameButton.setOnAction(e -> {
            String[] names = new String[playerCount];
            for (int i = 0; i < playerCount; i++) {
                names[i] = playerNames[i].getText();
            }
            startGame(names);
            nameInputStage.close();
        });

        layout.getChildren().add(startGameButton);
        Scene scene = new Scene(layout, 300, 200);
        nameInputStage.setScene(scene);
        nameInputStage.show();
    }

    private void startGame(String[] playerNames) {
        // Màn hình trò chơi
        Stage gameStage = new Stage();
        gameStage.setTitle("Màn hình trò chơi");

        VBox layout = new VBox(10);
        layout.setPadding(new javafx.geometry.Insets(20));

        Label label = new Label("Chào mừng đến với trò chơi!");
        layout.getChildren().add(label);

        for (String name : playerNames) {
            layout.getChildren().add(new Label("Người chơi: " + name));
        }

        Scene scene = new Scene(layout, 400, 300);
        gameStage.setScene(scene);
        gameStage.show();
    }
}