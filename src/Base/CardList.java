package Base;

import java.util.ArrayList;
import java.util.List;

public class CardList {
    protected List<Card> cards;

    public CardList() {
        cards = new ArrayList<>();
    }

    public void sort() {
        cards.sort((c1, c2) -> {
            if (c1 instanceof AtDe.Card && c2 instanceof AtDe.Card) {
                return ((AtDe.Card) c1).compareTo((AtDe.Card) c2);
            }
            else {
                return 0;
            }
        });
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void add(CardList cardList) {
        cards.addAll(cardList.getAll());
    }

    protected List<Card> getAll() {
        return cards;
    }

    public void remove(Card card) {
        cards.remove(card);
    }

    public void remove(int index) {
        cards.remove(index);
    }

    public void removeAll() {
        cards.clear();
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public int size() {
        return cards.size();
    }

    public String CardListToString() {
        String listCards = "";
        for (Card card : cards) {
            listCards += card.CardToString() + " ";
        }
        return listCards;
    }
}
