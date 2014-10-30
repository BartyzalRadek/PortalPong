
package pingpong;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Radek Bartyzal
 */
public class Ball implements Drawable {

    public int x;
    public int y;
    public int r;
    public double vx;
    public double vy;
    private int gpHeight;
    private int changeVy = 0;
    private int changeVx = 0;

    public Ball() {
        x = 450;
        y = 250;
        r = 5;
        gpHeight = 500;
        vx = (int) Math.floor(Math.random() * 2 + 3);
        vy = (int) Math.floor(Math.random() * 2 + 3);

    }

    public void scorePoint(Player player1, Player player2, boolean isClassic) {
        if (isClassic) {
            if (x > 970) {
                player1.score += 1;
                toCenter();
            }
        }
        if (x < 0) {
            player2.score += 1;
            toCenter();
        }
    }

    public void teleport(boolean isTeleport, Teleport teleport) {
        if (isTeleport == true) {
            if (vx > 0) {
                if (x > teleport.getX1()) {
                    x = teleport.getX2();
                    y = teleport.getY2() + 10;
                    changeDirection();
                }
            }
            if (vx < 0) {
                if (x < teleport.getX1()) {
                    x = teleport.getX2();
                    y = teleport.getY2() + 10;
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

    public void bounceOffWalls(boolean isClassic) {
        if (isClassic) {
            if (y > (gpHeight - r - 50) || y < 0) {
                vy = -vy;
                changeVy++;
            }
        } else {
            if (y > (gpHeight - r - 50) || y < 0) {
                vy = -vy;
                changeVy++;
            }
            if (x > 970) {
                vx = -vx;
                changeVx++;
            }
        }
    }

    public void bounceOffPaddle(Paddle paddle, Player player) {
        if (vx < 0) {
            if (x < (paddle.x + 20) && x > paddle.x && y < (paddle.y + paddle.length) && y > paddle.y) {
                changeAngle(paddle);
                player.ballReturned += 1;
            }
        } else {
            if (x > (paddle.x - 10) && x < (paddle.x + 10) && y < (paddle.y + paddle.length) && y > paddle.y) {
                changeAngle(paddle);
                player.ballReturned += 1;
            }
        }
    }

    private void changeAngle(Paddle paddle) {
        double angle = 0;
        double v = Math.sqrt(vx * vx + vy * vy);    //vysledna rychlost, prepona trojuhelniku
        double tempVx = vx;
        double tempVy = vy;
        int centerOfPaddleY = (int) (paddle.y + (paddle.length / 2));
        int difference = (y + 5) - (paddle.y + (paddle.length / 2));
        int sizeOfBlock = 5;
        int numberOfBlocks = (int) (paddle.length / sizeOfBlock);

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
            changeVx++;
        }
        if (tempVx < 0 && vx < 0) {
            vx = vx * (-1);
            changeVx++;
        }

    }

    private void repairVy(double tempVy, Paddle paddle) {
        int centerOfPaddleY = (int) (paddle.y + (paddle.length / 2));

        if (y < centerOfPaddleY) {
            if (tempVy > 0 && vy > 0) {
                vy = -vy;
                changeVy++;
            }

            if (tempVy < 0 && vy > 0) {
                vy = -vy;
                changeVy++;
            }
        } else {
            if (tempVy > 0 && vy < 0) {
                vy = -vy;
                changeVy++;
            }

            if (tempVy < 0 && vy < 0) {
                vy = -vy;
                changeVy++;
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

    public void toCenter() {
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

    public void changeDirection() {
        vx = -vx;
        changeVx++;
        vy = -vy;
        changeVy++;
    }

    public int getChangeVy() {
        return changeVy;
    }

    public int getChangeVx() {
        return changeVx;
    }

    public void setChangeVx(int changeVx) {
        this.changeVx = changeVx;
    }

    public void setChangeVy(int changeVy) {
        this.changeVy = changeVy;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 10, 10);
    }
}
