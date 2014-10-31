package pingpong;

import java.awt.Color;
import java.awt.Graphics;
import static pingpong.GameFrame.FRAME_HEIGHT;

/**
 *
 * @author Radek Bartyzal
 */
public class Paddle implements Drawable {

    private int length;
    private int x;
    private int y;
    private int duration; ///< Power up effect duration
    private static final int V = 20; ///< Speed of movement
    private static final int WIDTH = 20;
    private int center;

    public Paddle(int x) {
        this.x = x;
        y = 10;
        length = 100;
    }

    public void moveUp() {
        if (y > 0) {
            y -= V;
        }
    }

    public void moveDown() {
        if ((y + length) < FRAME_HEIGHT) {
            y += V;
        }
    }

    public void AImove(Ball ball, int height) {
        center = (y + (length / 2));

        if (center < ball.y) {
            if ((y + length) < height) {
                y += V;
            }
        } else {
            if (y > 0) {
                y -= V;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, WIDTH, length);
    }

    public void lengthReturn() {
        if (length != 100) {
            duration++;
            if (duration == 1000) {
                length = 100;
                duration = 0;
            }
        }
    }
    
    public void catchPowerUp(int type){
        switch (type) {
                case 1:
                    length += 50;
                    break;
                case 2:
                    if (length > 50) {
                        length -= 50;
                    }
                    break;
            }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLength() {
        return length;
    }
    
    
    
    
}
