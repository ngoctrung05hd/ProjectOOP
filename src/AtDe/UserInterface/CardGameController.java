package AtDe.UserInterface;

import java.util.ArrayList;
import java.util.List;

import AtDe.Core.Card;
import AtDe.Core.CardList;
import AtDe.Member.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CardGameController {
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

    private CardList pickedCards = new CardList();
    private CardList defendCards = new CardList();

    @FXML
    private HBox needToDefendCardsBox;
    @FXML
    private HBox usedCardsBox;
    @FXML
    private HBox handBox;
    @FXML
    private HBox speacialCardBox;
    @FXML
    private HBox opponentCard1;
    @FXML
    private HBox opponentCard2;
    @FXML
    private HBox opponentCard3;
    @FXML
    private Button prevNeedToDefendButton, nextNeedToDefendButton, prevUsedButton, nextUsedButton;
    @FXML
    private Button prevButton, nextButton, endTurnButton, playButton;

    @FXML
    private Label remainingCardLabel;
    @FXML
    private Label cardNums1;
    @FXML
    private Label cardNums2;
    @FXML
    private Label cardNums3;

    public List<Player> players;
    
    public ArrayList <Player> playersList;

    public Player player;

    @FXML
    public void initialize() {
    }

    public void display() {
        resize();
        update();
    	updateSpeacialCards();
        playButton.setDisable(true); // Đặt mặc định là không thể đánh bài
        endTurnButton.setOnAction(e -> endTurn());
        playButton.setOnAction(e -> play());

        updateHand();
        updateNeedToDefendCards();
        updateUsedCards();
        updateRemainingCardLabel();
        updateOpponentCard(opponentCard1);
        updateOpponentCard(opponentCard2);
        updateOpponentCard(opponentCard3);
        updateOpponentNumsCard();
    }

    public void setPlayer(Player player) {
        this.player = player;
        display();
    }
    


    public void addPlayersList(ArrayList <Player> playersList) {
    	playersList = new ArrayList<>();
    	for (Player p : playersList)
    		this.playersList.add(p);
    }
    

    private void update() {
    	updateHand();
        updateNeedToDefendCards();
        updateUsedCards();
    }
    private void endTurn() {
        System.out.println("Kết thúc lượt");
        player.setEndTurn(true);
        player.getDeck().endTurn();
    }
    private void play() {
        if (player.getRole().equals("attack")) {
            player.attack(pickedCards);
        }
        else {
            player.defend(defendCards.getFirstCard(), pickedCards.getFirstCard());
        }
    }
    private void draw() {
    	System.out.println("Bốc bài");
    }
    private void resize() {
        setHandCount(player.getHand().size());
        setNeedToDefendCardsCount(player.getNeedToDefend().size());
        setUsedCardsCount(player.getCardsUsed().size());
        handStates.clear();
        needToDefendCardsStates.clear();
        usedCardsStates.clear();
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
        pickedCards.removeAll();
        for (int i = 0; i < handCount; i++) {
            if (handStates.get(i)) {
                pickedCards.add(player.getHand().getCard(i));
            }
        }

        if (pickedCards.size() == 0)
            return false;
        

        if (player.getRole().equals("attack")) {
            if (player.isFirstMove()) {
                return player.getDeck().checkAttackFirstMove(pickedCards);
            }
            else  {
                return player.getDeck().checkAttack(pickedCards);
            }
        }
        else {
            defendCards.removeAll();
            for (int i = 0; i < needToDefendCardsCount; i++) {
                if (needToDefendCardsStates.get(i)) {
                    defendCards.add(player.getNeedToDefend().getCard(i));
                }
            }

            return player.getDeck().checkDefend(defendCards, pickedCards);
        }
    }


    // Hàm cập nhật hiển thị các lá trên tay
    private void updateHand() {
        handBox.getChildren().clear();

        for (int i = 0; i < 8 && i < handCount; i++) {
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
            if(handStates.get(index)) {
                cardButton.setTranslateY(-20);
            } else {
                cardButton.setTranslateY(0);
            }
            cardButton.setOnAction(e -> {
                handStates.set(index, cardButton.isSelected());
                playButton.setDisable(!checkMove());
                System.out.println(player.getNeedToDefend().CardListToString());
                System.out.println(handStates);
                if(handStates.get(index)) {
                    cardButton.setTranslateY(-20);
                } else {
                    cardButton.setTranslateY(0);
                }
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
            Image imageOn = new Image(player.getNeedToDefend().getCard(cardIndex).CardToLink()); // Đường dẫn ảnh "Bật"
            Image imageOff = new Image(player.getNeedToDefend().getCard(cardIndex).CardToLink()); // Đường dẫn ảnh "Tắt"

            // Tạo ImageView cho hai trạng thái
            ImageView imageViewOn = new ImageView(imageOn);
            ImageView imageViewOff = new ImageView(imageOff);
            ToggleButton cardButton = new ToggleButton();
            cardButton.setTranslateX(i * 0);
            cardButton.setSelected(needToDefendCardsStates.get(cardIndex));
            cardButton.setGraphic(imageViewOff);

            int index = cardIndex; // Lưu chỉ số thực tế
            if(needToDefendCardsStates.get(index)) {
                cardButton.setTranslateY(-20);
            } else {
                cardButton.setTranslateY(0);
            }
            cardButton.setOnAction(e -> {
                needToDefendCardsStates.set(index, cardButton.isSelected());
                playButton.setDisable(!checkMove());
                System.out.println(needToDefendCardsStates);
                if(needToDefendCardsStates.get(index)) {
                    cardButton.setTranslateY(-20);
                } else {
                    cardButton.setTranslateY(0);
                }
            });
            needToDefendCardsBox.getChildren().add(cardButton);
        }

        needToDefendCardsBox.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        needToDefendCardsBox.getStyleClass().add("speacial-card-box");
    }

    // Hàm cập nhật hiển thị các lá bài "Đã sử dụng" (Không có tính năng chọn)
    private void updateUsedCards() {
   	 usedCardsBox.getChildren().clear();
   	    for (int i = 0; i < visibleCards && i < usedCardsCount; i++) {
   	        int cardIndex = (startIndexUsed + i) % usedCardsCount; // Lấy chỉ số lá bài (tuần hoàn)
   	        Image imageOn = new Image(player.getCardsUsed().getCard(cardIndex).CardToLink()); // Đường dẫn ảnh "Bật"
   	        Image imageOff = new Image(player.getCardsUsed().getCard(cardIndex).CardToLink()); // Đường dẫn ảnh "Tắt"

   	        // Tạo ImageView cho hai trạng thái
   	        ImageView imageViewOn = new ImageView(imageOn);
   	        ImageView imageViewOff = new ImageView(imageOff);
   	        ToggleButton cardButton = new ToggleButton();
   	        cardButton.setTranslateX(i * 0);
   	        cardButton.setSelected(usedCardsStates.get(cardIndex));
   	        cardButton.setGraphic(imageViewOff);
               /*
   	        int index = cardIndex; // Lưu chỉ số thực tế
   	        cardButton.setOnAction(e -> {
   	            usedCardsStates.set(index, cardButton.isSelected());
   	            System.out.println(usedCardsStates);
   	        });
               */
   	        usedCardsBox.getChildren().add(cardButton);
   	    }

   	    usedCardsBox.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
   	    usedCardsBox.getStyleClass().add("speacial-card-box");
   }
    private void updateSpeacialCards() {
        speacialCardBox.getChildren().clear();
        Card specialCard = player.getDeck().getCardSet().getCard(0);
        Image specialCardImage = new Image(specialCard.CardToLink());
        ImageView imageViewFront = new ImageView(specialCardImage);
        ToggleButton cardButton1 = new ToggleButton();
        cardButton1.setGraphic(imageViewFront);
        speacialCardBox.getChildren().add(cardButton1);
        
        Image backCard = new Image("file:src/image/card/b.gif");
        ImageView imageViewBack = new ImageView(backCard);
        ToggleButton cardButton2 = new ToggleButton();
        cardButton2.setGraphic(imageViewBack);
        speacialCardBox.getChildren().add(cardButton2);
        speacialCardBox.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        speacialCardBox.getStyleClass().add("speacial-card-box");
    }
    // Các hàm dịch bài
    @FXML
    private void shiftHandLeft(ActionEvent e) {
        startIndexHand = (startIndexHand - 1 + handCount) % handCount;
        updateHand();
    }
    @FXML
    private void shiftHandRight(ActionEvent e) {
        startIndexHand = (startIndexHand + 1) % handCount;
        updateHand();
    }
    @FXML
    private void shiftNeedToDefendCardsLeft() {
        startIndexNeedToDefend = (startIndexNeedToDefend - 1 + needToDefendCardsCount) % needToDefendCardsCount;
        updateNeedToDefendCards();
    }
    @FXML
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
    @FXML
    private void shiftUsedCardsLeft() {
        startIndexUsed = (startIndexUsed - 1 + usedCardsCount) % usedCardsCount;
        updateUsedCards();
    }
    @FXML
    private void shiftUsedCardsRight() {
        startIndexUsed = (startIndexUsed + 1) % usedCardsCount;
        updateUsedCards();
    }

    private void updateRemainingCardLabel() {
    	remainingCardLabel.setText("" + player.getDeck().getCardSet().size());
    	remainingCardLabel.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    	remainingCardLabel.getStyleClass().add("text-bordered");
    }
    private void updateOpponentCard(HBox hbox) {
    	hbox.getChildren().clear();
    	Image backCard = new Image("file:src/image/card/b.gif");
        ImageView imageViewBack = new ImageView(backCard);
        ToggleButton cardButton = new ToggleButton();
        cardButton.setGraphic(imageViewBack);
        hbox.getChildren().add(cardButton);
        
    }
    // update số lá bài đối thủ
    private void updateOpponentNumsCard() {
    	int index = player.getId();
    	int count = player.getDeck().getMembers().size();
    	
        cardNums1.setText("0");
        if (count >= 4) {
        	cardNums1.setText("" + player.getDeck().getMember((index + 3) % count).getHand().size());
        }
        cardNums1.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    	cardNums1.getStyleClass().add("text-bordered");

    	
        cardNums2.setText("0");
        if (count >= 3) {
        	cardNums2.setText("" + player.getDeck().getMember((index + 2) % count).getHand().size());
        }
        cardNums2.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    	cardNums2.getStyleClass().add("text-bordered");

    	
        cardNums3.setText("0");
        if (count >= 2) {
        	System.out.println(player.getHand().CardListToString());
        	System.out.println(player.getDeck().getMember((index + 1) % count).getHand().CardListToString());
        	cardNums3.setText("" + player.getDeck().getMember((index + 1) % count).getHand().size());
        }
        cardNums3.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    	cardNums3.getStyleClass().add("text-bordered");
    }
}
