import java.util.ArrayList;
import java.util.List;

public class Game {
    List<Player> players;
    String round_wind;//more complex structure rememenbering repeats nr of round etc.
    int start_index;

    public Game(){
        players = new ArrayList<Player>();
    }

    public Game(Player p1){
        players = new ArrayList<Player>();
        players.add(p1);
    }

    public void startHanchan(){
        int temp = players.size();
        for(int i = 0; i < 4-temp; i++){
            players.add(new Player());
        }
        players.get(0).setWind("east");
        players.get(1).setWind("south");
        players.get(2).setWind("west");
        players.get(3).setWind("north");

        round_wind = "east";
        start_index = 0;
    }

    public void prepareRound(){
        Wall.getInstance().build();

        for(int p = 0; p < 4; p++){
            players.get(p).reset();
            for(int i = 0; i < 13; i++){
                players.get(p).draw();
            }
            
        }

        //start();
    }
    
    public void start(){
        GameLogic gl = new GameLogic();
        while(Wall.getInstance().size() > 10){
            gl.takeTurn();
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        System.out.println("Game has ended");
    }

    
    public class GameLogic{

        boolean is_players_turn = false;
        int curr_player_index;
        int nr = 0;

        GameLogic(){
            curr_player_index = start_index;
        }

        public synchronized void takeTurn(){
            players.get(curr_player_index).draw();
            players.get(curr_player_index).getHand().sort();
            printState();
            players.get(curr_player_index).discard(players.get(curr_player_index).chooseToDiscard());
            Tile recent_discard = players.get(curr_player_index).getRiver().getRecent();
            //System.out.println(players.get(curr_player_index).getWind() +" "+ players.get(curr_player_index) + ": " +recent_discard);
            
            is_players_turn = false;
            ////



            //sprawdzenie czy nikt nie kradnie, jeśli nie kradnie to:
            curr_player_index = (curr_player_index+1)%4;
            is_players_turn = true;
            //System.out.println(nr);
            //nr ++;

            try{
                Thread.sleep(400);

            }catch(Exception e){}
        }
        

        public synchronized void analyseDiscarded(Tile disard_tile){


        }



    }





    


    public void printState(){
        System.out.println("Game state:");
        for(int i = 0; i < 4; i++){
            System.out.println(players.get(i));
        }
    }
}
