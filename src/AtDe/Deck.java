package AtDe;

import java.util.ArrayList;

public class Deck implements Base.Deck{
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

    public boolean checkAttack(CardList attackCards) {
        for (int i = 0; i < attackCards.size(); ++i) {
            if (!checkAttack(attackCards.getCard(i)))
                return false;
        }
        return true;
    }

    public boolean checkAttackFirstMove(CardList attackCards) {
        if (attackCards.size() <= 0)
            return false;
        for (int i = 0; i < attackCards.size(); ++i) {
            if (attackCards.getCard(0).getRank() != attackCards.getCard(i).getRank())
                return false;
        }
        return true;
    }

    public boolean checkDefend(Card needToDefendCard, Card card) {
        return card.checkDefend(needToDefendCard);
    }

    public boolean checkDefend(CardList needToDefendCards, CardList pickedCards) {
        return needToDefendCards.size() == 1 && pickedCards.size() == 1 && checkDefend(needToDefendCards.getFirstCard(), pickedCards.getFirstCard());
    }

    public void attack(CardList cardList) {
        needToDefend.add(cardList);
        cardsUsed.add(cardList);
    }

    public void defend(Card card, Card cardDefend) {
        needToDefend.remove(card);
        cardsUsed.add(cardDefend);
    }

    private CardList getCards(int count) {
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
