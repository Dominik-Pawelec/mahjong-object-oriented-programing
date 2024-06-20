import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;



public class Results {
    JFrame frame;
    JPanel panel;
    ArrayList<JLabel> players = new ArrayList<>();


    Results(JFrame frame, Game game, boolean light_mode){
        this.frame = frame;
        for(int i = 0; i < 4; i ++){
            int temp = game.scores[i];
            if(i == 0)players.add(new JLabel("Player:  " + Integer.toString(temp) + " wins!"));
            else players.add(new JLabel("Bot nr. "+ Integer.toString(i) + ": " + Integer.toString(temp) + " wins"));
            frame.getContentPane().add(players.get(i));
            players.get(i).setFont(new Font("arial", 0, 30));
            players.get(i).setBounds(350,350 + 50 * i, 300, 50);
            if(light_mode) players.get(i).setForeground(new Color(27, 20, 38));
            else players.get(i).setForeground(new Color(246, 210, 235));
            players.get(i).setVisible(true);
            System.out.print("ugabuga");
        }
    }
}
