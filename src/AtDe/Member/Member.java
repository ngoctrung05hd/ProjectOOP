package AtDe.Member;

import AtDe.Components.Deck;
import AtDe.Components.Hand;
import AtDe.Core.Card;

public interface Member extends Base.Member {
    void setRole(String role);
    void collect(Card card);
    void getMove();
    boolean getSuccess();
    Hand getHand();
    void setDeck(Deck deck);
    void clearHand();
    String getRole();
}
