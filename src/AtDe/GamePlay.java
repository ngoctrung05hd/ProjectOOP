package AtDe;

import AtDe.Components.CardSet;
import AtDe.Components.Deck;
import AtDe.Member.BotEasy;
import AtDe.Member.Member;
import AtDe.Member.Player;
import AtDe.UserInterface.CardGameGUI;
import javafx.application.Application;

import java.util.ArrayList;

public class GamePlay {
    int deckId;
    Casino casino;

    GamePlay() {
        deckId = -1;
        casino = new Casino();
    }

    public void main() {
    	Deck deck = new Deck();
    	Player player = new Player();
    	BotEasy bot = new BotEasy();
    	deck.addMember(player);
    	deck.addMember(bot);
    	deck.newGame();
    	CardGameGUI.GUI(deck);
    }
}
