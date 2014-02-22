public class Card {

    private String side1;
    private String side2;


    public Card(String side1, String side2) {
	this.side1 = side1;
	this.side2 = side2;
    }

    public void setSide1(String side1) {
	this.side1 = side1;
    }

    public void setSide2(String side2) {
	this.side2 = side2;
    }

    public String getSide1() { return side1; }
    public String getSide2() { return side2; }

}
