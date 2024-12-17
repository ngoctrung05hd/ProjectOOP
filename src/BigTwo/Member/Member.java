package BigTwo.Member;

import BigTwo.Components.Hand;
import BigTwo.Core.Card;
import BigTwo.Core.CardList;
import BigTwo.GameLogic.Deck;

public interface Member extends Base.Member {
    void collect(Card card);
    void collect(CardList cardList);
    Hand getHand();
    void setDeck(Deck deck);
    boolean getSuccess();
}
