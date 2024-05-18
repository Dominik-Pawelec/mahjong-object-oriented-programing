import java.util.ArrayList;
import java.util.List;

public class Game {
    List<Player> players;
    String round_wind;//more complex structure rememenbering repeats nr of round etc.

    public Game(){
        players = new ArrayList<Player>();
    }

    public void startHanchan(){
        for(int i = 0; i < 4; i++){
            players.add(new Player());
        }
        players.get(0).setWind("east");
        players.get(1).setWind("south");
        players.get(2).setWind("west");
        players.get(3).setWind("north");

        round_wind = "east";
    }

    public void prepareRound(){
        Wall.getInstance().build();

        for(int p = 0; p < 4; p++){
            for(int i = 0; i < 13; i++){
                players.get(p).draw();
            }
            if(players.get(p).getWind() == "east"){
                players.get(p).draw();
            }
        }


    }
    

    public void printState(){
        System.out.println("Game state:");
        for(int i = 0; i < 4; i++){
            System.out.print("player ");
            System.out.print(i+1);
            System.out.println(":");
            System.out.print("\thand: ");
            System.out.println(players.get(i).getHand());
            System.out.print("\triver: ");
            System.out.println(players.get(i).getRiver());
        }
    }
}
