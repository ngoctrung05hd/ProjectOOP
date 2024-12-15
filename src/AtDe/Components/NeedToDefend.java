package AtDe.Components;

import AtDe.Core.Card;
import AtDe.Core.CardList;

public class NeedToDefend extends CardList {
    public NeedToDefend() {
        super();
    }

    public void add(Card card) {
        super.add(card);
        sort();
    }

    public void add(CardList cardList) {
        super.add(cardList);
        sort();
    }
}
