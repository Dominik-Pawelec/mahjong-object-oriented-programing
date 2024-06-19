import java.util.List;

import javafx.util.Pair;

import java.util.ArrayList;

public class AI extends Player{
    Game game;
    public AI(Hand h, River r, Game g){
        super(h,r);
        game = g;
    }
    public AI(Game g){
        super();
        game = g;
    }

    public TileGroup waitingTiles(){
        TileGroup output = new TileGroup();

        return output;
    }

    
    @Override
    public Tile chooseToDiscard(){
        if(in_riichi){
            return recent_draw;
        }

        String[] bad_tiles = new String[]{"S","W","N","E","R","G","B"};
        for(int i = 0; i < bad_tiles.length; i++){
            if(getHand().nrOfElem(new Tile(bad_tiles[i])) == 1){
                return new Tile(bad_tiles[i]);
            }
        } 
        String[] terminals = new String[]{"1m","9m","1p","9p","1s","9s","2m","2p","2s","8m","8p","8s","3m","3p","3s","7m","7p","7s","4m","4p","4s","6m","6p","6s","5m","5p","5s"};
        for(int i = 0; i < terminals.length; i++){
            if(getHand().nrOfElem(new Tile(terminals[i])) == 1 
            && getHand().nrOfElem(new Tile(new Tile(terminals[i]).getNr()+1,new Tile(terminals[i]).getFamily())) == 0 
            && getHand().nrOfElem(new Tile(new Tile(terminals[i]).getNr()-1,new Tile(terminals[i]).getFamily())) == 0
            && getHand().nrOfElem(new Tile(new Tile(terminals[i]).getNr()+2,new Tile(terminals[i]).getFamily())) == 0 
            && getHand().nrOfElem(new Tile(new Tile(terminals[i]).getNr()-2,new Tile(terminals[i]).getFamily())) == 0 ){
                System.out.println(getWind().toString() +" chose disconnected: " + terminals[i]);
                try{
                    Thread.sleep(0);
                }catch(Exception e){}
                return new Tile(terminals[i]);
            }
        }  

        return chooseShantenPositive();
    }
    public Tile chooseShantenPositive(){
        int wait = 0;
        Hand hand = new Hand(getHand());

        Hand hand_copy = new Hand(hand);
        Calculator calc = new Calculator();
        int shanten = calc.shanten(hand_copy);
        System.out.println(shanten);

        TileGroup potential_tiles = new TileGroup();

        for(int i = 1; i < hand.size(); i++){
            hand_copy = new Hand(hand);
            hand_copy.remove(hand_copy.get(i));
            System.out.println(calc.shanten(hand_copy));
            if(calc.shanten(hand_copy) > shanten){
                System.out.println(hand.get(i));
                System.out.println(getWind());
                try{
                    Thread.sleep(wait);
                }catch(Exception e){}

                potential_tiles.add(hand.get(i));
            }
        }

        if(potential_tiles.size()==0){
            try{
                System.out.println(getWind().toString() + " Every equal in terms of shanten");
                Thread.sleep(wait);
            }catch(Exception e){}
            return chooseMostWaits();
        }
        String[] discard_order = new String[]{"E","S","W","N","R","G","B","1m","9m","1p","9p","1s","9s","2m","2p","2s","8m","8p","8s","3m","3p","3s","7m","7p","7s","4m","4p","4s","6m","6p","6s","5m","5p","5s" };
    
        for(int i = 0; i < discard_order.length;i++){
            if(potential_tiles.nrOfElem(new Tile(discard_order[i])) > 0){
                return new Tile(discard_order[i]);
            }
        }
        return getHand().get(0);//zeby zwracalo int
    }
    public Tile chooseMostWaits(){
        List<Integer> lista = new ArrayList<>(0);

        int wait_nr = 0;
        Tile output = getHand().get(0);

        TileGroup temp_improv;
        Calculator calc = new Calculator();
        List<Pair<Tile, Integer>> tiles = calc.remainingTiles(getHand(), game);
        Hand h = new Hand(getHand());

        for(int i = 0; i < getHand().size(); i++){
            h = new Hand(getHand());
            h.remove(h.get(i));

            temp_improv = calc.improvingTiles(h);
            wait_nr = 0;
            for(int ii = 0; ii < temp_improv.size(); ii++){
                Tile t =  temp_improv.get(ii);

                for(int iii = 0; iii < tiles.size(); iii++){
                    if(tiles.get(iii).getKey().compareTo(t) == 0){
                        wait_nr += tiles.get(iii).getValue();
                        break;
                    }
                }
            }
            lista.add(wait_nr);
        }

        int min = 9999999;
        for(int i = 0; i < lista.size(); i++){
            System.out.println(getHand().get(i) + " -> " + lista.get(i));
            if (min > lista.get(i)){
                output = getHand().get(i);
                min = lista.get(i);
            }
        }
        System.out.println(getWind().toString() + " chose:3 : " + output);
        
        return output;
    }
    public boolean chooseToTsumo(){
        return true;
    }
    public boolean chooseToRiichi(){
        return true;
    }

    public String chooseCall(List<String> possible_calls, Tile discarded_tile) {// w possible_calls nie ma "skip"
        if(possible_calls.contains("ron")){return "ron";}

        //TODO: by szaclowal ktory z tych lepszy
        if(possible_calls.contains("pon")){ 
            if(discarded_tile.getFamily().equals("dragon") || (Character.toLowerCase(discarded_tile.toString().charAt(0)) == Character.toLowerCase(getWind().charAt(0))) || discarded_tile.equals(new Tile("E"))){
                return "pon";
            }
            Hand hand = new Hand(getHand());
            hand.add(discarded_tile);
            hand.openBlock(new TileGroup(discarded_tile,discarded_tile,discarded_tile));
            Calculator calc = new Calculator();
            if(calc.shanten(getHand()) > calc.shanten(hand)){
                return "pon";
            }
        }
        if(possible_calls.contains("chi")){
            Hand hand = new Hand(getHand());
            hand.add(discarded_tile);
            Calculator calc = new Calculator();
            if(calc.shanten(getHand()) > calc.shanten(hand)){
                return "chi";
            }
        }
        return "skip";
    }

    public TileGroup chooseGroup(List<TileGroup> groups, Tile discarded_tile){ 
        Hand hand = new Hand(getHand());
        for(int i = 0; i < groups.size(); i++){
            hand = new Hand(getHand());
            hand.add(discarded_tile);
            hand.openBlock(groups.get(i));
            Calculator calc = new Calculator();
            if(calc.shanten(getHand()) < calc.shanten(hand)){//TODO
                return groups.get(i);
            }
        }
        
        return groups.get(0);
    }

}
