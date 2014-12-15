package pingpong.panels;

import java.awt.GridLayout;
import javax.swing.JFrame;
import static pingpong.MainForm.FRAME_HEIGHT;
import static pingpong.MainForm.FRAME_WIDTH;

/**
 *
 * @author Radek Bartyzal
 */
public class GameFrame extends JFrame {
    
    public GraphicsPanel gp;
    public boolean visible;

    public GameFrame(String s) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(100, 100, FRAME_WIDTH, FRAME_HEIGHT);
        this.setLayout(new GridLayout());
        this.visible = false;
        this.setTitle("Portal Pong");

        if (s.equals("sp")) {
            gp = new SPGraphicsPanel();

        }
        if (s.equals("mp")) {
            gp = new MPGraphicsPanel();

        }
        if (s.equals("endless")) {
            gp = new EndlessPanel();

        }

        this.add(gp);
        this.gp.powerUpTimer.start();

    }

}
