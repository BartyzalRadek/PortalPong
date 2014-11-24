/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pingpong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author darmy
 */
public class InputFrame extends JFrame implements KeyListener {
    
    JLabel label;
    StringBuilder sb;
    
    public InputFrame(){
        init();
    }
    
    private void init(){
        label = new JLabel("Enter your name:");
        sb = new StringBuilder();
        this.setBackground(Color.black);
        this.getContentPane().add(label, BorderLayout.CENTER);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        sb.append(e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
