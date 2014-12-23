package pingpong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import static pingpong.panels.CardsPanel.FONT_SIZE;
import static pingpong.panels.CardsPanel.FRAME_HEIGHT;
import static pingpong.panels.CardsPanel.FRAME_WIDTH;

/**
 *
 * @author Radek Bartyzal
 */
public class PowerUp implements Drawable {

    private int x;
    private int y;
    private double vx;
    private double vy;
    private int type; //0 = T, 1 = +, 2 = -
    private int expire = 0;
    private boolean isExpired = false;
    private double rnd; // For matrix effect

    public PowerUp(int type) {
        this.type = type;
        vx = (int) (Math.round(Math.random() * 6) + 2);
        vy = (int) (Math.round(Math.random() * 7) + 1);
        x = 450;
        y = 250;
    }

    /*MATRIX BLOCK START*/
    public PowerUp() { 
        x = (int) (Math.round(Math.random() * FRAME_WIDTH));
        y = (int) (Math.round(Math.random() * FRAME_HEIGHT) - 50);
        vx = 0;
        vy = (int) (Math.round(Math.random() * 3) + 2);
        rnd = Math.random();
    }

    public void drawMatrix(Graphics g, String s) {
        g.setFont(new Font("Tahoma", Font.PLAIN, FONT_SIZE - FONT_SIZE/4));
        g.setColor(Color.green);
        g.drawString(Character.toString(s.charAt((int)(rnd * (s.length() - 1)))), x, y);
        g.setColor(Color.WHITE);
    }

    public void expire() {
        expire++;
        if (expire == 20 || y > FRAME_HEIGHT) {
            isExpired = true;
        }
    }
    /*MATRIX BLOCK END*/

    public void toCenter() {
        x = 450;
        y = 250;
        vx = (int) (Math.round(Math.random() * 6) + 2);
        vx = vx * randomSwitchDirection();
        vy = (int) (Math.round(Math.random() * 7) + 1);
        vy = vy * randomSwitchDirection();
    }

    public void move() {
        x += vx;
        y += vy;
    }

    public void bounceOffWalls() {
        if (y > (445) || y < 0) {
            vy = -vy;
        }
        if (x > (970) || x < 0) {
            vx = -vx;
        }
    }

    public void score(Paddle paddle, Player player) {
        if (collision(paddle)) {
            paddle.catchPowerUp(type);
            player.catchPowerUp(type);

            switch (type) {
                case 0:
                    toCenter();
                    break;
                default: 
                    isExpired = true;
                    break;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.PLAIN, 15));

        switch (type) {
            case 0:
                g.drawString("T", x, y);
                break;
            case 1:
                g.drawString("+", x, y);
                break;
            case 2:
                g.drawString("-", x, y);
                break;
        }

        g.setFont(new Font(g.getFont().getFontName(), g.getFont().getStyle(), g.getFont().getSize() - 2));
    }

    public boolean collision(Paddle paddle) {
        return (x < (paddle.getX() + 20) && x > paddle.getX() && y < (paddle.getY() + paddle.getLength()) && y > paddle.getY());
    }

    private int randomSwitchDirection() {
        int i;
        i = (int) Math.floor(Math.random() * 10 + 1);
        if (i > 5) {
            return -1;
        } else {
            return 1;
        }
    }
    
    public boolean isExpired(){
        return isExpired;
    }
}
