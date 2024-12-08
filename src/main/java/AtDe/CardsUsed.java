package AtDe;

import java.util.ArrayList;
import java.util.Collections;

public class CardsUsed {
    public CardList cardsUsed;
    private ArrayList <Boolean> existRank;

    public CardsUsed() {
        this.cardsUsed = new CardList();
        this.existRank = new ArrayList<>(Collections.nCopies(Card.COUNT_RANK, Boolean.FALSE));
    }

    public void add(Card card) {
        cardsUsed.add(card);
        existRank.set(card.getRank(), true);
        cardsUsed.sort();
    }

    public void add(CardList cardList) {
        cardsUsed.add(cardList);
        cardList.sort();
    }

    public void reset() {
        cardsUsed.removeAll();
        Collections.fill(existRank, Boolean.FALSE);
        cardsUsed.sort();
    }

    public boolean rankUsed(Card card) {
        return existRank.get(card.getRank());
    }
}
