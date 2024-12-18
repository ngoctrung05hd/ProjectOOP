package AtDe.UserInterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import AtDe.GameLogic.Deck;

public class CardGameGUI extends Application {
    static CardGameController controller;
    static Deck deck;

    @Override
    public void start(Stage primaryStage) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CardGameLayout.fxml"));
        Parent root = loader.load();
        if (deck == null) {
        	System.out.println("GG");
        	return;
        }
        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        root.getStyleClass().add("root");


        // Lấy Controller và truyền dữ liệu player
        controller = loader.getController();
        deck.setController(controller);
        controller.addPlayersList(deck.getPlayersList());
        controller.setPlayer(deck.getPlayer(deck.getStartMemberId()));

        // Tạo scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Card Game GUI");
        primaryStage.show();
    }

    public CardGameController getController() {
        return controller;
    }

    public static void main(String[] args) {
        launch(args);
    }
    public static void GUI(Deck deck) {
        CardGameGUI.deck = deck;
        main(null);
    }
}
