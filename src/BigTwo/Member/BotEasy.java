package BigTwo.Member;

import BigTwo.Components.Hand;
import BigTwo.Core.Card;
import BigTwo.Core.CardList;
import BigTwo.GameLogic.Deck;

public class BotEasy implements Member{
    private Hand hand;
    private String role;
    private boolean success;
    private boolean firstMove = false;
    private Deck deck;
    private boolean endTurn;
    private String name;
    private int id;

    public BotEasy() {
        this.hand = new Hand();
        this.role = "";
        deck = null;
    }

    public void collect(Card card) {
        hand.add(card);
    }

    public void collect(CardList cardList) {
        hand.add(cardList);
    }

    public void getMove() {

    }

    public void attack(CardList cardList) {
        for (int i = 0; i < cardList.size(); ++i)
            hand.remove(cardList.getCard(i));
        deck.move(cardList);
    }

    public void clearHand() {
        hand.removeAll();
    }


    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck() {
        return deck;
    }

    public boolean isEndTurn() {
        return endTurn;
    }

    public void setEndTurn(boolean endTurn) {
        this.endTurn = endTurn;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public int getId() {
    	return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setSuccess(boolean success) {
    	this.success = success;
    }
    
    public boolean getSuccess() {
    	return success;
    }
}