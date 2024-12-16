package AtDe;

import AtDe.Components.CardSet;
import AtDe.Components.CardsUsed;
import AtDe.Components.NeedToDefend;
import AtDe.Core.Card;
import AtDe.Core.CardList;
import AtDe.GameLogic.Deck;

import java.util.ArrayList;

public class Casino {
    public static ArrayList<Deck> decks;

    Casino() {
        decks = new ArrayList<>();
    }

    public static int newGame() {
        decks.add(new Deck());
        int newDeckId = decks.size() - 1;
        return newDeckId;
    }
}
