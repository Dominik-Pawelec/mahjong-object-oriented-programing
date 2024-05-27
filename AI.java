import java.util.List;

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
        String[] terminals = new String[]{"1m","9m","1p","9p","1s","9s"};
        for(int i = 0; i < terminals.length; i++){
            if(getHand().nrOfElem(new Tile(terminals[i])) == 1 
            && (getHand().nrOfElem(new Tile(new Tile(terminals[i]).getNr()+1,new Tile(terminals[i]).getFamily())) == 0 
            || getHand().nrOfElem(new Tile(new Tile(terminals[i]).getNr()-1,new Tile(terminals[i]).getFamily())) == 0)){
                return new Tile(terminals[i]);
            }
        } 
        

        //logika tego kiedy gra bezpieczniej??        

        return chooseShantenPositive();
    }
    public Tile chooseShantenPositive(){
        Hand hand = new Hand(getHand());

        Hand hand_copy = new Hand(hand);
        Calculator calc = new Calculator();
        int shanten = calc.shanten(hand_copy);
        for(int i = 1; i < hand.size(); i++){
            hand_copy = new Hand(hand);
            hand_copy.remove(hand_copy.get(i));
            if(calc.shanten(hand_copy) > shanten){
                System.out.println(hand.get(i));
                return hand.get(i);
            }
        }
        System.out.println("DUMB DECISION");
        return chooseMostWaits();
    }
    public Tile chooseMostWaits(){

        return getHand().get(getHand().size()-2);
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
            Hand hand = new Hand(getHand());
            hand.add(discarded_tile);
            hand.openBlock(new TileGroup(discarded_tile,discarded_tile,discarded_tile));
            Calculator calc = new Calculator();
            if(calc.shanten(getHand()) <= calc.shanten(hand)){
                return "pon";
            }
        }
        if(possible_calls.contains("chi")){
            Hand hand = new Hand(getHand());
            hand.add(discarded_tile);
            Calculator calc = new Calculator();
            if(calc.shanten(getHand()) <= calc.shanten(hand)){
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
            if(calc.shanten(getHand()) < calc.shanten(hand)){
                return groups.get(i);
            }
        }
        
        return groups.get(0);
    }

}
