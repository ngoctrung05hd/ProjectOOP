package AtDe;

import AtDe.CardGameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CardGameGUI extends Application {
    static Player player;

    @Override
    public void start(Stage primaryStage) throws IOException{
        // Khởi tạo trạng thái các lá bài

        // Khu vực các lá bài "Cần đỡ"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../AtDe/CardGameLayout.fxml"));
        Parent root = loader.load();

        // Lấy Controller và truyền dữ liệu player
        CardGameController controller = loader.getController();

        // Tạo scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Card Game GUI");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void GUI(Player p) {
        player = p;
        main(null);
    }
}