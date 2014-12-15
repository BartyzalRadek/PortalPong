package pingpong;

import java.awt.Color;
import java.awt.Graphics;
import static pingpong.MainForm.FRAME_HEIGHT;
import static pingpong.MainForm.FRAME_WIDTH;
import static pingpong.Paddle.PADDLE_WIDTH;

/**
 *
 * @author Radek Bartyzal
 */
public class Ball implements Drawable {

    private int x;
    private int y;
    private final int r = 5;
    private double vx;
    private double vy;

    public Ball() {
        x = 450;
        y = 250;
        vx = (int) Math.floor(Math.random() * 2 + 3);
        vy = (int) Math.floor(Math.random() * 2 + 3);

    }

    public void increaseSpeed() {
        if (vx > 0) {
            vx++;
        } else {
            vx--;
        }
        if (vy > 0) {
            vy++;
        } else {
            vy--;
        }
    }

    public void scorePoint(Player player1, Player player2, boolean isEndless) {
        if (!isEndless) {
            if (x > 970) {
                player1.score();
                toCenter();
            }
        }

        if (x < 0) {
            player2.score();
            toCenter();
        }
    }

    public void teleport(boolean isTeleport, Teleport teleport) {
        if (isTeleport == true) {
            if (vx > 0) {
                if (x > teleport.getX1()) {
                    x = teleport.getX2();
                    y = teleport.getY2() + 2 * r;
                    changeDirection();
                }
            }
            if (vx < 0) {
                if (x < teleport.getX1()) {
                    x = teleport.getX2();
                    y = teleport.getY2() + 2 * r;
                    changeDirection();
                }
            }
        }
    }

    public void move() {
        x += vx;
        y += vy;
        if (vx == 0) {
            toCenter();
        }

    }

    public void bounceOffWalls(boolean isEndless) {
        if (isEndless) {
            if (x > (FRAME_WIDTH - 30)) {
                vx = -vx;
                x = (FRAME_WIDTH - 30);
            }
        }

        if (y > (FRAME_HEIGHT - r - 50)) {
            vy = -vy;
            y = (FRAME_HEIGHT - r - 50);
        }
        if (y < 0) {
            vy = -vy;
            y = 0;
        }
    }

    public void bounceOffPaddle(Paddle paddle, Player player) {
        if (vx < 0) {
            if (x < (paddle.getX() + PADDLE_WIDTH) && x > paddle.getX()
                    && y < (paddle.getY() + paddle.getLength()) && y > paddle.getY()) {

                changeAngle(paddle);
                player.ballReturned();
            }
        } else {
            if (x > (paddle.getX() + PADDLE_WIDTH - 2 * r) && x < (paddle.getX() + PADDLE_WIDTH - 2 * r)
                    && y < (paddle.getY() + paddle.getLength()) && y > paddle.getY()) {

                changeAngle(paddle);
                player.ballReturned();
            }
        }
    }

    private void changeAngle(Paddle paddle) {
        double angle = 0;
        double v = Math.sqrt(vx * vx + vy * vy);    //vysledna rychlost, prepona trojuhelniku
        double tempVx = vx;
        double tempVy = vy;
        int centerOfPaddleY = (int) (paddle.getY() + (paddle.getLength() / 2));
        int difference = (y + 5) - (paddle.getY() + (paddle.getLength() / 2));
        int sizeOfBlock = 5;
        int numberOfBlocks = (int) (paddle.getLength() / sizeOfBlock);

        /*Rozdelim 90 stupnu na pocet casti korespondujici s poctem bloku, na ktere jsem rozdelil
         paddle.
         Vysledny uhel je pocet bloku ve vzdalenosti micku od stredu palky krat cast 90 stupnu 
         korespundujici s jednim blokem.
         Cim vetsi rozdil, tim vic bloku, tim vetsi uhel.*/
        if (y > centerOfPaddleY) {
            angle += (difference / sizeOfBlock) * (90 / (numberOfBlocks / 2));
            vy = Math.sin(angle) * v;
            repairVy(tempVy, paddle);
        } else {
            angle -= (difference / sizeOfBlock) * (90 / (numberOfBlocks / 2));
            vy = Math.sin(angle) * v;
            repairVy(tempVy, paddle);
        }

        //vy = Math.sin(angle) * v;
        vx = Math.cos(angle) * v;
        if (tempVx > 0 && vx > 0) {
            vx = vx * (-1);
        }
        if (tempVx < 0 && vx < 0) {
            vx = vx * (-1);
        }
        if (vy == 0) {
            vy += 0.3;
        }

    }

    private void repairVy(double tempVy, Paddle paddle) {
        int centerOfPaddleY = (int) (paddle.getY() + (paddle.getLength() / 2));

        if (y < centerOfPaddleY) {
            if (vy > 0) {
                vy = -vy;
            }
        } else {
            if (vy < 0) {
                vy = -vy;
            }

        }
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

    private void toCenter() {
        reset();
        vx = vx * randomSwitchDirection();
        vy = vy * randomSwitchDirection();

    }

    public void reset() {
        x = 450;
        y = 250;
        vx = (int) Math.floor(Math.random() * 2 + 3);
        vy = (int) Math.floor(Math.random() * 2 + 3);
    }

    private void changeDirection() {
        vx = -vx;
        vy = -vy;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 10, 10);
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
