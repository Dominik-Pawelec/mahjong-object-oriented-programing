import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;


public class DisplayHand {
    public ArrayList<JButton> jbutton_hand = new ArrayList<JButton>();
    static TileAssets tile_assets = new TileAssets();
    int x,y,direction,size_x,size_y;
    boolean is_hidden, is_players;
    JFrame frame;
    Hand raw_hand;

    boolean was_chosen_to_discard, light_mode;
    int tile_to_discard;

    int prev_hand_size = 13;


    public DisplayHand(int x, int y, int direction, int tile_size_x, int tile_size_y, JFrame frame, Hand hand, boolean is_hidden, boolean is_players, boolean light_mode){
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.size_x = tile_size_x;
        this.size_y = tile_size_y;
        this.frame = frame;
        this.raw_hand = hand;
        this.is_hidden = is_hidden;
        this.is_players = is_players;
        this.light_mode = light_mode;

        update();
    }

    public void update(){
        for(int i = 0; i < jbutton_hand.size(); i++){
            frame.getContentPane().remove(jbutton_hand.get(i));
        }
        
        jbutton_hand = new ArrayList<JButton>();
        for(int i = 0; i < raw_hand.group.size(); i++){
            if(is_hidden){jbutton_hand.add( new JButton(tile_assets.getIcon(direction,light_mode)) );}
            else{jbutton_hand.add( new JButton(tile_assets.getIcon(raw_hand.get(i),direction, light_mode)));}

            JButton temp_button = jbutton_hand.get(i);
            temp_button.setBorderPainted(false);
            temp_button.setFocusPainted(false);
            temp_button.setContentAreaFilled(false);
            this.frame.getContentPane().add(temp_button);
            
            int draw = 0;
            if(raw_hand.group.size()%3 != 1){
                if(Math.abs(prev_hand_size - raw_hand.group.size()) < 2 && i == raw_hand.group.size()-1 ) draw = 10;
            }

            if(direction == 0){temp_button.setBounds(x + i*size_x+draw, y,size_x, size_y);}
            if(direction == 1){temp_button.setBounds(x , y - i*size_y-draw,size_x, size_y);}
            if(direction == 2){temp_button.setBounds(x - i*size_x-draw, y,size_x, size_y);}
            if(direction == 3){temp_button.setBounds(x , y + i*size_y+draw,size_x, size_y);}
            
            temp_button.setVisible(true);

            if(is_players){
                temp_button.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        temp_button.setBounds(temp_button.getX(), y - 10, size_x, size_y);
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        temp_button.setBounds(temp_button.getX(), y , size_x, size_y);
                    }
                });
                temp_button.setEnabled(true);
                temp_button.setVisible(true);
            }
            
        }
        prev_hand_size = raw_hand.group.size();
        frame.repaint();
    }

    void showHand(){
        is_hidden = false;
        update();
    }
    void hideHand(){
        is_hidden = true;
        update();
    }
    public void destroy(){
        for (JButton tile : jbutton_hand) {
            frame.getContentPane().remove(tile);

        }
    }

    public int getTileToDiscard(){
        was_chosen_to_discard = false;
        while(!was_chosen_to_discard){
            for (JButton tile : jbutton_hand) {
                tile.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        tile_to_discard = jbutton_hand.indexOf(tile);
                        was_chosen_to_discard = true;
                    }
                });
            }
        }
        return tile_to_discard;
    }

}
