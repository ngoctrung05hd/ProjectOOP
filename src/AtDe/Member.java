package AtDe;

public interface Member extends Base.Member {
    void setRole(String role);
    void collect(Card card);
    void getMove(boolean firstMove);
    void attack(boolean firstMove);
    void defend();
    boolean getSuccess();
    Hand getHand();
    int getDeckId();
    void clearHand();
}
