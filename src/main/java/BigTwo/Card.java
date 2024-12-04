package BigTwo;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Card implements Base.Card, Comparable <Card> {
    private static final int[] POINT = {11, 12, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    private final int rank;
    private final int suit;

    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int[] POINT() {
        return POINT;
    }

    public int compareTo(@NotNull Card card) {
        if (this.getPoint() > card.getPoint()) {
            return 1;
        } else if (this.getPoint() < card.getPoint()) {
            return -1;
        } else return Integer.compare(this.suit, card.suit);
    }

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

    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }
}