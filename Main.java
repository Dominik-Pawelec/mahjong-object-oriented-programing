public class Main {
    //lista 6 zad 3 to buffory
    // wszystkie referencje są referencją
    public static void main(String[] args)
    {

        Game game = new Game(new Human());

        game.startHanchan();
        game.prepareRound();

        game.printState();

        game.start();
        
    }
}
