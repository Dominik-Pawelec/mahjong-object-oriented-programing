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

        players.get((0 + start_index) % 4).setWind("east");
        players.get((1 + start_index) % 4).setWind("south");
        players.get((2 + start_index) % 4).setWind("west");
        players.get((3 + start_index) % 4).setWind("north");

        //start();
    }
    public void prepareRound(Hand h){
        Wall.getInstance().build();
        players.get(0).setHand(h);
        for(int p = 1; p < 4; p++){
            players.get(p).reset();
            for(int i = 0; i < 13; i++){
                players.get(p).draw();
            }
        }

        players.get((0 + start_index) % 4).setWind("east");
        players.get((1 + start_index) % 4).setWind("south");
        players.get((2 + start_index) % 4).setWind("west");
        players.get((3 + start_index) % 4).setWind("north");

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

    public void end(Player player, Tile winning_tile){//now only 1 player can win, might more than one
        System.out.println(player.getWind() + " - " + player.getHand() + " on tile: " + winning_tile);
        //changing winds

        if(player.getWind() != "east"){
            start_index ++;
        }

        try{
            Thread.sleep(3000);

        }catch(Exception e){}

        prepareRound();
        start();
    }

    
    public class GameLogic{

        boolean is_players_turn = false;
        int curr_player_index;
        int nr = 0;
        Tile recent_drawn;

        GameLogic(){
            curr_player_index = start_index;
        }

        public void takeTurn(){
            Player curr_player = players.get(curr_player_index); 
            recent_drawn = curr_player.draw();

            curr_player.getHand().sort();
            printState();

            if(curr_player.getHand().isWinning()){//zamiast getHand().isWinning();
                if(curr_player.chooseToTsumo()){
                    end(curr_player, recent_drawn);
                    return;
                }
            }

            curr_player.discard(players.get(curr_player_index).chooseToDiscard());
            Tile recent_discard = curr_player.getRiver().getRecent();

            analyseDiscarded(recent_discard);
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


        public void analyseDiscarded(Tile disard_tile){
            
            
        }



    }





    


    public void printState(){
        System.out.println("Game state:");
        for(int i = 0; i < 4; i++){
            System.out.println(players.get(i).getWind() + ": "+ players.get(i));
        }
    }
}
