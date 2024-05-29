import java.util.List;
public class Main {
    public static void main(String[] args)
    {
        Game game = new Game(new Human());//usun "new Human() by boty grały same przeciwko sobie"
        for(int i = 0; i < 20; i++){
            game.startHanchan();
            game.prepareRound();
            game.start();
        }
    
    }
}
