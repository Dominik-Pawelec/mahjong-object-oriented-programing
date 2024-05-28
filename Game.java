import java.util.ArrayList;
import java.util.Collections;
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
            players.add(new AI(this));
        }
        
        round_wind = "east";
        start_index = 0;
    }

    public void prepareRound(){

        if(start_index > 4){//pppp(marker)
            endResults();
            return;
        }

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
        //Collections.shuffle(players);//temp!!!!!!!!!!!!!!!!!!

        players.get((0 + start_index) % 4).setWind("east");
        players.get((1 + start_index) % 4).setWind("south");
        players.get((2 + start_index) % 4).setWind("west");
        players.get((3 + start_index) % 4).setWind("north");

        //start();
    }
    
    public void start(){
        GameLogic gl = new GameLogic();
        gl.mainLoop();
    }


    public void end(Player player, Tile winning_tile){//now only 1 player can win, might more than one; plus doesnt treat is correctly (in ron)
        System.out.println(player.getWind() + " - " + player.getHand() + " on tile: " + winning_tile);
        //changing winds

        if(!player.getWind().equals("east")){
            start_index ++;
        }

        try{
            Thread.sleep(3000);

        }catch(Exception e){}

        prepareRound();
        start();
    }
    public void ryukuoku(){
        System.out.println("ryukyoku, in tenpai:");
        for(int i = 0; i < 4; i++){
            if(players.get(i).getHand().inTenpai()){
                System.out.println(players.get(i).getWind());
            }
        }
        try{
            Thread.sleep(2000);

        }catch(Exception e){}
        for(int i = 0; i < 4; i++){
            if (players.get(i).getWind().equals("east") && players.get(i).getHand().inTenpai()){
                prepareRound();
                start();
                return;   
            }
        }
        start_index ++;
        prepareRound();
        start();
        return; 
    }

    public void endResults(){
        System.out.println("GAME HAS ENDED SUCCESFULLY");
    }
    
    public class GameLogic{

        boolean is_players_turn = false;
        int curr_player_index;
        int nr = 0;
        Tile recent_drawn;

        boolean someone_win;
        Player winner;
        Tile winning_tile;

        GameLogic(){
            curr_player_index = start_index%4;
            someone_win = false;
        }
        public void mainLoop(){
            if(start_index > 3){// + zalezne od kasy graczy
                endResults();
                return;
            }
            while(Wall.getInstance().size() > 14 && !someone_win){
                takeTurn();
            }
            if(someone_win){end(winner,winning_tile);return;}
            ryukuoku();
        }

        public void takeTurn(){
            Player curr_player = players.get(curr_player_index); 
            recent_drawn = curr_player.draw();


            curr_player.getHand().sort2();
            printState();

            if(curr_player.getHand().isWinning()){
                if(curr_player.chooseToTsumo()){
                    winner = curr_player;
                    winning_tile = recent_drawn;
                    someone_win = true;
                    return;
                }
            }
            if(curr_player.getRiichi()){
                curr_player.discard(recent_drawn);
            }
            else{
                boolean flag = false;
                if(curr_player.canRiichi()){
                    if(curr_player.chooseToRiichi()){
                        flag = true;
                    }
                }
                
                curr_player.discard(curr_player.chooseToDiscard());
                if(curr_player.getHand().inTenpai() && flag){
                    System.out.println(curr_player.getWind() + ": RIICHI!");
                    curr_player.setRiichi(true);
                    try{
                        Thread.sleep(3000);
                    }catch(Exception e){}
                }
                else{
                    curr_player.setRiichi(false);
                }
            }

            curr_player.getHand().sort();
            Tile recent_discard = curr_player.getRiver().getRecent();
            printState();

            analyseDiscarded(recent_discard);

            curr_player_index = (curr_player_index+1)%4;

            try{
                Thread.sleep(0);//TODO

            }catch(Exception e){}
        }

        public void discardTurn(int prev_id,int steal_id, CallPackage call_pack){
            curr_player_index = steal_id;
            Player curr_player = players.get(steal_id);
            Tile stolen = players.get(prev_id).getRiver().getRecent();
            players.get(prev_id).getRiver().remove();

            curr_player.getHand().add(stolen);

            curr_player.call(call_pack.tileGroup());
            
            System.out.println(call_pack.callType());
            printState();

            curr_player.discard(curr_player.chooseToDiscard());

            Tile recent_discard = curr_player.getRiver().getRecent();
            printState();
            analyseDiscarded(recent_discard);

        }

        public void analyseDiscarded(Tile disard_tile){
            CallPackage input_package = new CallPackage(disard_tile, players.get(curr_player_index).getWind());
            List<CallPackage> packages = new ArrayList<>(0);
            for(int i = 0; i < 4; i++){
                packages.add( players.get(i).makePackage(input_package));
            }

            boolean is_call = false;
            String[] call_list = new String[4];
            for(int i = 0; i < 4; i++){
                call_list[i] = "";
                if(packages.get(i).isCalling()){
                    is_call = true;
                    call_list[i] = packages.get(i).callType();
                }
            }
            if (!is_call){
                return;
            }
            //search for rons: pozniej: dodaje do lsity wygranych
            for(int i = 0; i < 4; i++){
                if(call_list[i].equals("ron")){
                    winner = players.get(i);
                    winning_tile = disard_tile;
                    someone_win = true;
                    return;
                }
            }
            for(int i = 0; i < 4; i++){
                if(call_list[i].equals("pon")){
                    discardTurn(curr_player_index,i , packages.get(i));
                    return;
                }
            }
            for(int i = 0; i < 4; i++){
                if(call_list[i].equals("chi")){
                    discardTurn(curr_player_index,i , packages.get(i));
                    return;
                }
            }
        }
    }

    public void printState(){
        System.out.print("\033[H\033[2J");  
            System.out.flush(); 
        System.out.println("Game state:");
        for(int i = 0; i < 4; i++){
            System.out.println(players.get(i).getWind() + ": "+ players.get(i));
        }
    }
}
