package AtDe;

public interface Member extends Base.Member {
    void setRole(String role);
    void collect(Card card);
    void getMove(boolean firstMove);
    boolean getSuccess();
    Hand getHand();
    int getDeckId();
    void clearHand();
}
