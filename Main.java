import java.util.List;
public class Main {
    //lista 6 zad 3 to buffory
    // wszystkie referencje są referencją
    public static void main(String[] args)
    {
         
        Calculator calc = new Calculator();

        
        Game game = new Game(new Human());
        for(int i = 0; i < 20; i++){
            game.startHanchan();
            game.prepareRound();
            game.start();
        }
    
    }
}
