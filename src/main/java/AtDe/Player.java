package AtDe;

public class Player {
    Hand hand;
    boolean done;
    static final int maxCount = 8;

    Player() {
        hand = new Hand();
        done = false;
    }

    public void attack(boolean first) {
        if (first) {

        }
    }

    public boolean defense() {
        return false;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void getFullCount() {
    }
}
