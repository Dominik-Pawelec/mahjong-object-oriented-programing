import java.util.Scanner;

public class HumanPlayer extends Player {
    Scanner input;
    public HumanPlayer(Hand h, River r){
        super(h,r);
        input = new Scanner(System.in);
    }
    public HumanPlayer(){
        super();
        input = new Scanner(System.in);
    }

    @Override
    public Tile chooseToDiscard(){
        System.out.print("Discard tile: ");
        
        String tile = input.next();
        Tile output = new Tile(tile);
        

        if(getHand().containsTile(output)){
            return output;
        }

        System.out.println("selected tile is not part of your hand. choose another.");
        return chooseToDiscard();
    }
    public boolean chooseToTsumo(){
        System.out.print("TSUMO? (Y/n)");
        
        String inp = input.next();

        if(inp == "n" || inp == "no"){return false;}
        return true;
    }
}
