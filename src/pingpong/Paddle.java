package pingpong;

import java.awt.Color;
import java.awt.Graphics;
import static pingpong.panels.CardsPanel.FRAME_HEIGHT;

/**
 *
 * @author Radek Bartyzal
 */
public class Paddle implements Drawable {

    public static final int PADDLE_WIDTH = 20;

    private int length;
    private final int x;
    private int y;
    private int duration; ///< Power up effect duration
    private static int V = FRAME_HEIGHT / 25; ///< Speed of movement
    private int center;

    public Paddle(int x) {
        this.x = x;
        y = 10;
        length = FRAME_HEIGHT / 5;
    }

    public void moveUp() {
        if (y - V > 0) {
            y -= V;
        } else {
            y = 0;
        }
    }

    public void moveDown() {
        if ((y + length + V) < FRAME_HEIGHT) {
            y += V;
        } else {
            y = FRAME_HEIGHT - length;
        }
    }

    public void AImove(Ball ball, int height) {
        center = (y + (length / 2));

        if (center < ball.getY()) {
            moveDown();
        } else {
            moveUp();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, PADDLE_WIDTH, length);
    }

    public void lengthReturn() {
        if (length != FRAME_HEIGHT / 5) {
            duration++;
            if (duration == 1000) {
                length = FRAME_HEIGHT / 5;
                duration = 0;
            }
        }
    }

    public void catchPowerUp(int type) {
        switch (type) {
            case 1:
                length += FRAME_HEIGHT / 10;
                break;
            case 2:
                if (length > FRAME_HEIGHT / 10) {
                    length -= FRAME_HEIGHT / 10;
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

    public void resize() {
        length = FRAME_HEIGHT / 5;
        V = FRAME_HEIGHT / 25;
        duration = 0;

        if (y + length > FRAME_HEIGHT) {
            y = FRAME_HEIGHT - length;
        }

    }

}
