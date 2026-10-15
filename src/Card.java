public class Card {
    public String name;
    public int suit; //1-Di 2-He 3-Sp 4-Cl
    public int value;
    public String[] suits = {"Diamonds","Hearts","Spades","Clubs"};
    public String[] symbols = {"♢","♡","♠","♣"};

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

    //STILL NO FACE CARDS DISPLAY
    public String display(int row){
        String result="";
        switch(row){
            case 1 -> result="╭-----------╮";
            case 2 -> {
                if(this.value==11){result="|A          |";}
                else if(this.value<4){result="|"+this.name+"    "+this.symbols[suit-1]+"     |";}
                else if(this.value<10){result="|"+this.name+" "+this.symbols[suit-1]+"     "+this.symbols[suit-1]+"  |";}
                else if(this.value==10){result="|10"+this.symbols[suit-1]+"     "+this.symbols[suit-1]+"  |";}
                else{result="| +-------+ |";}
            }
            case 3 -> {
                if(this.value==11||this.value<8){result="|           |";}
                else if(this.value==8){result="|     "+this.symbols[suit-1]+"     |";}
                else if(this.value==9){result="|  "+this.symbols[suit-1]+"     "+this.symbols[suit-1]+"  |";}
                else if(this.value==10){result="|  "+this.symbols[suit-1]+"  "+this.symbols[suit-1]+"  "+this.symbols[suit-1]+"  |";}
                else{result="| |=+=+=+=| |";}
            }
            case 4 -> {
                if(this.value==11||this.value==3||this.value==5||this.value==9){result="|     "+this.symbols[suit-1]+"     |";}
                else if(this.value==2||this.value==4||this.value==10){result="|           |";}
                else if(this.value<9&&this.value>5){result="|  "+this.symbols[suit-1]+"     "+this.symbols[suit-1]+"  |";}
                else{result="| |+=+=+=+| |";}
            }
            case 5 -> {
                if(this.value<7||this.value==11){result="|           |";}
                else if(this.value<9){result="|     "+this.symbols[suit-1]+"     |";}
                else if(this.value==9){result="|  "+this.symbols[suit-1]+"     "+this.symbols[suit-1]+"  |";}
                else if(this.value==10){result="|  "+this.symbols[suit-1]+"  "+this.symbols[suit-1]+"  "+this.symbols[suit-1]+"  |";}
                else{result="| |=+=+=+=| |";}
            }
            case 6 -> {
                if(this.value==11){result="|           |";}
                else if(this.value==2||this.value==3){result="|     "+this.symbols[suit-1]+"     |";}
                else if(this.value<11){result="|  "+this.symbols[suit-1]+"     "+this.symbols[suit-1]+"  |";}
                else{result="| +-------+ |";}
            }
            case 7 -> result="╰-----------╯";
        }
        return result;
    }

    @Override
    public String toString(){
        return name+" of "+suits[suit-1]+"\t\tvalue:"+value;
    }
}
