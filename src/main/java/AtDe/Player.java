package AtDe;

public class Player {
    private Hand hand;
    private boolean done;
    static final int maxCount = 8;
    private String role;

    Player() {
        hand = new Hand();
        done = false;
        role = "";
    }

    public void attack(boolean first) {
        if (first) {

        }
        else {

        }
    }

    public boolean defend() {
        return false;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void getFullCount() {
    }
}
