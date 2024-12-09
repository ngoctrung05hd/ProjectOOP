package AtDe;

public class BotEasy implements Member {
    private Hand hand;
    private String role;
    private boolean success;
    private int deckId;

    BotEasy (int deckId) {
        this.hand = new Hand();
        this.role = "";
        this.success = false;
        this.deckId = deckId;
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
                    if (Casino.checkAttack((Card) hand.getCard(i), getDeckId())) {
                        attackCards.add((Card) hand.getCard(i));
                        hand.remove(i);
                    }
                }
        }
        Casino.attack(attackCards, getDeckId());
    }

    public void defend() {
        setSuccess(false);
        NeedToDefend needToDefend = Casino.getNeedToDefend(getDeckId());
        for (int i = 0; i < hand.size(); ++i) {
            for (int j = 0; j < needToDefend.size(); ++j) {
                if (((Card)needToDefend.getCard(j)).checkDefend((Card) hand.getCard(i))) {
                    Casino.defend((Card) needToDefend.getCard(j), (Card) hand.getCard(i), getDeckId());
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
}
