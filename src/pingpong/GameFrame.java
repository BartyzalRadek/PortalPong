/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Darmy
 */
public class GameFrame extends JFrame {

    public MPGraphicsPanel mpgp = new MPGraphicsPanel();
    public SPGraphicsPanel spgp = new SPGraphicsPanel();
    public EndlessPanel endlessgp = new EndlessPanel();
    public boolean visible;

    public GameFrame(String s) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(100, 100, 1000, 500);
        this.setLayout(new GridLayout());
        this.visible = false;
        this.setTitle("Portal Pong");

        if (s.equals("sp")) {
            this.add(spgp);
            this.spgp.powerUpTimer.start();
            this.addKeyListener(spgp);
        }
        if (s.equals("mp")) {
            this.add(mpgp);
            this.mpgp.powerUpTimer.start();
            this.addKeyListener(mpgp);
        }
        if (s.equals("endless")) {
            this.add(endlessgp);
            this.endlessgp.powerUpTimer.start();
            this.addKeyListener(endlessgp);
        }

    }

    public void checkVisible() {
        if ((mpgp.endGame == 2) || (spgp.endGame == 2) || (endlessgp.endGame == 3)) {
            visible = false;
            mpgp.reset();
            spgp.reset();
            endlessgp.reset();
        }
    }
}
