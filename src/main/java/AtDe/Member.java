package AtDe;

public interface Member extends Base.Member {
    void setRole(String role);
    void getMove(boolean firstMove);
    void attack(boolean firstMove);
    void defense();
    boolean getSuccess();
    int getDeckId();
}
