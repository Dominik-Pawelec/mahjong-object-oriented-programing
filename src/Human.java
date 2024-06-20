import java.util.List;
import java.util.Scanner;

public class Human extends Player {
    Scanner input;

    DisplayGame display_game;

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

        if(in_riichi){
            return recent_draw;
        }
        System.out.print("Discard tile: ");

        if(display_game != null){
            int x = display_game.display_hands.get(0).getTileToDiscard();
            return getHand().get(x);
        }

        System.out.print("Discard tile: ");
        
        String tile = input.next();

        try{
        if(Integer.valueOf(tile) <= getHand().size() && Integer.valueOf(tile) > 0 ){
            return getHand().get(Integer.valueOf(tile)-1);
        }
        }catch(NumberFormatException e){}

        Tile output = new Tile(tile);

        if(getHand().containsTile(output)){
            return output;
        }

        System.out.println("selected tile is not part of your hand. choose another.");
        return chooseToDiscard();
    }
    public boolean chooseToTsumo(){
        System.out.print("TSUMO? (Y/n)");
        
        if(display_game != null){
            boolean[] temp = {true,false,false,false,true,false};
            if ("tsumo" == display_game.calls.getCall(temp)) return true;
            return false;
        }

        String inp = input.next();
        if(inp.equals("n") || inp.equals("no")){return false;}
        return true;
    }
    public boolean chooseToRiichi(){
        System.out.println("RIICHI? (Y/n)");
        
        if(display_game != null){
            boolean[] temp = {false,false,false,false,false,true};
            if ("riichi" == display_game.calls.getCall(temp)) return true;
            return false;
        }
    
        String inp = input.next();
        if(inp.equals("n") || inp.equals("no")){return false;}
        return true;
    }

    public String chooseCall(List<String> possible_calls, Tile discarded_tile) {// w possible_calls nie ma "skip"
        System.out.println("call: " + discarded_tile + " | skip or:" + possible_calls);
        
        if(display_game != null){
            System.out.println(possible_calls.contains("ron"));
            System.out.println(possible_calls.contains("pon"));
            System.out.println(possible_calls.contains("chi"));

            boolean[] temp = {true,possible_calls.contains("ron"),possible_calls.contains("pon"),possible_calls.contains("chi"),false,false};
            return display_game.calls.getCall(temp);
        }

        String inp = input.next();
        if((inp.equals("chi") || inp.equals("pon") || inp.equals("ron"))&&(possible_calls.contains(inp))){return inp;}
        if(inp.equals("skip")){return inp;}

        System.out.println("Wrong input. Choose another:");
        return chooseCall(possible_calls, discarded_tile);
    }

    public TileGroup chooseGroup(List<TileGroup> groups, Tile discarded_tile){ 
        return(groups.get(0));

        /*System.out.println("which to call(nr from left): "+groups);
        
        int inp = input.nextInt();
        if(groups.size() >= inp){return groups.get(inp-1);}

        System.out.println("Wrong input. Choose another:");
        return chooseGroup(groups,discarded_tile);*/
    }


    public void pinToDisplay(DisplayGame dg){
        display_game = dg;
    }


}
