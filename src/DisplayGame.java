import javax.swing.JFrame;
import java.util.ArrayList;

public class DisplayGame {
    JFrame frame;
    Game game;
    boolean flag;
    static TileAssets tile_assets = new TileAssets();
    ArrayList<DisplayHand> display_hands = new ArrayList<>(); 
    Calls calls;

    ArrayList<DisplayRiver> display_rivers = new ArrayList<>();


    public DisplayGame(JFrame frame, Game game){
        this.frame = frame;
        this.game = game;
        calls = new Calls(frame, 750, 750, true); 

        flag = true;
    }
    
    void reset(){
        flag = true;
        for(DisplayRiver river : display_rivers){
            river.destroy();
        }
    }
    
 
    void draw(int curr_player){
        

        if(flag){
            for(int i = 0 ; i < display_hands.size(); i ++){
                display_hands.get(i).destroy();
            }

            display_hands = new ArrayList<>(); 
            display_hands.add(new DisplayHand(164, 840, 0, 44, 60, frame, game.players.get(0).hand,false,true));
            display_hands.add(new DisplayHand(840, 692, 1, 60, 44, frame, game.players.get(1).hand,true,false));
            display_hands.add(new DisplayHand(692, 0, 2, 44, 60, frame, game.players.get(2).hand,true,false));
            display_hands.add(new DisplayHand(0, 164, 3, 60, 44, frame, game.players.get(3).hand,true,false));
            flag = false;

            display_rivers = new ArrayList<>();
            display_rivers.add(new DisplayRiver(300, 600, frame, 0, false));
            display_rivers.add(new DisplayRiver(600, 600, frame, 1, false));
            display_rivers.add(new DisplayRiver(600, 300, frame, 2, false));
            display_rivers.add(new DisplayRiver(300, 300, frame, 3, false));
        }

        display_hands.get(curr_player).destroy();
        display_hands.get(curr_player).update();

        frame.repaint();
    }
    
    
}
