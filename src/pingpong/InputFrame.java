/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static pingpong.EndlessPanel.NAME;

/**
 *
 * @author darmy
 */
public class InputFrame extends JFrame {

    JLabel label;
    JPanel panel;
    StringBuilder sb;

    public InputFrame() {
        init();
    }

    private void init() {
        label = new JLabel("Enter your name:");
        panel = new JPanel();
        label.setForeground(Color.gray);
        label.setFont(new Font("asf", Font.PLAIN, 20));
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setPreferredSize(new Dimension(100, 30));
        sb = new StringBuilder();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setName("Input name");
        this.setBackground(Color.black);
        panel.setBackground(Color.black);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(30, 50)));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(30, 50)));
        panel.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {

                if (sb.length() <= 8) {
                    if (String.valueOf(e.getKeyChar()).matches("[a-zA-Z0-9]")) {
                        sb.append(e.getKeyChar());
                    }
                }
                label.setText(sb.toString());

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    label.setText("BAF");
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
        this.add(panel);
        this.setPreferredSize(new Dimension(300, 150));
        this.pack();
    }

    private void quit() {
        NAME = sb.toString();
        this.setVisible(false);
        this.dispose();
    }
    
    public String getName(){
        String name;
        name = sb.toString();
        return name;
    }

}
