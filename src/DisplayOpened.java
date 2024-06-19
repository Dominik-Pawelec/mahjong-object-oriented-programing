import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

public class DisplayOpened {
    List<TileGroup> opened_blocks;
    public ArrayList<JButton> tiles = new ArrayList<JButton>();
    static TileAssets tile_assets = new TileAssets();
    int d;
    JFrame frame;

    public DisplayOpened(int x, int y, JFrame frame, int direction, Boolean light){
        this.frame = frame;
        this.d = direction;
    }   




    public void destroy(){
        for (JButton tile : tiles) {
            frame.getContentPane().remove(tile);
        }
    }
}
 