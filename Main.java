public class Main {
    //lista 6 zad 3 to buffory
    // wszystkie referencje są referencją
    public static void main(String[] args)
    {

        Game game = new Game();

        game.startHanchan();
        game.prepareRound();

        game.printState();

        game.prepareRound();

        game.printState();

        Hand hand = new Hand();

        for(int i = 0; i < 14; i++){
            hand.add(new Tile(2,"man"));
        }

        hand = new Hand(new Tile(2,"man"),new Tile(3,"man"),new Tile(4,"man"),
        new Tile(2,"man"),new Tile(3,"man"),new Tile(4,"man"),
        new Tile(2,"man"),new Tile(3,"man"),new Tile(4,"man"),
        new Tile(2,"man"),new Tile(3,"man"),new Tile(4,"man"),
        new Tile(2,"man"));
        System.out.println(hand.isWinning());
        System.out.println(hand);
        hand.openBlock(new TileGroup(new Tile(2,"man"),new Tile(2,"man"),new Tile(2,"man")));
        hand.openBlock(new TileGroup(new Tile(2,"man"),new Tile(3,"man"),new Tile(4,"man")));
        System.out.println(hand);
        System.out.println(hand.isWinning());
        System.out.println(hand.inTenpai());
        System.out.println(hand.winningTiles());
    }
}
