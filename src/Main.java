import java.util.List;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Main {
    static JFrame frame = new JFrame("Mahjong");
    static JPanel panel = new JPanel();
    static ImageIcon background = new ImageIcon("Background.png");
    static JLabel label = new JLabel(background);
    public static void main(String[] args)
    {
        frame.setResizable(false);
        panel.setPreferredSize(new Dimension(900, 900));
        panel.setBackground(new Color(246, 210, 235));
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);

        Menu menu = new Menu(frame);



/* 
        Game game = new Game(new Human());
        game.startHanchan();
        game.prepareRound();
        game.start();*/
    }
}
