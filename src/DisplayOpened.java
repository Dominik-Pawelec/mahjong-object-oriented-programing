import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class DisplayOpened {
    public ArrayList<JButton> tiles = new ArrayList<JButton>();
    static TileAssets tile_assets = new TileAssets();
    int d;
    JFrame frame;
    int start_x;
    int start_y;
    int curr_x;
    int curr_y;
    int w,h, nr;
    boolean light_mode;
    

    public DisplayOpened(int x, int y, JFrame frame, int direction, boolean light_mode){
        this.frame = frame;
        this.d = direction;
        this.light_mode = light_mode;
        curr_x = x;
        start_x = x;
        curr_y = y;
        start_y = y;
        nr = 0;
        if(d%2 == 0) {
            w = 44;
            h = 60;
        }
        else{
            w = 60;
            h = 44;
        }
    }   

    public void displayNewBlock(TileGroup t){
        for(int i = 0; i < 3; i++){
            tiles.add(new JButton(tile_assets.getIcon(t.get(i), d, light_mode)));
        }
        for(int i = 0; i < 3; i++){
            JButton temp2 = tiles.get((2-i)+ nr);
            temp2.setFocusPainted(false);
            temp2.setBorderPainted(false);
            temp2.setContentAreaFilled(false);
            temp2.setBounds(curr_x,curr_y,w,h);
            temp2.setVisible(true);
            this.frame.getContentPane().add(temp2);
            
            if(d == 0) curr_x -=44;
            if(d == 1) curr_y +=44;
            if(d == 2) curr_x +=44;
            if(d == 3) curr_y -=44;
            
            
        }
        nr += 3;
        frame.repaint();
    }



    public void destroy(){
        for (JButton tile : tiles) {
            frame.getContentPane().remove(tile);
        }
        nr = 0;
        curr_x = start_x;
        curr_y = start_y;
        tiles = new ArrayList<JButton>();
    }
}
 