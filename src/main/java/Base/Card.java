package Base;
import java.util.List;

public interface Card {
    static final char[] SUITS = {'♠', '♣', '♦', '♥'};
    static final String[] RANKS = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    static final int COUNT_RANK = 13;
    int[] POINT();

    public default String CardToString() {
        return "" + RANKS[getRank()] + SUITS[getSuit()];
    }

    public default int getPoint() {
        return POINT()[getRank()];
    }

    public int getRank();
    public int getSuit();
}