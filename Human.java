import java.util.List;
import java.util.Scanner;

public class Human extends Player {
    Scanner input;
    public Human(Hand h, River r){
        super(h,r);
        input = new Scanner(System.in);
    }
    public Human(){
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
        if(inp.equals("n") || inp.equals("no")){return false;}
        return true;
    }

    public String chooseCall(List<String> possible_calls) {// w possible_calls nie ma "skip"
        System.out.println("skip? or:" + possible_calls);
        
        String inp = input.next();
        if((inp.equals("chi") || inp.equals("pon") || inp.equals("skip") || inp.equals("ron"))&&(possible_calls.contains(inp))){return inp;}

        System.out.println("Wrong input. Choose another:");
        return chooseCall(possible_calls);
    }

    public TileGroup chooseGroup(List<TileGroup> groups){ 
        System.out.println("which to call(nr from left): "+groups);
        
        int inp = input.nextInt();
        if(groups.size() <= inp && groups.size() > 0){return groups.get(inp-1);}

        System.out.println("Wrong input. Choose another:");
        return chooseGroup(groups);
    }



}
