package BigTwo.Components;

import BigTwo.Core.CardList;

public class LastCardList extends CardList {
    public LastCardList() {
        super();
    }

    public void update(CardList cardList) {
    	removeAll();
        super.add(cardList);
    }
}
