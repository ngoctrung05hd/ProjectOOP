package AtDe;

import java.util.ArrayList;
import java.util.Collections;

public class CardsUsed extends CardList {
    private ArrayList <Boolean> existRank;

    public CardsUsed() {
        super();
        this.existRank = new ArrayList<>(Collections.nCopies(Card.COUNT_RANK, Boolean.FALSE));
    }

    public void add(Card card) {
        super.add(card);
        existRank.set(card.getRank(), true);
        sort();
    }

    public void add(CardList cardList) {
        super.add(cardList);
        sort();
    }

    public void reset() {
        removeAll();
        Collections.fill(existRank, Boolean.FALSE);
    }

    public boolean rankUsed(Card card) {
        return existRank.get(card.getRank());
    }
}
