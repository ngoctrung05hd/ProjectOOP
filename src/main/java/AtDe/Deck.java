package AtDe;

public class Deck {
    private int playerCount;
    private CardSet cardSet;
    public CardsUsed cardsUsed;
    private NeedToDefend needToDefend;

    Deck() {
        playerCount = 0;
        cardSet = new CardSet();
        cardsUsed = new CardsUsed();
        needToDefend = new NeedToDefend();
    }

    void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public boolean checkAttack(Card card) {
        return cardsUsed.rankUsed(card);
    }

    public void attack(CardList cardList) {
        needToDefend.add(cardList);
        cardsUsed.add(cardList);
    }

    public void defend(Card card, Card cardDefend) {
        needToDefend.remove(card);
        cardsUsed.add(cardDefend);
    }

    public CardList getCards(int count) {
        CardList cardList = new CardList();
        while (count > 0 && cardSet.size() > 0) {
            cardList.add(cardSet.getEnd());
        }
        return cardList;
    }

    public NeedToDefend getNeedToDefend() {
        return needToDefend;
    }
}
