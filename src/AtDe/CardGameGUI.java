package AtDe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CardGameGUI extends Application {
    private static Player player;
    private final ArrayList<Boolean> handStates = new ArrayList<>();       // Trạng thái các lá bài trên tay
    private final ArrayList<Boolean> needToDefendCardsStates = new ArrayList<>(); // Trạng thái các lá bài "Cần đỡ"
    private final ArrayList<Boolean> usedCardsStates = new ArrayList<>(); // Trạng thái các lá bài "Đã sử dụng"
    private int handCount = 8;       // Tổng số lá bài trên tay
    private int visibleCards = 5;     // Số lá bài hiển thị trong mỗi khu vực
    private int needToDefendCardsCount = 8; // Tổng số lá bài "Cần đỡ"
    private int usedCardsCount = 8; // Tổng số lá bài "Đã sử dụng"
    private int startIndexHand = 0;             // Vị trí bắt đầu hiển thị lá bài trên tay (0-based)
    private int startIndexNeedToDefend = 0;           // Vị trí bắt đầu hiển thị lá bài "Cần đỡ"
    private int startIndexUsed = 0;         // Vị trí bắt đầu hiển thị lá bài "Đã sử dụng"

    private HBox handBox;                       // HBox chứa các lá bài hiển thị trên tay
    private HBox needToDefendCardsBox;          // HBox chứa các lá bài "Cần đỡ"
    private HBox usedCardsBox;                  // HBox chứa các lá bài "Đã sử dụng"

    private Button playButton;

    @Override
    public void start(Stage primaryStage) {
        // Khởi tạo trạng thái các lá bài
        resize();

        // Khu vực các lá bài "Cần đỡ"
        System.out.println(player);
        needToDefendCardsBox = new HBox(10);
        needToDefendCardsBox.setAlignment(Pos.CENTER);
        needToDefendCardsBox.setPadding(new Insets(10));
        updateNeedToDefendCards();

        // Điều hướng cho "Cần đỡ"
        Button prevNeedToDefendButton = new Button("<");
        Button nextNeedToDefendButton = new Button(">");
        prevNeedToDefendButton.setOnAction(e -> shiftNeedToDefendCardsLeft());
        nextNeedToDefendButton.setOnAction(e -> shiftNeedToDefendCardsRight());

        // HBox chứa nút điều hướng và các lá bài "Cần đỡ"
        HBox needToDefendControlBox = new HBox(10, prevNeedToDefendButton, needToDefendCardsBox, nextNeedToDefendButton);
        needToDefendControlBox.setAlignment(Pos.CENTER);

        // Khu vực các lá bài "Đã sử dụng" (không có tính năng chọn)
        usedCardsBox = new HBox(10);
        usedCardsBox.setAlignment(Pos.CENTER);
        usedCardsBox.setPadding(new Insets(10));
        updateUsedCards();

        // Điều hướng cho "Đã sử dụng"
        Button prevUsedButton = new Button("<");
        Button nextUsedButton = new Button(">");
        prevUsedButton.setOnAction(e -> shiftUsedCardsLeft());
        nextUsedButton.setOnAction(e -> shiftUsedCardsRight());

        // HBox chứa nút điều hướng và các lá bài "Đã sử dụng"
        HBox usedControlBox = new HBox(10, prevUsedButton, usedCardsBox, nextUsedButton);
        usedControlBox.setAlignment(Pos.CENTER);

        // Gộp 2 hộp "Đã đánh" và "Đã đỡ" thành 1 HBox
        HBox topSection = new HBox(20, needToDefendControlBox, usedControlBox);
        topSection.setPadding(new Insets(10));
        topSection.setAlignment(Pos.CENTER);
        topSection.setSpacing(20);

        // Đặt tỷ lệ kích thước (2/5 mỗi hộp)
        needToDefendCardsBox.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.4));
        usedCardsBox.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.4));
        HBox.setHgrow(needToDefendCardsBox, Priority.ALWAYS);
        HBox.setHgrow(usedCardsBox, Priority.ALWAYS);

        // Khu vực các lá bài bên dưới (hiển thị 5 lá bài trong số 8 lá bài, có thể dịch trái/phải)
        handBox = new HBox(-50);
        handBox.setAlignment(Pos.CENTER);
        handBox.setPadding(new Insets(0));
        updateHand();

        // Các nút điều hướng (dịch các lá bài)
        Button prevButton = new Button("<");
        Button nextButton = new Button(">");
        prevButton.setOnAction(e -> shiftHandLeft());
        nextButton.setOnAction(e -> shiftHandRight());

        // Nút Skip và Đánh bài
        VBox actionButtons = new VBox(10);
        actionButtons.setAlignment(Pos.CENTER);

        Button skipButton = new Button("Skip");
        playButton = new Button("Đánh bài");
        skipButton.setOnAction(e -> System.out.println("Bỏ lượt"));
        playButton.setDisable(!checkMove());
        playButton.setOnAction(e -> System.out.println("Đánh bài"));

        actionButtons.getChildren().addAll(skipButton, playButton);

        // Tách riêng nút "Bốc bài"
        Button drawCardButton = new Button("Bốc bài");
        drawCardButton.setOnAction(e -> System.out.println("Bốc bài"));

        // Gộp điều hướng và các lá bài hiện tại
        HBox navigationBox = new HBox(10, prevButton, handBox, nextButton);
        navigationBox.setAlignment(Pos.CENTER);

        // Bố cục chính
        VBox mainLayout = new VBox(20, topSection, navigationBox, actionButtons, drawCardButton);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));

        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Card Game GUI");
        primaryStage.show();
    }

    private void resize() {
        setHandCount(player.getHand().size());
        setNeedToDefendCardsCount(player.getNeedToDefend().size());
        setUsedCardsCount(player.getCardsUsed().size());
        for (int i = 0; i < handCount; i++) {
            handStates.add(false); // Trạng thái mặc định
        }
        for (int i = 0; i < needToDefendCardsCount; i++) {
            needToDefendCardsStates.add(false); // Trạng thái mặc định cho "Cần đỡ"
        }
        for (int i = 0; i < usedCardsCount; i++) {
            usedCardsStates.add(false); // Trạng thái mặc định cho "Đã sử dụng"
        }
    }

    private boolean checkMove() {
        int count = 0;
        for (int i = 0; i < handCount; i++) {
            if (handStates.get(i) == true) {
                ++count;
            }
        }
        return (count == 1);
    }


    // Hàm cập nhật hiển thị các lá trên tay
    private void updateHand() {
        handBox.getChildren().clear();

        for (int i = 0; i < visibleCards && i < handCount; i++) {
            int cardIndex = (startIndexHand + i) % handCount; // Lấy chỉ số lá bài (tuần hoàn)
            Image imageOn = new Image(player.getHand().getCard(cardIndex).CardToLink()); // Đường dẫn ảnh "Bật"
            Image imageOff = new Image(player.getHand().getCard(cardIndex).CardToLink()); // Đường dẫn ảnh "Tắt"

            // Tạo ImageView cho hai trạng thái
            ImageView imageViewOn = new ImageView(imageOn);
            ImageView imageViewOff = new ImageView(imageOff);
            ToggleButton cardButton = new ToggleButton();
            cardButton.setTranslateX(i * 0);
            cardButton.setSelected(handStates.get(cardIndex));
            cardButton.setGraphic(imageViewOff);

            int index = cardIndex; // Lưu chỉ số thực tế
            //cardButton.setOnAction(e -> handStates.set(index, cardButton.isSelected()));
            cardButton.setOnAction(e -> {
                handStates.set(index, cardButton.isSelected());
                playButton.setDisable(!checkMove());
                System.out.println(handStates);
            });
            handBox.getChildren().add(cardButton);
        }

        handBox.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    // Hàm cập nhật hiển thị các lá bài "Cần đỡ"
    private void updateNeedToDefendCards() {
        needToDefendCardsBox.getChildren().clear();

        for (int i = 0; i < visibleCards && i < needToDefendCardsCount; i++) {
            int cardIndex = (startIndexNeedToDefend + i) % needToDefendCardsCount; // Lấy chỉ số lá bài (tuần hoàn)
            ToggleButton cardButton = new ToggleButton(player.getNeedToDefend().getCard(cardIndex).CardToString());
            cardButton.setSelected(needToDefendCardsStates.get(cardIndex));
            int index = cardIndex; // Lưu chỉ số thực tế
            cardButton.setOnAction(e -> {
                needToDefendCardsStates.set(index, cardButton.isSelected());
                playButton.setDisable(!checkMove());
            });
            needToDefendCardsBox.getChildren().add(cardButton);
        }
    }

    // Hàm cập nhật hiển thị các lá bài "Đã sử dụng" (Không có tính năng chọn)
    private void updateUsedCards() {
        usedCardsBox.getChildren().clear();

        for (int i = 0; i < visibleCards && i < usedCardsCount; i++) {
            int cardIndex = (startIndexUsed + i) % usedCardsCount; // Lấy chỉ số lá bài (tuần hoàn)
            Button cardButton = new Button(player.getCardsUsed().getCard(cardIndex).CardToString());
            cardButton.setDisable(true);  // Không thể chọn
            usedCardsBox.getChildren().add(cardButton);
        }
    }

    private void updateButtonUI(HBox buttonBox, ArrayList<Boolean> buttonStates) {
        for (int i = 0; i < buttonBox.getChildren().size(); i++) {
            ToggleButton toggleButton = (ToggleButton) buttonBox.getChildren().get(i);
            toggleButton.setSelected(buttonStates.get(i));
        }
    }

    // Các hàm dịch bài
    private void shiftHandLeft() {
        startIndexHand = (startIndexHand - 1 + handCount) % handCount;
        updateHand();
    }

    private void shiftHandRight() {
        startIndexHand = (startIndexHand + 1) % handCount;
        updateHand();
    }

    private void shiftNeedToDefendCardsLeft() {
        startIndexNeedToDefend = (startIndexNeedToDefend - 1 + needToDefendCardsCount) % needToDefendCardsCount;
        updateNeedToDefendCards();
    }

    private void shiftNeedToDefendCardsRight() {
        startIndexNeedToDefend = (startIndexNeedToDefend + 1) % needToDefendCardsCount;
        updateNeedToDefendCards();
    }

    public void setVisibleCards(int visibleCards) {
        this.visibleCards = visibleCards;
    }

    public void setHandCount(int handCount) {
        this.handCount = handCount;
    }

    public void setNeedToDefendCardsCount(int needToDefendCardsCount) {
        this.needToDefendCardsCount = needToDefendCardsCount;
    }

    public void setUsedCardsCount(int usedCardsCount) {
        this.usedCardsCount = usedCardsCount;
    }

    private void shiftUsedCardsLeft() {
        startIndexUsed = (startIndexUsed - 1 + usedCardsCount) % usedCardsCount;
        updateUsedCards();
    }

    private void shiftUsedCardsRight() {
        startIndexUsed = (startIndexUsed + 1) % usedCardsCount;
        updateUsedCards();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void GUI(Player p) {
        player= p;
        main(null);
    }
}
