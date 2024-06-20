import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import java.util.ArrayList;
import java.util.Arrays;


public class Menu{

    JFrame frame;
    JPanel panel;

    ImageIcon start_icon = new ImageIcon(".//src/Buttons//Start.png");
    ImageIcon start_pressed = new ImageIcon(".//src/Buttons//StartPressed.png");
    ImageIcon options_icon = new ImageIcon(".//src/Buttons//Options.png");
    ImageIcon options_pressed = new ImageIcon(".//src/Buttons//OptionsPressed.png");
    ImageIcon mute_icon = new ImageIcon(".//src/Buttons//Mute.png");
    ImageIcon mute_pressed = new ImageIcon(".//src/Buttons//MutePressed.png");
    ImageIcon unmute_icon = new ImageIcon(".//src/Buttons//Unmute.png");
    ImageIcon unmute_pressed = new ImageIcon(".//src/Buttons//UnmutePressed.png");
    ImageIcon light_icon = new ImageIcon(".//src/Buttons//Light.png");
    ImageIcon light_pressed = new ImageIcon(".//src/Buttons//LightPressed.png");
    ImageIcon dark_icon = new ImageIcon(".//src/Buttons//Dark.png");
    ImageIcon dark_pressed = new ImageIcon(".//src/Buttons//DarkPressed.png");
    ImageIcon back_icon = new ImageIcon(".//src/Buttons//Back.png");
    ImageIcon back_pressed = new ImageIcon(".//src/Buttons//BackPressed.png");


    File click_clip = new File(".//src/Audio//click.wav");
    AudioInputStream click_stream;
    Clip click = null;

    boolean game_start = false;
    boolean audio_on = true;
    boolean light_mode = true;
    
    JButton start = new JButton(start_icon);
    JButton options = new JButton(options_icon);
    JButton mute = new JButton(mute_icon);
    JButton unmute = new JButton(unmute_icon);
    JButton light = new JButton(light_icon);
    JButton dark = new JButton(dark_icon);
    JButton back = new JButton(back_icon);

    ArrayList<JButton> buttons = new ArrayList<>(Arrays.asList(start, options, mute, unmute, light, dark, back));


    public Menu(JFrame frame){
        try{
            click_stream = AudioSystem.getAudioInputStream(click_clip);
            click = AudioSystem.getClip();
            click.open(click_stream);   
        }catch (Exception e){}

            
        this.frame = frame;

        for(JButton button : buttons){
            button.setVisible(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            this.frame.getContentPane().add(button);
        }
        
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(audio_on){
                    click.setMicrosecondPosition(0);
                    click.start();}
                game_start = true;
                System.out.println("START GAME");
                for(JButton button : buttons){
                    button.setVisible(false);
                }
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

        options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(audio_on){
                    click.setMicrosecondPosition(0);
                    click.start();}
                start.setVisible(false);
                options.setVisible(false);
                back.setVisible(true);
                if(!audio_on)unmute.setVisible(true);
                if(audio_on)mute.setVisible(true);
                if(!light_mode)light.setVisible(true);
                if(light_mode)dark.setVisible(true);
            }
        });
        options.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                options.setIcon(options_pressed);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                options.setIcon(options_icon);
            }
        });
        options.setBounds(385, 480, 130, 60);
        options.setVisible(true);

        mute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(audio_on){
                    click.setMicrosecondPosition(0);
                    click.start();}
                audio_on = false;
                mute.setVisible(false);
                unmute.setVisible(true);
            }
        });
        mute.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mute.setIcon(mute_pressed);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mute.setIcon(mute_icon);
            }
        });
        mute.setBounds(385, 380, 130, 60);
        mute.setVisible(false);

        unmute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(audio_on){
                    click.setMicrosecondPosition(0);
                    click.start();}
                audio_on = true;
                mute.setVisible(true);
                unmute.setVisible(false);
            }
        });
        unmute.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                unmute.setIcon(unmute_pressed);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                unmute.setIcon(unmute_icon);
            }
        });
        unmute.setBounds(385, 380, 130, 60);
        unmute.setVisible(false);

        light.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(audio_on){
                    click.setMicrosecondPosition(0);
                    click.start();}
                light_mode = true;
                light.setVisible(false);
                dark.setVisible(true);
                frame.getContentPane().setBackground(new Color(246, 210, 235));
            }
        });
        light.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                light.setIcon(light_pressed);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                light.setIcon(light_icon);
            }
        });
        light.setBounds(385, 480, 130, 60);
        light.setVisible(false);

        dark.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(audio_on){
                    click.setMicrosecondPosition(0);
                    click.start();}
                light_mode = false;
                light.setVisible(true);
                dark.setVisible(false);
                frame.getContentPane().setBackground(new Color(27, 20, 38));
            }
        });
        dark.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dark.setIcon(dark_pressed);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dark.setIcon(dark_icon);
            }
        });
        dark.setBounds(385, 480, 130, 60);
        dark.setVisible(false);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(audio_on){
                    click.setMicrosecondPosition(0);
                    click.start();}

                back.setVisible(false);
                unmute.setVisible(false);
                mute.setVisible(false);
                start.setVisible(true);
                options.setVisible(true);
            }
        });
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                back.setIcon(back_pressed);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                back.setIcon(back_icon);
            }
        });
        back.setBounds(385, 580, 130, 60);
        back.setVisible(false);
    }

    boolean getAudio(){
        return audio_on;
    }
    boolean getLight(){
        return light_mode;
    }
    void hideMenu(){
        for(JButton button : buttons){
            button.setVisible(false);
        }
    }
}