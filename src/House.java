public class House {
    public Card[] hand;
    public int status; // 1- draw; 0- pass;

    public House(){
        this.hand=new Card[12];
        this.status=1;
    }
}
