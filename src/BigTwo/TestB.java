package BigTwo;

import BigTwo.GameLogic.Deck;
import BigTwo.Member.*;
import BigTwo.UserInterface.CardGameGUI;
import BigTwo.Core.*;

public class TestB {

	static public void main(String args[]) {
    	Deck deck = new Deck();
    	Player player = new Player();
    	Player bot = new Player();
    	deck.addMember(player);
    	deck.addMember(bot);
    	deck.newGame();
    	CardGameGUI.GUI(deck);
	}
}
