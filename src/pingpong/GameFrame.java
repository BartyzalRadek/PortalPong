package pingpong;

import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 *
 * @author Radek Bartyzal
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
        if ((mpgp.isClosePanel()) || (spgp.isClosePanel()) || (endlessgp.isClosePanel())) {
            visible = false;
            mpgp.reset();
            spgp.reset();
            endlessgp.reset();
        }
    }
}
