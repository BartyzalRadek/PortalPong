package pingpong;

import java.awt.Color;
import java.awt.Graphics;
import pingpong.panels.CardsPanel;
import static pingpong.panels.CardsPanel.FRAME_HEIGHT;

/**
 *
 * @author Radek Bartyzal
 */
public class Paddle implements Drawable {

    public static final int PADDLE_WIDTH = 20;

    private int length;
    private int x;
    private int y;
    private int duration; ///< Power up effect duration
    private int v; ///< Speed of movement
    private int center;
    private final boolean isLeftPaddle; ///< Whether is the paddle on the left side of the screen
    private int cnt = 0;

    public Paddle(boolean isLeft) {
        isLeftPaddle = isLeft;

        if (isLeftPaddle) {
            x = 10;
        } else {
            x = CardsPanel.FRAME_WIDTH - 50;
        }
        y = 10;
        v = FRAME_HEIGHT / 25;
        length = FRAME_HEIGHT / 5;
    }

    public void moveUp() {
        if (y - v > 0) {
            y -= v;
        } else {
            y = 0;
        }
    }

    public void moveDown() {
        if ((y + length + v) < FRAME_HEIGHT) {
            y += v;
        } else {
            y = FRAME_HEIGHT - length;
        }
    }

    public void AImove(Ball ball, int height) {
        cnt++;
        if (cnt == 6) {
            cnt = 0;

            center = (y + (length / 2));

            if (center < ball.getY()) {
                moveDown();
            } else {
                moveUp();
            }
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
        if (!isLeftPaddle) {
            x = CardsPanel.FRAME_WIDTH - 50;
        }
        length = FRAME_HEIGHT / 5;
        v = FRAME_HEIGHT / 25;
        duration = 0;

        if (y + length > FRAME_HEIGHT) {
            y = FRAME_HEIGHT - length;
        }

    }

}
