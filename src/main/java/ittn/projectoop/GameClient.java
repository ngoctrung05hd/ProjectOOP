package ittn.projectoop;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;

public class GameClient extends Application {
    private int clientId;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Stage primaryStage;
    private TextField nameField;
    private Button submitButton;

    public GameClient(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Client " + clientId);

        // Tạo giao diện nhập tên
        nameField = new TextField();
        nameField.setPromptText("Nhập tên của bạn");
        submitButton = new Button("Xác nhận");

        submitButton.setOnAction(e -> {
            String playerName = nameField.getText();
            if (!playerName.isEmpty()) {
                sendPlayerName(playerName);
                primaryStage.close(); // Đóng cửa sổ sau khi nhập tên
            }
        });

        VBox layout = new VBox(10, new Label("Nhập tên:"), nameField, submitButton);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Phương thức để kết nối client với server
    public void connectToServer() {
        try {
            socket = new Socket("localhost", 12345); // Kết nối với server
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Sau khi kết nối thành công, chạy giao diện nhập tên trên thread chính
            Platform.runLater(() -> startClientUI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Phương thức để hiển thị giao diện nhập tên, sẽ được gọi từ Platform.runLater()
    private void startClientUI() {
        // Khởi chạy giao diện nhập tên
        launch(); // Điều này sẽ gọi phương thức start() của Application
    }

    // Gửi tên người chơi tới server
    private void sendPlayerName(String name) {
        out.println(name);
    }

    // Phương thức này được gọi từ PlayerSelectionApp để khởi chạy client
    public void startClient() {
        // Mỗi client sẽ chạy trên một thread riêng biệt
        new Thread(() -> connectToServer()).start();
    }

    public static void main(String[] args) {
        // Khởi tạo client và chạy
        int clientId = Integer.parseInt(args[0]); // Client ID sẽ được truyền từ PlayerSelectionApp
        GameClient client = new GameClient(clientId);
        client.startClient(); // Khởi chạy client
    }
}
