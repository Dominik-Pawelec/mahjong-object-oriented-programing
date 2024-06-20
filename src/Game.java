import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game {
    List<Player> players;
    int scores[] = {0,0,0,0};
    String round_wind;
    int start_index;
    JFrame frame;

    DisplayGame display;
    int curr_player_index;

    boolean audio_on;

    public Game(){
        players = new ArrayList<Player>();
    }

    public Game(Player p1, JFrame frame, boolean audio_on){
        players = new ArrayList<Player>();
        players.add(p1);
        this.frame = frame;
        this.audio_on = audio_on;
    }

    public void startHanchan(){
        int temp = players.size();
        for(int i = 0; i < 4-temp; i++){
            players.add(new AI(this));
        }
        
        round_wind = "east";
        start_index = 0;
    }
    public void startHanchan(DisplayGame display){
        int temp = players.size();
        for(int i = 0; i < 4-temp; i++){
            players.add(new AI(this));
        }
        
        round_wind = "east";
        start_index = 0;

        this.display = display;
        
        players.get(0).pinToDisplay(display);
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

        display.reset();
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

    }
    
    public void start(){
        GameLogic gl = new GameLogic();
        gl.mainLoop();
    }


    public void end(int winner_index, Tile winning_tile){
        System.out.println(players.get(winner_index).getWind() + " - " + players.get(winner_index).getHand() + " on tile: " + winning_tile);
        for(int i = 0; i < 4; i++){
            display.display_hands.get(i).hideHand();
        }
        display.display_hands.get(winner_index).showHand();
        scores[winner_index] += 1;
        //changing winds
        if(!players.get(winner_index).getWind().equals("east")){
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
                display.display_hands.get(i).showHand();
                System.out.println(players.get(i).getWind());
            }
            else{
                display.display_hands.get(i).hideHand();
            }
        }
        try{
            Thread.sleep(3000);

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
        display.reset();
        System.out.println("GAME HAS ENDED SUCCESFULLY");
    }
    
    public class GameLogic{
        File discard_clip = new File(".//src/Audio//discard.wav");
        AudioInputStream discard_stream;
        Clip discard = null;
        File riichi_clip = new File(".//src/Audio//riichi.wav");
        AudioInputStream riichi_stream;
        Clip riichi = null;
        File tsumo_clip = new File(".//src/Audio//tsumo.wav");
        AudioInputStream tsumo_stream;
        Clip tsumo = null;
        File ron_clip = new File(".//src/Audio//ron.wav");
        AudioInputStream ron_stream;
        Clip ron = null;
        File chi_clip = new File(".//src/Audio//chi.wav");
        AudioInputStream chi_stream;
        Clip chi = null;
        File pon_clip = new File(".//src/Audio//pon.wav");
        AudioInputStream pon_stream;
        Clip pon = null;
        
        ///dzwieki 

        boolean is_players_turn = false;
        
        int nr = 0;
        Tile recent_drawn;

        boolean someone_win;
        int winner_index;
        Tile winning_tile;

        GameLogic(){
            curr_player_index = start_index%4;
            someone_win = false;

            //dzwieki:
            try {
                discard_stream = AudioSystem.getAudioInputStream(discard_clip);
                discard = AudioSystem.getClip();
                discard.open(discard_stream);       
                riichi_stream = AudioSystem.getAudioInputStream(riichi_clip);
                riichi = AudioSystem.getClip();
                riichi.open(riichi_stream);    
                tsumo_stream = AudioSystem.getAudioInputStream(tsumo_clip);
                tsumo = AudioSystem.getClip();
                tsumo.open(tsumo_stream);
                ron_stream = AudioSystem.getAudioInputStream(ron_clip);
                ron = AudioSystem.getClip();
                ron.open(ron_stream);  
                chi_stream = AudioSystem.getAudioInputStream(chi_clip);
                chi = AudioSystem.getClip();
                chi.open(chi_stream);    
                pon_stream = AudioSystem.getAudioInputStream(pon_clip);
                pon = AudioSystem.getClip();
                pon.open(pon_stream);  
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
        public void mainLoop(){
            if(start_index > 3){// + zalezne od kasy graczy
                endResults();
                return;
            }
            for(int i = 0; i < 4; i++){
                players.get(i).hand.sort();
            }
            while(Wall.getInstance().size() > 14 && !someone_win){
                takeTurn();
            }
            if(someone_win){end(winner_index,winning_tile);return;}
            ryukuoku();
        }

        public void takeTurn(){
            Player curr_player = players.get(curr_player_index); 
            recent_drawn = curr_player.draw();

            curr_player.getHand().sort2();
            printState();

            if(curr_player.getHand().isWinning()){
                if(curr_player.chooseToTsumo()){
                    winner_index = curr_player_index;
                    winning_tile = recent_drawn;
                    someone_win = true;
                    if(audio_on){
                        tsumo.setMicrosecondPosition(0);
                        tsumo.start();}
                    return;
                }
            }
            if(curr_player.getRiichi()){
                try{
                    Thread.sleep(400);
                }catch(Exception e){}

                curr_player.discard(recent_drawn);
                if(audio_on){
                discard.setMicrosecondPosition(0);
                discard.start();}
            }
            else{
                boolean flag = false;
                if(curr_player.canRiichi()){
                    if(curr_player.chooseToRiichi()){
                        flag = true;
                    }
                }
                
                curr_player.discard(curr_player.chooseToDiscard());
                if(audio_on){
                    discard.setMicrosecondPosition(0);
                    discard.start();}

                if(curr_player.getHand().inTenpai() && flag){
                    System.out.println(curr_player.getWind() + ": RIICHI!");
                    curr_player.setRiichi(true);
                    display.display_rivers.get(curr_player_index).setRichiiTile();
                    display.drawRiichi(curr_player_index);
                    if(audio_on){
                        riichi.setMicrosecondPosition(0);
                        riichi.start();}
                    try{
                        Thread.sleep(500);
                    }catch(Exception e){}
                }
                else{
                    curr_player.setRiichi(false);
                }
            }

            curr_player.getHand().sort();
            Tile recent_discard = curr_player.getRiver().getRecent();
            display.display_rivers.get(curr_player_index).addTile(recent_discard);
            printState();

            analyseDiscarded(recent_discard);

            curr_player_index = (curr_player_index+1)%4;
        }

        public void discardTurn(int prev_id,int steal_id, CallPackage call_pack){
            
            display.display_rivers.get(prev_id).removeLastTile();

            curr_player_index = steal_id;
            Player curr_player = players.get(steal_id);
            Tile stolen = players.get(prev_id).getRiver().getRecent();
            players.get(prev_id).getRiver().remove();

            curr_player.getHand().add(stolen);

            curr_player.call(call_pack.tileGroup());
            
            System.out.println(call_pack.callType());

            display.display_opened.get(curr_player_index).displayNewBlock(call_pack.tileGroup());

            printState();

            curr_player.discard(curr_player.chooseToDiscard());
            if(audio_on){
                discard.setMicrosecondPosition(0);
                discard.start();}

            Tile recent_discard = curr_player.getRiver().getRecent();
            display.display_rivers.get(curr_player_index).addTile(recent_discard);
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
            for(int i = 0; i < 4; i++){
                if(call_list[i].equals("ron")){
                    try{
                        Thread.sleep(200);
                    }catch(Exception e){}
                    winner_index = i;
                    winning_tile = disard_tile;
                    someone_win = true;
                    if(audio_on){
                        ron.setMicrosecondPosition(0);
                        ron.start();}
                    return;
                }
            }
            for(int i = 0; i < 4; i++){
                if(call_list[i].equals("pon")){
                    try{
                        Thread.sleep(200);
                    }catch(Exception e){}
                    if(audio_on){
                        pon.setMicrosecondPosition(0);
                        pon.start();}
                    discardTurn(curr_player_index,i , packages.get(i));
                    return;
                }
            }
            for(int i = 0; i < 4; i++){
                if(call_list[i].equals("chi")){
                    try{
                        Thread.sleep(200);
                    }catch(Exception e){}
                    if(audio_on){
                        chi.setMicrosecondPosition(0);
                        chi.start();}
                    discardTurn(curr_player_index,i , packages.get(i));
                    return;
                }
            }
        }
    }

    public void printState(){

        System.out.println("aaaa");

        display.draw(curr_player_index);

    }
}
