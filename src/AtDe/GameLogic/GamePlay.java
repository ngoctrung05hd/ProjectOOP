package AtDe.GameLogic;

import java.util.List;

import AtDe.Casino;
import AtDe.Member.BotEasy;
import AtDe.Member.Player;
import AtDe.UserInterface.CardGameGUI;

public class GamePlay {
    int deckId;
    Casino casino;
    public Deck deck;
    public GamePlay() {
        deckId = -1;
        casino = new Casino();
        deck = new Deck();
    }

    public void main(List<String> playerName, int botNum ) {
        for (int i = 0; i < playerName.size(); i++ ) {
	        Player player = new Player();
	        player.setName(playerName.get(i));
	        deck.addMember(player);
        }
        
		for (int i = 0; i < botNum; i++) {
			BotEasy bot = new BotEasy();
			bot.setName("Bot " + (i + 1));
			deck.addMember(bot);
		}

		System.out.println(botNum);
        
        
        deck.newGame();

        // Safely call GUI
        // CardGameGUI.GUI(deck);
    }
}
