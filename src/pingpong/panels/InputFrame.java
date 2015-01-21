/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong.panels;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static pingpong.panels.EndlessPanel.NAME;

/**
 *
 * @author Radek Bartyzal
 */
public class InputFrame extends JDialog {

    private static final int MAX_LENGTH = 7;
    
    private JLabel label;
    private JPanel panel;
    private StringBuilder sb;
    private final EndlessPanel ePanel;

    public InputFrame(EndlessPanel panel) {
        ePanel = panel;
        init();
    }

    private void init() {
        label = new JLabel("Enter your name:");
        panel = new JPanel();
        sb = new StringBuilder();
        
        initLabel();
        initPanel();
        
        this.setBackground(Color.black);
        this.add(panel);
        this.setPreferredSize(new Dimension(300, 150));
        this.pack();
    }
    
    private void initLabel(){
        label.setForeground(Color.gray);
        label.setFont(new Font("asf", Font.PLAIN, 20));
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setPreferredSize(new Dimension(100, 30));
    }
    
    private void initPanel(){
        panel.setBackground(Color.black);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(30, 50)));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(30, 50)));
        panel.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (sb.length() <= MAX_LENGTH) {
                    if (String.valueOf(e.getKeyChar()).matches("[a-zA-Z0-9]")) {
                        sb.append(e.getKeyChar());
                    }
                }
                label.setText(sb.toString());

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    quit();
                }
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                        label.setText(sb.toString());
                    }

                }

            }
        });
        panel.setFocusable(true);
    }

    private void quit() {
        NAME = sb.toString();
        ePanel.nameNotSet = false;
        ePanel.drawNAME();
        ePanel.submitNewScore();
        this.setVisible(false);
        ePanel.drawFinalScore();
        this.dispose();
    }
}
