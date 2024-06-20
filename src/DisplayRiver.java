import java.util.ArrayList;
import javax.swing.*;

public class DisplayRiver{

    public ArrayList<JButton> river = new ArrayList<JButton>();
    public ArrayList<Tile> river_tiles = new ArrayList<Tile>();
    static TileAssets tile_assets = new TileAssets();
    JFrame frame;
    int x, y, d, w, h;
    int richiiTile = 100;
    Boolean light;

    public DisplayRiver(int x, int y, JFrame frame, int direction, Boolean light){
        this.frame = frame;
        this.x = x;
        this.y = y;
        d = direction;
        if(d%2 == 0) {
            w = 44;
            h = 60;
        }
        else{
            w = 60;
            h = 44;
        }
        this.light = light;
    }

    void setRichiiTile(){
        richiiTile = river.size();
    }

    void addTile(Tile tile){
        river_tiles.add(tile);
        JButton temp = new JButton();
        int s = river.size();
        if(s == richiiTile){
            temp = new JButton(tile_assets.getIcon(tile, d+1/* , light*/));
            if(s < 18){
                if(d == 0)temp.setBounds(x + w * (s % 6), y + h * (s / 6), h, w);
                if(d == 1)temp.setBounds(x + w * (s / 6), y - h * (s % 6), h, w);
                if(d == 2)temp.setBounds(x - w * (s % 6)-16, y - h * (s / 6), h, w);
                if(d == 3)temp.setBounds(x - w * (s / 6), y + h * (s % 6), h, w);
            }
            else{
                if(d == 0)temp.setBounds(x + w * (s % 6 + 6), y + h * (s / 6 - 1), h, w);
                if(d == 1)temp.setBounds(x + w * (s / 6 - 1), y - h * (s % 6 + 6), h, w);
                if(d == 2)temp.setBounds(x - w * (s % 6 + 6), y - h * (s / 6 - 1), h, w);
                if(d == 3)temp.setBounds(x - w * (s / 6 - 1), y + h * (s % 6 + 6), h, w);
            }
        }
        else{
            temp = new JButton(tile_assets.getIcon(tile, d/* , light*/));
            s = river.size();
            if(s > richiiTile && s / 6 == richiiTile / 6){
                if(s < 18){
                    if(d == 0)temp.setBounds(x + w * (s % 6) + 16, y + h * (s / 6), w, h);
                    if(d == 1)temp.setBounds(x + w * (s / 6), y - h * (s % 6) - 16, w, h);
                    if(d == 2)temp.setBounds(x - w * (s % 6) - 16, y - h * (s / 6), w, h);
                    if(d == 3)temp.setBounds(x - w * (s / 6), y + h * (s % 6) + 16, w, h);
                }
                else{
                    if(d == 0)temp.setBounds(x + w * (s % 6 + 6) + 16, y + h * (s / 6 - 1), w, h);
                    if(d == 1)temp.setBounds(x + w * (s / 6 - 1), y - h * (s % 6 + 6) - 16, w, h);
                    if(d == 2)temp.setBounds(x - w * (s % 6 + 6) - 16, y - h * (s / 6 - 1), w, h);
                    if(d == 3)temp.setBounds(x - w * (s / 6 - 1), y + h * (s % 6 + 6) + 16, w, h);
                }
            }
            else{
                if(s < 18){
                    if(d == 0)temp.setBounds(x + w * (s % 6), y + h * (s / 6), w, h);
                    if(d == 1)temp.setBounds(x + w * (s / 6), y - h * (s % 6), w, h);
                    if(d == 2)temp.setBounds(x - w * (s % 6), y - h * (s / 6), w, h);
                    if(d == 3)temp.setBounds(x - w * (s / 6), y + h * (s % 6), w, h);
                }
                else{
                    if(d == 0)temp.setBounds(x + w * (s % 6 + 6), y + h * (s / 6 - 1), w, h);
                    if(d == 1)temp.setBounds(x + w * (s / 6 - 1), y - h * (s % 6 + 6), w, h);
                    if(d == 2)temp.setBounds(x - w * (s % 6 + 6), y - h * (s / 6 - 1), w, h);
                    if(d == 3)temp.setBounds(x - w * (s / 6 - 1), y + h * (s % 6 + 6), w, h);
                }
            }
        }
        
        temp.setFocusPainted(false);
        temp.setBorderPainted(false);
        temp.setContentAreaFilled(false);
        river.add(temp);
        frame.getContentPane().add(river.get(s));
        frame.repaint();
    }

    void removeLastTile(){
        frame.remove(river.get(river.size() - 1));
        river_tiles.remove(river_tiles.size() - 1);
        river.remove(river.size() - 1);
        frame.repaint();
    }

    public void destroy(){
        for (JButton tile : river) {
            frame.getContentPane().remove(tile);
        }
        river = new ArrayList<>();
        frame.repaint();
        richiiTile = 100;
    }
}