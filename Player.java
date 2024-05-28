import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player{
    Hand hand;
    River river;
    String wind;

    Tile recent_draw;
    boolean in_riichi = false;

    CallPackage call_package;

    public Player(Hand h, River r){
        hand = h;
        river = r;
        in_riichi = false;
    }
    public Player(){
        hand = new Hand();
        river = new River();
        in_riichi = false;
    }

    public Tile draw(){
        Wall wall = Wall.getInstance();
        recent_draw = hand.add(wall.drawTile());
        return recent_draw;
    }

    public void discard(Tile t){

        if(hand.containsTile(t)){
            hand.remove(t);
            river.add(t);
            return;
        }
        //System.out.println("Player doesn,'t contain tile:" + t + " . Operation ignored");
    }
    public void call(TileGroup tile_group){//just call, no discard
        getHand().openBlock(tile_group);
    }
    public CallPackage makePackage(CallPackage input_package){//inaczej kształt w zaleznosci od tego od kogo kradnie etc. :c
        CallPackage output_package = new CallPackage(input_package); 
        Tile temp_tile = output_package.getTile();

        if(getWind().equals(input_package.getWind()) || getRiichi()){
            output_package.preparePackage();
            return output_package;
        }

        //prepares list of all calls and makes player choose one of them (choosing is part of player's interface)
        List<String> possible_calls = new ArrayList<String>(0);
        String[] wind_list = new String[]{"east","south","west","north"};
        if(canChi(temp_tile) && (wind_list[(Arrays.asList(wind_list).indexOf(output_package.getWind())+1)%4] == wind)){ //whatthefuck
            possible_calls.add("chi");
        }
        if(canPon(temp_tile)){possible_calls.add("pon");}
        if(canRon(temp_tile)){possible_calls.add("ron");}

        if(possible_calls.size()==0){
            output_package.preparePackage();
            return output_package;
        }

        String chosen_call = chooseCall(possible_calls, input_package.getTile());
        
        TileGroup group;
        switch (chosen_call) {
            case "skip":
                output_package.preparePackage();
                return output_package;
            case "ron":
                output_package.preparePackage(true, chosen_call, new TileGroup());
                return output_package;
            case "chi":
                if(getHand().chiOptions(temp_tile).size() == 1){group = getHand().chiOptions(temp_tile).get(0);}
                else {group = new TileGroup(chooseGroup(getHand().chiOptions(temp_tile),input_package.getTile()));}
                output_package.preparePackage(true, chosen_call, group);
                return output_package;
            case "pon":
                if(getHand().ponOptions(temp_tile).size() == 1){group = new TileGroup(getHand().ponOptions(temp_tile).get(0));}
                else {group = new TileGroup(chooseGroup(getHand().ponOptions(temp_tile),input_package.getTile()));}
                output_package.preparePackage(true, chosen_call, group);
                return output_package;
            default:
                output_package.preparePackage();
                return output_package;
        }
    }

    //bez kan'a (kan na samym końcu)
    public boolean canChi(Tile t){
        return (getHand().chiOptions(t).size() != 0);
    }
    public boolean canPon(Tile t){
        return (getHand().ponOptions(t).size() != 0);
    }
    public boolean canRiichi(){
        Hand riichi_hand = new Hand(getHand());
        Hand temp_hand = new Hand(riichi_hand);
        if(riichi_hand.isOpen()){return false;}
        
        for(int i = 0; i < riichi_hand.size(); i++){
            temp_hand = new Hand(riichi_hand);
            temp_hand.remove(temp_hand.get(i));

            if(temp_hand.inTenpai()){return true;}
        }

        return false;
    }
    public boolean canRon(Tile t){
        Hand ron_hand = new Hand(getHand());
        ron_hand.add(t);
        return(ron_hand.isWinning());
    }
    public boolean canTsumo(){//nie jest konieczny argument do samej wygranej, ale pinfu czy fu juz tak
        return(getHand().isWinning());
    }

    public void setHand(Hand h){
        hand = h;
    }
    public boolean getRiichi(){
        return in_riichi;
    }
    public void setRiichi(boolean b){
        in_riichi = b;
    }
    public Hand getHand(){
        return hand;
    }
    public River getRiver(){
        return river;
    }
    public String getWind(){
        return wind;
    }
    public void setWind(String w){
        wind = w;
    }
    public void reset(){
        hand = new Hand();
        river = new River();
        in_riichi = false;
    }



    ///DIFFERENT AMONG SUBCLASSES
    public Tile chooseToDiscard(){//zamienić na: logika wyboru odrzutu
        //logic of discarding (for player: input, for bot:algorith) (this: last drawn)

        getHand().sort();
        /*return getHand().get(13);*/
        TileGroup all_tiles = new TileGroup("all");
        all_tiles.shuffle();
        Tile temp = new Tile("1m");
        for(int i = 0; i < all_tiles.size(); i++){
            temp = all_tiles.get(i);
            if(hand.containsTile(temp)){
                return temp;
            }
        }
        return temp;
    }

    public boolean chooseToTsumo(){
        return true;
    }
    public boolean chooseToRiichi(){
        return true;
    }
    
    
    public String chooseCall(List<String> possible_calls, Tile discarded_tile) {// w possible_calls nie ma "skip"
        if(possible_calls.contains("ron")){return "ron";}
        if(possible_calls.contains("pon")){return "pon";}
        if(possible_calls.contains("chi")){return "chi";}
        return "skip";//"skip","ron","chi","pon"
    }
    public TileGroup chooseGroup(List<TileGroup> groups, Tile discarded_tile){ 
        return groups.get(0);
    }

    /////////THE SAME:
    @Override
    public String toString(){
        return (getHand().toString() + getRiver().toString() + "in riichi: " + in_riichi);
    }

}
