public class Player{
    Hand hand;
    River river;
    String wind;
    //money etc.
    //autosort
    boolean in_riichi = false;

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
    public void call(TileGroup tile_group, Tile t){//inaczej kształt w zaleznosci od tego od kogo kradnie etc. :c
        getHand().add(t);
        getHand().openBlock(tile_group);
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
    public boolean chooseToRon(Tile t){
        return true;
    }
    /*public boolean chooseToChi(Tile t){
        return canChi(t);
    }*/
    //public boolean chooseToCall(Tile t) {

    //}

    /////////THE SAME:
    @Override
    public String toString(){
        return (getHand().toString() + getRiver().toString());
    }

}
