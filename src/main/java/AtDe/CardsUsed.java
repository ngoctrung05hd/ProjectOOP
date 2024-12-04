package AtDe;

import java.util.ArrayList;
import java.util.Collections;

public class CardsUsed extends Base.CardList {
    public ArrayList <Boolean> existRank;

    public CardsUsed() {
        super();
        this.existRank = new ArrayList<>(Collections.nCopies(Card.COUNT_RANK, Boolean.FALSE));
    }

    public void add(Card card) {
        super.add(card);
        existRank.set(card.getRank(), true);
        sort();
    }

    public void reset() {
        Collections.fill(existRank, Boolean.FALSE);
    }
}
