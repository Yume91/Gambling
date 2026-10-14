public class Card {
    public String name;
    public int suit; //1-Di 2-He 3-Sp 4-Cl
    public int value;
    public String[] suits = {"Diamonds","Hearts","Spades","Clubs"};

    public Card(String name,int suit){
        this.name=name;
        this.suit=suit;
        if(name.matches("\\d")){
            this.value=name.charAt(0)-48;
        }else if(name.matches("Ace")){
            this.value=11;
        }else{
            this.value=10;
        }
    }

    @Override
    public String toString(){
        return name+" of "+suits[suit-1]+"\t\tvalue:"+value;
    }
}
