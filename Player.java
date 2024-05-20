import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player{
    Hand hand;
    River river;
    String wind;
    //money etc.
    //autosort
    boolean in_riichi = false;

    CallPackage call_package;

    public Player(Hand h, River r){
        hand = h;
        river = r;
    }
    public Player(){
        hand = new Hand();
        river = new River();
    }

    public Tile draw(){
        Wall wall = Wall.getInstance();

        return hand.add(wall.drawTile());
    }
    public void discard(Tile t){
        if(hand.containsTile(t)){
            hand.remove(t);
            river.add(t);
            return;
        }
        //System.out.println("Player doesn,'t contain tile:" + t + " . Operation ignored");
    }
    public CallPackage call(CallPackage input_package){//inaczej kształt w zaleznosci od tego od kogo kradnie etc. :c
        CallPackage output_package = new CallPackage(input_package); 
        Tile temp_tile = output_package.getTile();

        //prepares list of all calls and makes player choose one of them (choosing is part of player's interface)
        List<String> possible_calls = new ArrayList<String>(0);
        String[] wind_list = new String[]{"east","south","west","north"};
        if(canChi(temp_tile) && (wind_list[(Arrays.asList(wind_list).indexOf(output_package.getWind())+1)%4] == wind)){ //whatthefuck
            possible_calls.add("chi");
        }
        if(canPon(temp_tile)){possible_calls.add("pon");}
        if(canRon(temp_tile)){possible_calls.add("ron");}

        if(possible_calls.size()==0){
            output_package.preparePackage(false, null, null);
            return output_package;
        }

        String chosen_call = chooseCall(possible_calls);
        
        TileGroup group;
        switch (chosen_call) {
            case "skip":
                output_package.preparePackage(false, null, null);
                return output_package;
            case "ron":
                output_package.preparePackage(true, chosen_call, null);
                return output_package;
            case "chi":
                if(getHand().chiOptions(temp_tile).size() == 1){group = getHand().chiOptions(temp_tile).get(0);}
                else {group = new TileGroup(chooseGroup(getHand().chiOptions(temp_tile)));}
                output_package.preparePackage(true, chosen_call, group);
                return output_package;
            case "pon":
                if(getHand().ponOptions(temp_tile).size() == 1){group = new TileGroup(getHand().ponOptions(temp_tile).get(0));}
                else {group = new TileGroup(chooseGroup(getHand().ponOptions(temp_tile)));}
                output_package.preparePackage(true, chosen_call, group);
                return output_package;
            default:
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
    public boolean canRiichi(Tile t){
        Hand riichi_hand = new Hand(getHand());
        return(riichi_hand.inTenpai() && !(riichi_hand.isOpen()));
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
    }



    ///DIFFERENT AMONG SUBCLASSES
    public Tile chooseToDiscard(){//zamienić na: logika wyboru odrzutu
        //logic of discarding (for player: input, for bot: random/algorithm) (this: random)
        getHand().sort();
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
    
    /*public boolean chooseToChi(Tile t){
        return canChi(t);
    }*/
    public String chooseCall(List<String> possible_calls) {

        if(possible_calls.contains("ron")){return "ron";}

        return "skip";//"skip","ron","chi","pon"
    }
    public TileGroup chooseGroup(List<TileGroup> groups){

        return groups.get(0);
    }

    /////////THE SAME:
    @Override
    public String toString(){
        return (getHand().toString() + getRiver().toString());
    }

}
