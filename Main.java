import java.util.List;
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
         */
         
        Calculator calc = new Calculator();

        int x = calc.shanten(new Hand(new Tile("3m"),new Tile("2m"),new Tile("4m"),new Tile("7m"),new Tile("8m"),new Tile("B"),new Tile("B"),
        new Tile("2s"),new Tile("2s"),new Tile("3s"),new Tile("6s"),new Tile("8s"),new Tile("5p"),new Tile("5p")));
        System.out.println(x);
        Game game = new Game();
        for(int i = 0; i < 20; i++){
            game.startHanchan();
            game.prepareRound();
            game.start();
        }
        /*Player x = new Player(new Hand(new Tile("1m"),new Tile("1m"),new Tile("1m"),new Tile("2m"),
        new Tile("3m"),new Tile("4m"),new Tile("5m"),new Tile("6m"),new Tile("7m"),new Tile("8m"),
        new Tile("9m"),new Tile("9m"),new Tile("9m"), new Tile("B")),new River());

        System.out.println(x.canRiichi());
        


        Calculator calc = new Calculator();

        int p = calc.shanten(new Hand(new Tile("1m"),new Tile("1m"),new Tile("1m"),new Tile("2m"),
        new Tile("3m"),new Tile("4m"),new Tile("5m"),new Tile("6m"),new Tile("7m"),new Tile("8m"),
        new Tile("9m"),new Tile("9m"),new Tile("9m"), new Tile("B")));
    
    


        List<Integer[]> x = calc.possibleShapes(new TileGroup(new Tile("1m"),new Tile("1m"),new Tile("1m"),new Tile("2m"),
        new Tile("3m"),new Tile("4m"),new Tile("5m"),new Tile("6m"),new Tile("7m"),new Tile("8m"),
        new Tile("9m"),new Tile("9m"),new Tile("9m"), new Tile("B")),0,0);
        for(int i = 0; i < x.size(); i++){
            if(x.get(i)[0] > 3){
                System.out.print("[ ");
                System.out.print(x.get(i)[0]);
                System.out.print(" - ");
                System.out.print(x.get(i)[1]);
                System.out.print("] ");
            }

        }
        System.out.println();
        System.out.println(p); */
    }
}
