package AtDe;

import java.util.Objects;

public class Card extends Base.Card implements Comparable<Card> {
    private static final int[] POINT = {12, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

    private int specialSuit;

    Card(int rank, int suit) {
        super(rank, suit);
        specialSuit = -1;
    }

    public int[] POINT() {
        return POINT;
    }

    public void setSpecialSuit(int suit) {
        specialSuit = suit;
    }

    public boolean checkDefend(Card cardDefend) {
        if (getSuit() == specialSuit)
            return compareTo(cardDefend) > 0;
        else {
            return getSuit() == cardDefend.getSuit() && getRank() > cardDefend.getRank();
        }
    }

    public int compareTo(Card card) {
        if (this.suit == specialSuit && card.suit != specialSuit) {
            return 1;
        } else if (this.suit != specialSuit && card.suit == specialSuit) {
            return -1;
        } else return Integer.compare(this.getPoint(), card.getPoint());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return this.getRank() == card.getRank() && this.getSuit() == card.getSuit();
    }




    @Override
    public int hashCode() {
        return Objects.hash(getRank(), getSuit());
    }
}