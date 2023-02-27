public class Player {
    private String name;
    private Deck drawDeck;
    private Deck winDeck;

    /**
     * constructor of player class
     * creates 2 new empty decks
     * @param name
     */
    Player(String name) {
        this.name = name;
        this.drawDeck = new Deck(false);
        this.winDeck = new Deck(false);
    }

    /**
     *
     * @return the player name
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return the player's drew deck of cards
     */
    public Deck getDrawDeck() {
        return this.drawDeck;
    }
    /**
     *
     * @return the player's win deck of cards
     */
    public Deck getWinDeck() {
        return this.winDeck;
    }
    /**
     *
     * @return the player's top card from drew deck
     */
    public void drawCard(){
        this.drawDeck.removeTopCard();
    }

    /**
     *
     * @return true if both decks of the player is empty'
     * false otherwise
     */
    public boolean OutOfCards(){
        return this.drawDeck.isEmpty() && this.winDeck.isEmpty();
    }

    /**
     *
     * @return string description of a player
     */
    @Override
    public String toString(){
        return "the name is:" + this.name;
    }
}
