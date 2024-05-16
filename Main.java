public class Main {
    public static void main(String[] args)
    {
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

        System.out.println(hand.IsOpen());
        hand.OpenBlock(test_group);
        System.out.println(hand.IsOpen());

    }
}
