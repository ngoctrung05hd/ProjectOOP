package AtDe;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainHelper extends Application {
    private static Application application; // Lưu giữ ứng dụng cụ thể

    public static void setApplication(Application app) {
        System.out.println("gg");
        application = app;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        if (application == null) {
            throw new IllegalStateException("Application instance chưa được thiết lập!");
        }
        // Gọi start() trên ứng dụng cụ thể
        application.start(primaryStage);
    }
}
