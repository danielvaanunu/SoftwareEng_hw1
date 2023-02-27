public class WarGame {
    private Player firstPlayer;
    private Player secondPlayer;
    private Deck priorityPlayerDeck; // the temporary deck of the first lexicographic player
    private Deck secondaryPlayerDeck; // the temporary deck of the second lexicographic player
    private String winner = ""; // represent the winner in the war game
    static int numberOfRounds = 1; // represent the number of rounds, related only to the class

    /**
     * constructor of the class
     * builds 2 new players
     * builds 2 empty decks
     * @param firstPlayerName
     * @param secondPlayerName
     */
    WarGame(String firstPlayerName,String secondPlayerName){
        this.firstPlayer = new Player(firstPlayerName);
        this.secondPlayer = new Player(secondPlayerName);
        this.priorityPlayerDeck = new Deck(false);
        this.secondaryPlayerDeck = new Deck(false);

    }

    /**
     * initialize the game, by shuffling the main deck
     * and handing out cards to each player
     */
    public void initializeGame(){
        numberOfRounds = 1;
        Deck mainDeck = new Deck(true);
        mainDeck.shuffle();

        Player priorityPlayer = getFirstLexicographicPlayer();
        Player secondaryPlayer = getSecondLexicographicPlayer();

        Deck tempDeck = priorityPlayer.getDrawDeck();

        int numberOfCards = mainDeck.getSizeOfDeck();
        Card card;

        for(int i = 0; i< numberOfCards; i++){
            card = mainDeck.removeTopCard();
            tempDeck.addCard(card);
            if(tempDeck == priorityPlayer.getDrawDeck()){
                tempDeck = secondaryPlayer.getDrawDeck();
            }else if(tempDeck == secondaryPlayer.getDrawDeck()){
                tempDeck = priorityPlayer.getDrawDeck();
            }
        }
    }

    /**
     *
     * @return the first lexicographic player
     * according to the ABC
     */
    public Player getFirstLexicographicPlayer(){
        if(this.firstPlayer.getName().compareTo(this.secondPlayer.getName()) < 0){
            return this.firstPlayer;
        }else{
            return this.secondPlayer;
        }
    }

    /**
     *
     * @return the second lexicographic player
     * according to the ABC
     */
    public Player getSecondLexicographicPlayer(){
        if(this.firstPlayer.getName().compareTo(this.secondPlayer.getName()) > 0){
            return this.firstPlayer;
        }else{
            return this.secondPlayer;
        }
    }

    /**
     * this function prints the name of
     * the player who drew the card
     * and his card description
     * @param name
     * @param card
     */
    public void printDrewCard(String name, Card card){
        System.out.println(name + " drew " + card);
    }

    /**
     *
     * @param player
     * @return true if drew deck and win deck of the
     * same player is empty, false otherwise
     */
    public boolean isBothDecksEmpty(Player player){
        return player.getWinDeck().isEmpty() && player.getDrawDeck().isEmpty();
    }

    /**
     * this function play the war part,
     * drew 1 card from each player twice,
     * according to lexicographic order,
     * and compare the third card drew-
     * in a different function
     */
    public void war(){
        System.out.println("Starting a war...");

        Player priorityPlayer = getFirstLexicographicPlayer();
        Player secondaryPlayer = getSecondLexicographicPlayer();
        Card topCard;

        for (int i = 0; i < 2; i++){
            if(checkWinner(priorityPlayer,secondaryPlayer)){
                break;
            }
            System.out.println(priorityPlayer.getName() + " drew a war card");
            topCard = priorityPlayer.getDrawDeck().removeTopCard();
            this.priorityPlayerDeck.addCard(topCard);

            System.out.println(secondaryPlayer.getName() + " drew a war card");
            topCard = secondaryPlayer.getDrawDeck().removeTopCard();
            this.secondaryPlayerDeck.addCard(topCard);
        }
        if(!checkWinner(priorityPlayer, secondaryPlayer)){
            compareCards(priorityPlayer,secondaryPlayer);
        }
    }

    /**
     * this function compares the cards drew in a round,
     * sends to war function in case of a tie,
     * otherwise fills the win deck of the winner
     * @param priorityPlayer
     * @param secondaryPlayer
     */
    public void compareCards(Player priorityPlayer, Player secondaryPlayer){

        Card priorityPlayerCard = priorityPlayer.getDrawDeck().removeTopCard();
        this.priorityPlayerDeck.addCard(priorityPlayerCard);
        printDrewCard(priorityPlayer.getName(),priorityPlayerCard);

        Card secondaryPlayerCard = secondaryPlayer.getDrawDeck().removeTopCard();
        this.secondaryPlayerDeck.addCard(secondaryPlayerCard);
        printDrewCard(secondaryPlayer.getName(),secondaryPlayerCard);

        if(priorityPlayerCard.getValue() > secondaryPlayerCard.getValue()){

            if (this.priorityPlayerDeck.getSizeOfDeck() > 1 ){
                System.out.println(priorityPlayer.getName() + " won the war");
            }else {
                System.out.println(priorityPlayer.getName() + " won");
            }
            priorityPlayer.getWinDeck().addWinCards(this.priorityPlayerDeck, this.secondaryPlayerDeck);

        }else if(priorityPlayerCard.getValue() < secondaryPlayerCard.getValue()){

            if (this.priorityPlayerDeck.getSizeOfDeck() > 1 ){
                System.out.println(secondaryPlayer.getName() + " won the war");
            }else {
                System.out.println(secondaryPlayer.getName() + " won");
            }
            secondaryPlayer.getWinDeck().addWinCards(this.priorityPlayerDeck, this.secondaryPlayerDeck);

        }else war();
    }

    /**
     * if the drew deck of a player is empty-
     * shuffles the win deck, and adds all
     * the cards to the player drew deck
     * @param player
     */
    public void checkAndFillDeck(Player player){
        if(player.getDrawDeck().isEmpty()) {
            player.getWinDeck().shuffle();
            player.getDrawDeck().addAllCardsToDrewDeck(player.getWinDeck());
        }
    }

    /**
     * checks if both of the win deck and the drew deck
     * of a player is empty
     * @param priorityPlayer
     * @param secondaryPlayer
     * @return the other player name
     */
    public boolean checkWinner(Player priorityPlayer, Player secondaryPlayer){
        if(priorityPlayer.OutOfCards()){
            this.winner = secondaryPlayer.getName();
            return true;
        } else if(secondaryPlayer.OutOfCards()) {
            this.winner = priorityPlayer.getName();
            return true;
        } else {
            checkAndFillDeck(priorityPlayer);
            checkAndFillDeck(secondaryPlayer);
            return false;
        }
    }

    /**
     * play the war game according to the rules
     * @return the winner name
     */
    public String start(){
        System.out.println("Initializing the game...");
        initializeGame();

        Player priorityPlayer = getFirstLexicographicPlayer();
        Player secondaryPlayer = getSecondLexicographicPlayer();

        while(!(isBothDecksEmpty(priorityPlayer)) || !(isBothDecksEmpty(secondaryPlayer))){

            if(checkWinner(priorityPlayer,secondaryPlayer)){
                break;
            }else {
                System.out.println("------------------------- Round number " + numberOfRounds + " ------------------------- ");
                compareCards(priorityPlayer,secondaryPlayer);
                numberOfRounds++;
            }
        }
        return this.winner;
    }
}
