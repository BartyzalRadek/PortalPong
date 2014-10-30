package pingpong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Radek Bartyzal
 */
public class PowerUp implements Drawable {

    public int x;
    public int y;
    public double vx;
    public double vy;
    public int v;
    public int type; //0 = T, 1 = +, 2 = -
    public boolean isDeleted;
    private int expire = 0;
    public int random;

    public PowerUp(int type) {
        this.type = type;
        vx = (int) (Math.round(Math.random() * 6) + 2);
        vy = (int) (Math.round(Math.random() * 7) + 1);
        x = 450;
        y = 250;
        isDeleted = false;
        v = 1;

    }
    
    /*MATRIX BLOCK START*/
    public PowerUp(int random, boolean tmp) { //tmp just for allowing to have 2 constructors
        x = (int) (Math.round(Math.random() * 1000));
        y = (int) (Math.round(Math.random() * 500) - 50);
        vx = 0;
        vy = (int) (Math.round(Math.random() * 3) + 2);
        isDeleted = false;
        this.random = random;
    }
    

    public void drawMatrix(Graphics g, String s) {
        g.setColor(Color.green);
        g.drawString(s.substring(random, random + 1), x, y);
        g.setColor(Color.WHITE);
    }
    

    public void expire() {
        expire++;
        if (expire == 20) {
            isDeleted = true;
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

            switch (type) {
                case 0:
                    if (paddle.player == 1) {
                        player.teleports += 1;
                        toCenter();
                        player.tCount++;
                    } else {
                        player.teleports += 1;
                        toCenter();
                        player.tCount++;
                    }
                    break;
                case 1:
                    paddle.length += 50;
                    player.plusCount++;
                    isDeleted = true;
                    break;
                case 2:
                    if (paddle.length > 50) {
                        paddle.length -= 50;
                    }
                    player.minusCount++;
                    isDeleted = true;
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
        return (x < (paddle.x + 20) && x > paddle.x && y < (paddle.y + paddle.length) && y > paddle.y);
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
}
