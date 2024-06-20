import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Calls {
    JFrame frame;
    ImageIcon skipIcon = new ImageIcon(".//src/Buttons//skip.png");
    ImageIcon ponIcon = new ImageIcon(".//src/Buttons//pon.png");
    ImageIcon chiIcon = new ImageIcon(".//src/Buttons//chi.png");
    ImageIcon ronIcon = new ImageIcon(".//src/Buttons//ron.png");
    ImageIcon tsumoIcon = new ImageIcon(".//src/Buttons//tsumo.png");
    ImageIcon riichiIcon = new ImageIcon(".//src/Buttons//riichi.png");
    ImageIcon skipPressed = new ImageIcon(".//src/Buttons//skipPressed.png");
    ImageIcon ponPressed = new ImageIcon(".//src/Buttons//ponPressed.png");
    ImageIcon chiPressed = new ImageIcon(".//src/Buttons//chiPressed.png");
    ImageIcon ronPressed = new ImageIcon(".//src/Buttons//ronPressed.png");
    ImageIcon tsumoPressed = new ImageIcon(".//src/Buttons//tsumoPressed.png");
    ImageIcon riichiPressed = new ImageIcon(".//src/Buttons//riichiPressed.png");
    
    JButton skip = new JButton(skipIcon);
    JButton pon = new JButton(ponIcon);
    JButton chi = new JButton(chiIcon);
    JButton ron = new JButton(ronIcon);
    JButton tsumo = new JButton(tsumoIcon);
    JButton riichi = new JButton(riichiIcon);
    ArrayList<JButton> buttons = new ArrayList<JButton>();
    int x, y;
    String choice = "skip";
    boolean choice_got = false;
    boolean audio_on;

    File app = new File(".//src/Audio//appear.wav");
    File cli = new File(".//src/Audio//click.wav");
    AudioInputStream app_stream, click_stream;
    Clip appear = null;
    Clip click = null;

    public Calls(JFrame frame, int x, int y, boolean audio_on) {
        this.frame = frame;
        this.x = x;
        this.y = y;
        buttons.add(skip);
        buttons.add(ron);
        buttons.add(pon);
        buttons.add(chi);
        buttons.add(tsumo);
        buttons.add(riichi);
        this.audio_on = audio_on;
        try {
            app_stream = AudioSystem.getAudioInputStream(app);
            click_stream = AudioSystem.getAudioInputStream(cli);
            appear = AudioSystem.getClip();
            click = AudioSystem.getClip();
            appear.open(app_stream);
            click.open(click_stream);            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        appear.start();
        for (JButton button : buttons) {
            button.setVisible(false);
            frame.getContentPane().add(button);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
        }
        skip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                skip.setIcon(skipPressed);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                skip.setIcon(skipIcon);
            }
        });
        ron.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ron.setIcon(ronPressed);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ron.setIcon(ronIcon);
            }
        });
        pon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pon.setIcon(ponPressed);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pon.setIcon(ponIcon);
            }
        });
        chi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chi.setIcon(chiPressed);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chi.setIcon(chiIcon);
            }
        });
        tsumo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tsumo.setIcon(tsumoPressed);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tsumo.setIcon(tsumoIcon);
            }
        });
        riichi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                riichi.setIcon(riichiPressed);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                riichi.setIcon(riichiIcon);
            }
        });
    }

    public void showCalls(boolean[] available){
        int tempX = x;
        buttons.get(0).setVisible(true);
        buttons.get(0).setBounds(tempX, y, 130, 60);

        tempX -= 140;
        if(audio_on) {
            appear.setMicrosecondPosition(0);
            appear.start();
        }
        for(int i = 0; i < available.length; i++){
            if(available[i]) {
                buttons.get(i).setBounds(tempX, y, 130, 60);
                buttons.get(i).setVisible(true);
                tempX -= 140;
            }
        }
    }

    public void hideCalls(){
        for (JButton button : buttons) {
            button.setVisible(false);
        }
    }

    public String getCall(boolean[] available){
        choice_got = false;
        showCalls(available);
        while(!choice_got){
            for(JButton button : buttons){
                button.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int index = buttons.indexOf(button);
                        switch (index) {
                            case 0:
                                choice = "skip";
                                break;
                            case 1:
                                choice = "ron";
                                break;
                            case 2:
                                choice = "pon";
                                break;
                            case 3:
                                choice = "chi";
                                break;
                            case 4:
                                choice = "tsumo";
                                break;
                            case 5:
                                choice = "riichi";
                                break;
                            
                        
                            default:
                                break;
                        }
                        choice_got = true;
                        hideCalls();
                    }
                });
            }
        }
        try {
            if(audio_on) {
                click.setMicrosecondPosition(0);
                click.start();
            }
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return choice;
    }


}
