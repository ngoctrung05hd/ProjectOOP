package Base;

import java.util.ArrayList;
import java.util.List;

public class CardList {
    protected List<Card> cards;

    protected CardList() {
        cards = new ArrayList<>();
    }

    public void sort() {
        cards.sort((c1, c2) -> {
        if (c1 instanceof BigTwo.Card && c2 instanceof BigTwo.Card) {
            return ((BigTwo.Card) c1).compareTo((BigTwo.Card) c2);
        }
        else if (c1 instanceof AtDe.Card && c2 instanceof AtDe.Card) {
            return ((AtDe.Card) c1).compareTo((AtDe.Card) c2);
        }
        else {
            return 0;
        }
    });
    }

    protected int getSize() {
        return cards.size();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void add(CardList cardList) {
        cards.addAll(cardList.getAll());
        cardList.removeAll();
    }

    protected List<Card> getAll() {
        return cards;
    }

    protected void remove(Card card) {
        cards.remove(card);
    }

    protected void remove(int index) {
        cards.remove(index);
    }

    protected void removeAll() {
        cards.clear();
    }

    public String CardListToString() {
        String listCards = "";
        for (Card card : cards) {
            listCards += card.CardToString() + " ";
        }
        return listCards;
    }

    protected int size() {
        return cards.size();
    }
}
