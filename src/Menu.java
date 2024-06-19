import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Menu{

    JFrame frame;
    ImageIcon start_icon = new ImageIcon(".//src/Buttons//Start.png");
    ImageIcon start_pressed = new ImageIcon(".//src/Buttons//StartPressed.png");

    JButton start = new JButton(start_icon);

    boolean game_start = false;

    public Menu(JFrame frame){
        this.frame = frame;
        start.setVisible(false);
        start.setBorderPainted(false);
        start.setFocusPainted(false);
        start.setContentAreaFilled(false);
        this.frame.getContentPane().add(start);

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                //start.setVisible(false);
                game_start = true;
                System.out.println("DZIAŁA");

                Tile t = new Tile("R");

                ImageIcon test = new TileAssets().getIcon(t, 0);

                start.setIcon(test);


            }
        });
        start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                start.setIcon(start_pressed);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                start.setIcon(start_icon);
            }
        });
        start.setBounds(385, 380, 130, 60);

        start.setVisible(true);
    }

    void hideMenu(){
        start.setVisible(false);
    }
}