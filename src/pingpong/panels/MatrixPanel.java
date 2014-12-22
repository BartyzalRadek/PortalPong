package pingpong.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import pingpong.AbleToGetOptions;
import pingpong.PowerUp;

/**
 *
 * @author Radek Bartyzal
 */
public class MatrixPanel extends JPanel implements AbleToGetOptions {

    private List<PowerUp> powerUpList = new ArrayList<PowerUp>();
    private boolean matrixOn = true;
    private int type = 0;
    private final String chinese = "あたアカサザジズゼゾシスセソキクケコイウエオジャな";
    private final String latin = "QWERTYUIOPASDFGHJKLZXCVBNM";
    private final String numbers = "0123456789";
    private final String signs = "~!@#$%^&*()_+/*-.°=´)§¨,[];',/\"";

    //Main timer for moving the signs
    private Timer timer1 = new Timer(30, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < powerUpList.size(); i++) {
                if (powerUpList.get(i).isExpired()) {
                    powerUpList.remove(i);
                }
            }

            for (PowerUp p : powerUpList) {
                p.move();
                p.expire();
            }
            repaint();
        }
    });

    //Adding new signs
    private Timer timer2 = new Timer(100, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 10; i++) {
                powerUpList.add(new PowerUp());
            }
        }
    });

    public MatrixPanel() {
        timer1.start();
        timer2.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setDoubleBuffered(true);
        this.setBackground(Color.BLACK);

        if (matrixOn) {
            for (PowerUp p : powerUpList) {
                switch (type) {
                    case 0:
                        p.drawMatrix(g, chinese);
                        break;
                    case 1:
                        p.drawMatrix(g, latin);
                        break;
                    case 2:
                        p.drawMatrix(g, numbers);
                        break;
                    case 3:
                        p.drawMatrix(g, signs);
                        break;
                }
            }
        }
    }

    private void startMatrix() {
        if (!timer1.isRunning()) {
            timer1.start();
            timer2.start();
        }
    }

    private void stopMatrix() {
        if (timer1.isRunning()) {
            timer1.stop();
            timer2.stop();
        }
    }

    @Override
    public void getOptions() {
        for (Component p : getParent().getComponents()) {
            if (p instanceof OptionsPanel) {
                matrixOn = ((OptionsPanel) p).isMatrixOn();
                type = ((OptionsPanel) p).getType();
            }
        }

        if (matrixOn) {
            startMatrix();
        } else {
            stopMatrix();
        }
    }
}
