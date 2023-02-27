public class Card {
    private int value;
    private Shape shape;

    /**
     * constructor of the card class
     */
    Card(int value, Shape shape) {
        this.value = value;
        this.shape = shape;
    }

    /**
     *
     * @return the value of the card
     */
    public int getValue() {
        return value;
    }

    /**
     *
     * @return the shape of the card
     */
    public Shape getShape() {
        return shape;
    }

    /**
     *
     * @param other - compered card
     * @return 0 if the 2 cards are identical,
     * 1 if the first value us greater, else -1
     */
    public int compare(Card other) {
        if(this.value < other.value) {
            return -1;
        }else if(this.value == other.value){
            return 0;
        }else{
            return 1;
        }
    }

    /**
     *
     * @return the picture string of a card
     */
    public String isPicture(){
        switch (this.value){
            case 1:
                return "Ace";
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
            default:
                return null;
        }
    }

    /**
     *
     * @return String description os class card
     */
    @Override
    public String toString() {
        String picture = isPicture();
        if(picture != null) {
            return picture + " of " + this.shape;
        }else {
            return this.value + " of " + this.shape;
        }
    }
}
