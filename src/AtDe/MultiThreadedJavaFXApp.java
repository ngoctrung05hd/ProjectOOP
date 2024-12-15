package AtDe;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MultiThreadedJavaFXApp extends Application {

    private Label statusLabel;

    public static void main(String[] args) {
        launch(args); // Khởi chạy JavaFX Application Thread
    }

    @Override
    public void start(Stage primaryStage) {
        // Giao diện cơ bản
        statusLabel = new Label("Trạng thái: Đang chờ...");
        StackPane root = new StackPane(statusLabel);
        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("JavaFX Multithreading");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Bắt đầu một luồng riêng sau khi giao diện được hiển thị
        startBackgroundThread();
    }

    private void startBackgroundThread() {
        // Tạo một luồng riêng
        Thread backgroundThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(1000); // Giả lập công việc trong luồng bot
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Cập nhật giao diện từ luồng JavaFX
                final int count = i;
                Platform.runLater(() -> statusLabel.setText("Đang xử lý: " + count));
            }

            // Hoàn thành công việc
            Platform.runLater(() -> statusLabel.setText("Hoàn thành!"));
        });

        backgroundThread.setDaemon(true); // Đảm bảo luồng kết thúc khi ứng dụng đóng
        backgroundThread.start(); // Chạy luồng
    }
}
