package AtDe;

public class Test {
    public static void main(String[] args) {
        CardList cardList = new CardList();
        cardList.add(new Card(9, 2));
        cardList.add(new Card(9, 3));
        Card.setSpecialSuit(3);

        new Casino();
        Casino.newGame();
        Casino.attack(cardList, 0);
        System.out.println(Casino.decks.get(0).cardsUsed.cardsUsed.CardListToString());
    }
}
