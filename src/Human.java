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

        if(display_game != null){
            int x = display_game.display_hands.get(0).getTileToDiscard();
            return getHand().get(x);
        }
        
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
        return chooseToDiscard();
    }
    public boolean chooseToTsumo(){
        
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
        
        if(display_game != null){
            boolean[] temp = {true,possible_calls.contains("ron"),possible_calls.contains("pon"),possible_calls.contains("chi"),false,false};
            return display_game.calls.getCall(temp);
        }

        String inp = input.next();
        if((inp.equals("chi") || inp.equals("pon") || inp.equals("ron"))&&(possible_calls.contains(inp))){return inp;}
        if(inp.equals("skip")){return inp;}

        return chooseCall(possible_calls, discarded_tile);
    }

    public TileGroup chooseGroup(List<TileGroup> groups, Tile discarded_tile){ 
        return(groups.get(0));

    }


    public void pinToDisplay(DisplayGame dg){
        display_game = dg;
    }


}
