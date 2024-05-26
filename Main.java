public class Main {
    //lista 6 zad 3 to buffory
    // wszystkie referencje są referencją
    public static void main(String[] args)
    {

        /*Game game = new Game(new Human());*/

        /*Game game = new Game(new Human());
        game.startHanchan();
        game.prepareRound(new Hand(new Tile("1m"),new Tile("1m"),new Tile("1m"),new Tile("2m"),
        new Tile("3m"),new Tile("4m"),new Tile("5m"),new Tile("6m"),new Tile("7m"),new Tile("8m"),
        new Tile("9m"),new Tile("9m"),new Tile("9m")));
        game.start();

        /* 
        Game game = new Game(new Human());
        game.startHanchan();
        game.prepareRound();
        game.start();*/

        /*Player x = new Player(new Hand(new Tile("1m"),new Tile("1m"),new Tile("1m"),new Tile("2m"),
        new Tile("3m"),new Tile("4m"),new Tile("5m"),new Tile("6m"),new Tile("7m"),new Tile("8m"),
        new Tile("9m"),new Tile("9m"),new Tile("9m"), new Tile("B")),new River());

        System.out.println(x.canRiichi());*/
        


        Calculator calc = new Calculator();

        int p = calc.shanten(new Hand(new Tile("1m"),new Tile("1m"),new Tile("1m"),new Tile("2m"),
        new Tile("3m"),new Tile("4m"),new Tile("5p"),new Tile("6p"),new Tile("7p"),new Tile("8s"),
        new Tile("9s"),new Tile("9m"),new Tile("9s"), new Tile("B")));
    
    
        System.out.println(p);
    }
}
