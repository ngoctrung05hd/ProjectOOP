package AtDe;

import java.util.ArrayList;

public class Casino {
    public static ArrayList<Deck> decks;

    Casino() {
        decks = new ArrayList<>();
    }

    static public void attack(CardList cardList, int deckId) {
        decks.get(deckId).attack(cardList);
    }

    static public void defend(Card card, Card cardDefend, int deckId) {
        decks.get(deckId).defend(card, cardDefend);
    }

    static public boolean checkAttack(Card card, int deckId) {
        return decks.get(deckId).checkAttack(card);
    }

    static public void newGame() {
        decks.add(new Deck());
        int newDeckId = decks.size() - 1;
    }

    public static NeedToDefend getNeedToDefend(int deckId) {
        return decks.get(deckId).getNeedToDefend();
    }
}
