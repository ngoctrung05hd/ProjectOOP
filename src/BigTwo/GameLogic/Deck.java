package BigTwo.GameLogic;

import BigTwo.Core.CardList;

import java.util.ArrayList;

import BigTwo.Components.CardSet;
import BigTwo.Components.LastCardList;
import BigTwo.Member.Member;

public class Deck {
    private CardSet cardSet;
	private LastCardList lastCardList;
    private ArrayList<Member> members;
    private int currentMemberId;
    private int	startMemberId;
    private int endTurnCount = 0;
    //private CardGameController controller;

    public Deck() {
        cardSet = new CardSet();
        members = new ArrayList<>();
        endTurnCount = 0;
    }

    public void reset() {
        cardSet = new CardSet();
        lastCardList.removeAll();

		for (Member mem : members) {
			mem.clearHand();
		}
    }
    
    public void drawCard() {
    	for (int i = 0; i < 13; ++i) {
    		for (Member mem : members) {
    			mem.collect(cardSet.getEnd());
    		}
    	}
    }
    
	public void move(CardList cardList) {
		lastCardList.update(cardList);
	}
    
	public LastCardList getLastCardList() {
		return lastCardList;
	}
	
}
