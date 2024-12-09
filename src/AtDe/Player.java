package AtDe;

public class Player implements Member{
    private Hand hand;
    private String role;
    private boolean success;
    private int deckId;
    private Deck deck;

    Player(int deckId) {
        this.hand = new Hand();
        this.role = "";
        this.success = false;
        this.deckId = deckId;
        deck = null;
    }

    public void collect(Card card) {
        hand.add(card);
    }

    public void getMove(boolean firstMove) {
        if (getRole().equals("attack")) {
            attack(firstMove);
        }
        else {
            defend();
        }
    }

    public void attack(boolean firstMove) {
        if (firstMove) {

        }
        else {

        }
    }

    public void defend() {

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

    private void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public Hand getHand() {
        return hand;
    }

    public int getDeckId() {
        return deckId;
    }

    public NeedToDefend getNeedToDefend() {
        return deck.getNeedToDefend();
    }

    public CardsUsed getCardsUsed() {
        return deck.getCardsUsed();
    }


    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
