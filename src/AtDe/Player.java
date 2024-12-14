package AtDe;

public class Player implements Member{
    private Hand hand;
    private String role;
    private boolean success;
    private boolean firstMove = false;
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
        setFirstMove(firstMove);
    }

    public void attack(CardList attackCards) {
        setFirstMove(false);
        for (int i = 0; i < attackCards.size(); ++i)
            hand.remove(attackCards.getCard(i));
        deck.attack(attackCards);
    }

    public void defend(Card needToDefend, Card card) {
        deck.defend(needToDefend, card);
        hand.remove(card);
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

    public boolean isSuccess() {
        return success;
    }
}
