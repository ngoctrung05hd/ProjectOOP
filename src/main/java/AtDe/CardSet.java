package AtDe;

public class CardSet extends Base.CardList {
    public CardSet() {
        super();
    }

    public Card getBegin() {
        Card begin = null;
        if (this.size() > 0)
            begin = (Card) cards.getFirst();
        return begin;
    }

    public void setBegin(Card begin) {
        cards.set(0, begin);
    }

    public Card getBack() {
        Card back = null;
        if (this.size() > 0) {
            back = (Card) cards.getLast();
            cards.removeLast();
        }
        return back;
    }

}
