//FIGURE OUT ASCII DISPLAY FOR CARDS
//NO MAIN MENU; LAUNCH RIGHT INTO THE GAME
//POP-UP WINDOW WITH TIPS WHEN UP-UP-DOWN-DOWN-LEFT-RIGHT-LEFT-RIGHT

//===============================================================
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Card[] deck=shuffle(generate());
        Player player=new Player();
        House house=new House();
        int status=0;

        for(int i=0;i<=1;i++){
            draw(player,deck);
            draw(house,deck);
        }

        while(status==0){
            mainScreen(player,house,deck);
            status=winCondition(house,player);
        }

        switch(status){
            case 1 -> System.out.println("\nThe player wins!");
            case 2 -> System.out.println("\nThe house wins!");
            case 3 -> System.out.println("\nIt's a tie!");
        }

    }

//===============================================================

    public static Card[] shuffle(Card[] deck){
        Card[] newdeck = new Card[52];
        for(Card card : deck){
            boolean success=true;
            while(success){
                int index = (int)(Math.random()*52);
                if(newdeck[index]==null){
                    newdeck[index]=card;
                    success=false;
                }
            }
        }
        return newdeck;
    }

    public static Card[] generate(){
        Card[] deck = new Card[52];
        String[] names = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};

        int counter=0;
        for(int s=0;s<4;s++){
            for (String name : names){
                deck[counter] = new Card(name, s+1);
                counter++;
            }
        }
        return deck;
    }

    public static Card lastcard(Card[] deck,int id){
        Card result;
        if(deck[id-1]==null){
            return lastcard(deck,id-1);
        }else{
            result=deck[id-1];
            deck[id-1]=null;
        }
        return result;
    }

    public static int scan(House player){
        int id=0;
        for(Card card : player.hand){
            if(card==null){
                break;
            }else{id+=1;}
        }
        return id;
    }

    public static void draw(House player,Card[] deck){
        player.hand[scan(player)]=lastcard(deck, deck.length);
    }

    public static void displayHouse(House house){
        int id=0;
        for(Card card : house.hand){
            if(id!=house.hand.length-1&&house.hand[id+1]!=null){
                System.out.println(card);
                id+=1;
            }else{
                System.out.println("   -- unknown card --");
                break;
            }
        }
    }

    public static int winCondition(House house,House player){
        if(player.status==1||house.status==1){
            return 0;
        }

        int housesum=count(house);
        int playersum=count(player);

        System.out.println("\nPlayer's score: "+playersum+"\thas aces:"+hasAces(player)+
                "\nHouse's score: "+housesum+"\thas aces:"+hasAces(house));

        if(housesum>21&&playersum>21){
            return 3;
        }else if(housesum>21){
            return 1;
        }else if(playersum>21){
            return 2;
        }else{
            if(playersum==housesum){
                return 3;
            }else if(playersum>housesum){
                return 1;
            }else{
                return 2;
            }
        }
    }

    public static boolean hasAces(House player){
        boolean check=false;
        for(Card card : player.hand){
            if(card==null)break;
            else if(Objects.equals(card.name, "Ace")) {
                check = true;
            }
        }
        return check;
    }

    public static int countAces(House player){
        int count=0;
        for(Card card : player.hand){
            if(card==null){
                break;
            }else if(card.name.equals("Ace")) {
                count += 1;
            }
        }
        return count;
    }

    public static int count(House player){
        int playersum=0;

        for(Card card : player.hand){
            if(card!=null)playersum+=card.value;
            else break;
        }

        if(hasAces(player)&&playersum>21){
            for(int i=0;i<countAces(player);i++){
                playersum-=10;
                if(playersum<22){
                    break;
                }
            }
        }
        return playersum;
    }

    public static void mainScreen(Player player, House house,Card[] deck){
        System.out.println("\nplayer's hand:");
        for(Card card : player.hand){
            if(card!=null)System.out.println(card);
        }

        System.out.println("\nhouse's hand:");
        displayHouse(house);

        turnp(player,deck);
        turnh(house,deck);
    }

    public static void turnp(Player player, Card[] deck){
        if(player.status!=0) {
            if(ask(player)==2){
                player.status=0;
            }else{
                draw(player,deck);
            }
        }
    }

    public static void turnh(House house, Card[] deck){
        if(house.status!=0){
            if(count(house)<17){
                draw(house,deck);
            }else{
                house.status=0;
            }
        }
    }

    public static int ask(Player player){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n1 - Draw a card\n2 - Pass");
        int ans=scan.nextInt();
        if(ans!=2&&ans!=1){
            System.out.println("[!!!]Please input either 1 or 2");
            ans=ask(player);
        }
        return ans;
    }
}