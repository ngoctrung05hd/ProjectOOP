package AtDe;

import java.util.ArrayList;

public class Deck {
    private CardSet cardSet;
    private CardsUsed cardsUsed;
    private NeedToDefend needToDefend;
    private ArrayList<Member> members;

    Deck() {
        cardSet = new CardSet();
        cardsUsed = new CardsUsed();
        needToDefend = new NeedToDefend();
        members = new ArrayList<>();
    }

    public void reset() {
        cardSet = new CardSet();
        cardsUsed.reset();
        needToDefend.removeAll();
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

    public CardsUsed getCardsUsed() {
        return cardsUsed;
    }

    public CardSet getCardSet() {
        return cardSet;
    }
}
