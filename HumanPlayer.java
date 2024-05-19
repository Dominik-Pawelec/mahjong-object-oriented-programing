import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(Hand h, River r){
        super(h,r);
    }
    public HumanPlayer(){
        super();
    }

    @Override
    public Tile chooseToDiscard(){

        
        Scanner input = new Scanner(System.in);
        System.out.print("Discard tile: ");
        
        String tile = input.next();
        Tile output = new Tile(tile);
        

        if(getHand().containsTile(output)){
            return output;
        }

        System.out.println("selected tile is not part of your hand. choose another.");
        return chooseToDiscard();
    }
}
