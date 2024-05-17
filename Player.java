public class Player {
    Hand hand;
    River river;

    public Player(Hand h, River r){
        hand = h;
        river = r;
    }

    public void draw(){

    }
    public void discard(Tile t){
        hand.remove(t);
        river.add(t);
    }

}
