package AtDe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;

public class CardGameUI extends Application {

    // ArrayList to represent the selection state of cards
    private ArrayList<Boolean> cardSelection = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        // Root layout
        BorderPane root = new BorderPane();

        // Top: Display played cards
        HBox playedCards = new HBox(10);
        for (int i = 0; i < 5; i++) {
            Button card = new Button("Đã đánh");
            card.setPrefSize(70, 100);
            playedCards.getChildren().add(card);
        }
        root.setTop(playedCards);

        // Bottom: Display player's hand
        HBox handCards = new HBox(10);
        for (int i = 0; i < 5; i++) {
            ToggleButton card = new ToggleButton("Lá bài " + (i + 1));
            card.setPrefSize(70, 100);
            int index = i;
            card.setOnAction(e -> cardSelection.set(index, card.isSelected())); // Bind selection to ArrayList
            handCards.getChildren().add(card);
            cardSelection.add(false); // Initialize selection state
        }

        // Navigation buttons for hand
        Button leftButton = new Button("<");
        Button rightButton = new Button(">");

        leftButton.setOnAction(e -> System.out.println("Dịch sang trái"));
        rightButton.setOnAction(e -> System.out.println("Dịch sang phải"));

        HBox navigation = new HBox(10, leftButton, handCards, rightButton);
        root.setBottom(navigation);

        // Center: Skip and play buttons
        VBox centerControls = new VBox(10);
        Button skipButton = new Button("Skip");
        Button playButton = new Button("Đánh bài");

        skipButton.setOnAction(e -> System.out.println("Bỏ lượt!"));
        playButton.setOnAction(e -> System.out.println("Đánh bài!"));

        centerControls.getChildren().addAll(skipButton, playButton);
        root.setCenter(centerControls);

        // Right: Display opponent's cards
        VBox opponentCards = new VBox(10);
        for (int i = 0; i < 5; i++) {
            Button card = new Button("Đã đỡ");
            card.setPrefSize(70, 100);
            opponentCards.getChildren().add(card);
        }
        root.setRight(opponentCards);

        // Scene and Stage setup
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Card Game UI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
