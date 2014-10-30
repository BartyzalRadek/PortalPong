package pingpong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Radek Bartyzal
 */
public class MatrixPanel extends JPanel {

    private List<PowerUp> powerUpList = new ArrayList<PowerUp>();
    private boolean matrixOn = true;
    private int type = 0;
    private String chinese = "あたアカサザジズゼゾシスセソキクケコイウエオジャな";
    private String latin = "QWERTYUIOPASDFGHJKLZXCVBNM";
    private String numbers = "0123456789";
    private String signs = "~!@#$%^&*()_+/*-.°=´)§¨,[];',/\"";
    
    public Timer timer1 = new Timer(30, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < powerUpList.size(); i++) {
                if (powerUpList.get(i).isDeleted) {
                    powerUpList.remove(i);
                }
            }

            for (int i = 0; i < powerUpList.size(); i++) {
                powerUpList.get(i).move();
                powerUpList.get(i).expire();

            }
            repaint();
        }
    });
    //pridavani novych znaku
    public Timer timer2 = new Timer(100, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 10; i++) {
                switch (type) {
                    case 0:
                        powerUpList.add(new PowerUp((int) (Math.random() * chinese.length()-1), true));
                        break;
                    case 1:
                        powerUpList.add(new PowerUp((int) (Math.random() * latin.length()-1), true));
                        break;
                    case 2:
                        powerUpList.add(new PowerUp((int) (Math.random() * numbers.length()-1), true));
                        break;
                    case 3:
                        powerUpList.add(new PowerUp((int) (Math.random() * signs.length()-1), true));
                        break;
                }
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
    
    public boolean isMatrixOn() {
        return matrixOn;
    }

    public void setMatrixOn(boolean matrixOn) {
        this.matrixOn = matrixOn;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
