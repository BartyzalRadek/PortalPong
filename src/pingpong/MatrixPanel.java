package pingpong;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Radek Bartyzal
 */
public class MatrixPanel extends JPanel {

    private List<PowerUp> powerUpList = new ArrayList<PowerUp>();
    private boolean matrixOn = true;
    private int type = 2;
    private final String chinese = "あたアカサザジズゼゾシスセソキクケコイウエオジャな";
    private final String latin = "QWERTYUIOPASDFGHJKLZXCVBNM";
    private final String numbers = "01234567890123456789012345"; //to have the same length - filling up an array and painting it later => trying to draw a string of powerup init with different length of string because the settings changed
    private final String signs = "~!@#$%^&*()_+/*-.°=´)§¨,[];',/\"";

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
    //pridavani novych znaku
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

    public void getOptions() {
        for (Component p : getParent().getComponents()) {
            if (p instanceof OptionsPanel) {
                matrixOn = ((OptionsPanel) p).isMatrixOn();
                type = ((OptionsPanel) p).getType();
            }
        }

        if (matrixOn) startMatrix();
        else stopMatrix();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
