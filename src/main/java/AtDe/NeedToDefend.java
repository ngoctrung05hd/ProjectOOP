package AtDe;

import Base.Card;
import Base.CardList;

public class NeedToDefend extends Base.CardList {
    public NeedToDefend() {
        super();
    }

    @Override
    public void add(Card card) {
        super.add(card);
        sort();
    }

    @Override
    public void add(CardList cardList) {
        super.add(cardList);
        sort();
    }
}
