package AtDe;

import java.util.ArrayList;

public class GamePlay {
    int deckId;
    Casino casino;
    ArrayList<Member> members;

    GamePlay() {
        deckId = -1;
        casino = new Casino();
        members = new ArrayList<>();
    }

    void reset() {
        Casino.reset(getDeckId());
    }

    boolean endGame() {
        int count = 0;
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getHand().size() == 0)
                ++count;
        }
        return (count == members.size() - 1);
    }

    public void main() {
        Deck deck = new Deck();


        BotEasy bot = new BotEasy(0);
        Player player = new Player(0);

        bot.setDeck(deck);
        player.setDeck(deck);

        members.add(bot);
        members.add(player);

        CardSet cardSet = deck.getCardSet();
        System.out.println(cardSet.CardListToString());

        int numPlayer = 2;

        for (int turn = 0; turn < 8; ++turn) {
            for (int i = 0; i < numPlayer; ++i) {
                members.get(i).collect(cardSet.getEnd());
            }
        }

        for (int i = 0; i < numPlayer; ++i)
            members.get(i).setRole("attack");
        members.get(1).setRole("defend");

        members.get(1).getHand().sort();

        members.get(0).getMove(true);

        CardGameGUI.GUI(player);
    }

    private void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public int getDeckId() {
        return deckId;
    }
}
