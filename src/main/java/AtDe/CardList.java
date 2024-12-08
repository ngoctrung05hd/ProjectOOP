package AtDe;

import java.util.ArrayList;

public class CardList extends Base.CardList {
    public CardList() {
        super();
    }

    public Card getFirstCard() {
        if (size() > 0)
            return (Card) cards.get(0);
        return null;
    }

    public ArrayList<Integer> getRanksOfList() {
        ArrayList<Integer> listRank = new ArrayList<Integer>();
        for (int i = 0; i < cards.size(); i++) {
            listRank.add(cards.get(i).getRank());
        }
        return listRank;
    }
}
