import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.util.ArrayList;

public class DisplayGame {
    JFrame frame;
    Game game;
    boolean flag;
    static TileAssets tile_assets = new TileAssets();
    ArrayList<DisplayHand> display_hands = new ArrayList<>(); 
    ArrayList<DisplayRiver> display_rivers = new ArrayList<>();
    ArrayList<DisplayOpened> display_opened = new ArrayList<>();
    Calls calls;

    ImageIcon riichistick = new ImageIcon(".//src/Buttons//riichistick.png");
    ImageIcon riichistick_r = new ImageIcon(".//src/Buttons//riichistick2.png");

    ArrayList<JButton> riichis = new ArrayList<>();

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
        for(DisplayOpened opened : display_opened){
            opened.destroy();
        }
        for(JButton riichi : riichis){
            frame.getContentPane().remove(riichi);
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


            display_rivers = new ArrayList<>();
            display_rivers.add(new DisplayRiver(300, 600, frame, 0, false));
            display_rivers.add(new DisplayRiver(600, 600, frame, 1, false));
            display_rivers.add(new DisplayRiver(600, 300, frame, 2, false));
            display_rivers.add(new DisplayRiver(300, 300, frame, 3, false));

            display_opened.add(new DisplayOpened(856, 840, frame, 0, false));
            display_opened.add(new DisplayOpened(840, 0, frame, 1, false));
            display_opened.add(new DisplayOpened(0, 0, frame, 2, false));
            display_opened.add(new DisplayOpened(0, 856, frame, 3, false));

            riichis = new ArrayList<>();

            flag = false;
        }

        display_hands.get(curr_player).destroy();
        display_hands.get(curr_player).update();

        frame.repaint();
    }

    void drawRiichi(int player_nr){
        JButton temp;
        if(player_nr % 2 == 0) temp = new JButton(riichistick);
        else temp = new JButton(riichistick_r);
        temp.setFocusPainted(false);
        temp.setBorderPainted(false);
        temp.setContentAreaFilled(false);
        if(player_nr == 0)temp.setBounds(375,559,151,41);
        if(player_nr == 1)temp.setBounds(559,375,41,151);
        if(player_nr == 2)temp.setBounds(375,341,151,41);
        if(player_nr == 3)temp.setBounds(341,375,41,151);
        temp.setVisible(true);
        this.frame.getContentPane().add(temp);

        riichis.add(temp);
    }
    
    
}
