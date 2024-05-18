public class Player {
    Hand hand;
    River river;
    String wind;

    //autosort

    public Player(Hand h, River r){
        hand = h;
        river = r;
    }
    public Player(){
        hand = new Hand();
        river = new River();
    }

    public void draw(){
        Wall wall = Wall.getInstance();

        hand.add(wall.drawTile());
    }
    public void discard(Tile t){
        hand.remove(t);
        river.add(t);
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

}
