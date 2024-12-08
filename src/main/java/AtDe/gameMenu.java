package AtDe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class gameMenu extends Application {

    // Mảng trạng thái boolean
    private final List<Boolean> buttonStates = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        // Số lượng nút
        int numberOfButtons = 5;

        // Khởi tạo mảng trạng thái và giao diện
        HBox buttonBox = new HBox(10); // HBox chứa các nút bấm

        for (int i = 0; i < numberOfButtons; i++) {
            buttonStates.add(false); // Trạng thái mặc định là false

            // Tạo nút bấm ToggleButton
            ToggleButton toggleButton = new ToggleButton("Button " + (i + 1));
            toggleButton.setSelected(false);

            int index = i; // Lưu chỉ số của nút

            // Xử lý sự kiện khi nút được bấm
            toggleButton.setOnAction(e -> {
                updateButtonStates(index);
                updateButtonUI(buttonBox);
            });

            buttonBox.getChildren().add(toggleButton);
        }

        // Hiển thị giao diện
        Scene scene = new Scene(buttonBox, 400, 100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Toggle Buttons Example");
        primaryStage.show();
    }

    // Cập nhật trạng thái của các nút
    private void updateButtonStates(int selectedIndex) {
        for (int i = 0; i < buttonStates.size(); i++) {
            buttonStates.set(i, i == selectedIndex); // Chỉ nút được chọn mới chuyển sang true
        }
    }

    // Cập nhật giao diện các nút theo trạng thái
    private void updateButtonUI(HBox buttonBox) {
        for (int i = 0; i < buttonBox.getChildren().size(); i++) {
            ToggleButton toggleButton = (ToggleButton) buttonBox.getChildren().get(i);
            toggleButton.setSelected(buttonStates.get(i));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
