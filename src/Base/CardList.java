package Base;

import java.util.ArrayList;
import java.util.List;

public class CardList <T extends Card> {
    protected List<T> cards;

    public CardList() {
        cards = new ArrayList<>();
    }

    public void sort() {
        cards.sort((c1, c2) -> {
            if (c1 instanceof AtDe.Core.Card && c2 instanceof AtDe.Core.Card) {
                return ((AtDe.Core.Card) c1).compareTo((AtDe.Core.Card) c2);
            }
            if (c1 instanceof BigTwo.Core.Card && c2 instanceof BigTwo.Core.Card) {
                return ((BigTwo.Core.Card) c1).compareTo((BigTwo.Core.Card) c2);
            }
            else {
                return 0;
            }
        });
    }

    public void add(T card) {
        cards.add(card);
    }

    public void add(CardList cardList) {
        cards.addAll(cardList.getAll());
    }

    protected List<T> getAll() {
        return cards;
    }

    public void remove(T card) {
        cards.remove(card);
    }

    public void remove(int index) {
        cards.remove(index);
    }

    public void removeAll() {
        cards.clear();
    }

    public T getCard(int index) {
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
