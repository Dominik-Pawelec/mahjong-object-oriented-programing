import java.util.List;
public class Main {
    //lista 6 zad 3 to buffory
    // wszystkie referencje są referencją
    public static void main(String[] args)
    {
         
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
    
    }
}
