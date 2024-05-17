public class Main { //lista 6 zad 3 to buffory
    public static void main(String[] args)
    {
        /*
        Tile x = new Tile(2,"man");
        System.out.println(x);

        TileGroup test_group = new TileGroup(x,x,x);

        System.out.println(test_group);

        Wall test_wall = new Wall();

        test_wall.Build();

        System.out.println(test_wall);

        x = test_wall.DrawTile();

        System.out.println(x);
        System.out.println(test_wall);

        Hand hand = new Hand();

        for(int i = 0; i < 5; i++){
            hand.Add(new Tile(1,"man"));
        }

        TileGroup pon_test = new TileGroup(new Tile(1,"man"),
                             new Tile(4,"man"),
                             new Tile(3,"man"));

        System.out.println(hand.IsOpen());
        System.out.println(hand.ContainsTile(new Tile(1,"man")));

        hand.OpenBlock(pon_test);
        System.out.println(hand.IsOpen());

        pon_test.sort();
        System.out.println(pon_test);

        System.out.println( new Tile(4,"man").compareTo( new Tile(5,"man")));
        */
        Wall wall = new Wall();
        wall.build();
        System.out.println(wall);

        Hand player_test = new Hand(new Tile(1,"man"),new Tile(2,"man"),
                            new Tile(3,"man"),new Tile(4,"man"),
                        new Tile(5,"man"),new Tile(5,"man"));

        Tile test = new Tile(1,"man");

        System.out.println(player_test.chiOptions(test));
        System.out.println(player_test.ponOptions(test));



        Player player = new Player(player_test,new River());

        for(int i = 1; i < 5; i++){
            player.discard(new Tile(i,"man"));
            System.out.print(player.river);
            System.out.println(player.hand);
        }
        player_test.remove(test);
        System.out.println(player_test);
        
    }
}
