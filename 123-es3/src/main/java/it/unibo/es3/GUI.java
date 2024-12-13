package it.unibo.es3;

import javax.swing.*;

import java.awt.*;
import java.util.*;

public class GUI extends JFrame {
    
    private final Map<JButton, Point> buttons = new HashMap<>();
    private final JButton computeButton = new JButton(">");
    private final Logics logics;
    
    public GUI(int width) {
        this.logics = new LogicsImpl(width);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70*width, 70*width);
        
        JPanel panel = new JPanel(new GridLayout(width,width));
        this.getContentPane().add(BorderLayout.CENTER, panel);
        this.getContentPane().add(BorderLayout.SOUTH, computeButton);
        
        computeButton.addActionListener(e -> {
            logics.hitKey();
            this.update();
            if(logics.toQuit()){
                System.exit(0);
            }
        });
                
        for (int i=0; i<width; i++){
            for (int j=0; j<width; j++){
                final JButton jb = new JButton(" ");
                this.buttons.put(jb, new Point(i,j));
                panel.add(jb);
            }
        }
        this.update();
        this.setVisible(true);
    }
    
    void update(){
        this.buttons.forEach((btn, pos) -> btn.setText(this.logics.getActivated().contains(pos)?"*":" "));
    }
    
}