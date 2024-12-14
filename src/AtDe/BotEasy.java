package AtDe;

public class BotEasy implements Member {
    private Hand hand;
    private String role;
    private boolean success;
    private int deckId;
    private Deck deck;

    BotEasy (int deckId) {
        this.hand = new Hand();
        this.role = "";
        this.success = false;
        this.deckId = deckId;
        this.deck = null;
    }

    @Override
    public void getMove(boolean firstMove) {
        hand.sort();
        if (getRole().equals("attack")) {
            attack(firstMove);
        }
        else {
            defend();
        }
    }

    public void collect(Card card) {
        hand.add(card);
    }

    public void attack(boolean firstMove) {
        CardList attackCards = new CardList();
        if (firstMove)
        {
            for (int i = 0; i < hand.size(); ++i)
                if(hand.getFirstCard().getRank() == hand.getCard(i).getRank()) {
                    attackCards.add(hand.getCard(i));
                }

            for (int i = 0; i < attackCards.size(); ++i) {
                hand.remove(attackCards.getCard(i));
            }
        }
        else {
            for (int i = 0; i < hand.size(); ++i){
                    if (deck.checkAttack(hand.getCard(i))) {
                        attackCards.add(hand.getCard(i));
                        hand.remove(i);
                    }
                }
        }
        deck.attack(attackCards);
    }

    public void defend() {
        setSuccess(false);
        NeedToDefend needToDefend = deck.getNeedToDefend();
        for (int i = 0; i < hand.size(); ++i) {
            for (int j = 0; j < needToDefend.size(); ++j) {
                if (deck.checkDefend(needToDefend.getCard(j), hand.getCard(i))) {
                    deck.defend(needToDefend.getCard(j), hand.getCard(i));
                    hand.remove(i);
                    setSuccess(true);
                }
            }
        }
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

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
