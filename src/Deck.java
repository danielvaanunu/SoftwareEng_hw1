import java.util.*;

public class Deck {
    public ArrayList<Card> deck;

    /**
     * constructor for the deck class
     * @param boolVal - true for initialize deck
     *                false for empty deck
     */
    Deck(Boolean boolVal) {
        this.deck = new ArrayList<>();
        if (boolVal) {
            createOneType(Shape.Spades);
            createOneType(Shape.Diamonds);
            createOneType(Shape.Clubs);
            createOneType(Shape.Hearts);
        }
    }

    /**
     *
     * @param shape - create 13 cards for the shape sent
     */
    public void createOneType(Shape shape) {
        Card card;
        for (int i = 1; i <= 13; i++) {
            card = new Card(i, shape);
            this.deck.add(card);
        }
    }

    /**
     *
     * @param card add card to the top of the deck
     */
    public void addCard(Card card){
        this.deck.add(this.deck.size(), card);
    }

    /**
     *
     * @return top card
     */
    public Card removeTopCard(){
        int lastIndexCardDeck = this.deck.size() - 1;
        Card topCard = this.deck.get(lastIndexCardDeck);
        this.deck.remove(lastIndexCardDeck);
        return topCard;
    }

    /**
     *
     * @return true if the deck is empty,
     * false otherwise
     */
    public boolean isEmpty(){
        if (this.deck.size() != 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * this function shuffles a deck according
     * to the method given
     */
    public void shuffle(){
        int numberOfCards = this.deck.size();
        Card tempCard;
        int firstIndex ,secondIndex;
        for (int i = 0; i < 50; i++){
            firstIndex = Main.rnd.nextInt(numberOfCards);
            secondIndex = Main.rnd.nextInt(numberOfCards);

            tempCard = this.deck.get(firstIndex);
            this.deck.set(firstIndex,this.deck.get(secondIndex));
            this.deck.set(secondIndex,tempCard);
        }
    }

    /**
     *
     * @return the total cards in the deck
     */
    public int getSizeOfDeck(){
        return this.deck.size();
    }

    /**
     *
     * @param winDeck - add all cards from winDeck deck
     *              to drew deck
     */
    public void addAllCardsToDrewDeck(Deck winDeck){
        int deckSize = winDeck.getSizeOfDeck();
        Card card;
        for(int i = 0; i < deckSize; i++){
            card = winDeck.removeTopCard();
            this.deck.add(0, card);
        }
    }

    /**
     * adding winning cards to deck
     * according to the rules
     * @param tempWinDeck
     * @param tempLoserDeck
     */
    public void addWinCards(Deck tempWinDeck, Deck tempLoserDeck){
        int deckSize = tempWinDeck.getSizeOfDeck() + tempLoserDeck.getSizeOfDeck();
        Card card;
        for (int i = 0; i < deckSize ; i += 2){
            card = tempLoserDeck.removeTopCard();
            this.deck.add(this.deck.size(), card);
            card = tempWinDeck.removeTopCard();
            this.deck.add(this.deck.size(), card);
        }
    }
}
