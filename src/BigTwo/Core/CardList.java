package BigTwo.Core;

import java.util.ArrayList;

public class CardList extends Base.CardList<Card> {
    public CardList() {
        super();
    }

    public Card getFirstCard() {
        if (size() > 0)
            return (Card) cards.get(0);
        return null;
    }
    
    String typeOfList() {
    	String type = "";
    	
    	return type;
    }
}
