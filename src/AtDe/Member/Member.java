package AtDe.Member;

import AtDe.Components.Hand;
import AtDe.Core.Card;
import AtDe.Core.CardList;
import AtDe.GameLogic.Deck;

public interface Member extends Base.Member {
    void collect(Card card);
    void collect(CardList cardList);
    Hand getHand();
    void setDeck(Deck deck);
    boolean getSuccess();
}
