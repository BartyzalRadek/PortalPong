package pingpong;

import java.awt.Color;
import java.awt.Graphics;
import pingpong.panels.CardsPanel;

/**
 *
 * @author Radek Bartyzal
 */
public class Teleport implements Drawable {

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int duration; //How long has been the teleport on screen
    private int width;
    private int height;
    private static final int MAX_DURATION = 40;

    public Teleport() {
        duration = 0;
        width = CardsPanel.FRAME_HEIGHT / 25;
        height = CardsPanel.FRAME_HEIGHT / 25 * 2;
    }

    public void setLocations(Ball ball) {
        x1 = ball.getX() + (int) (5 * ball.getVx());
        y1 = ball.getY() + (int) (5 * ball.getVy());

        x2 = (int) (Math.floor(Math.random() * 900 + 100));
        y2 = (int) (Math.floor(Math.random() * 420));
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawOval(x2, y2, width, height);
        g.setColor(Color.ORANGE);
        g.drawOval(x1, y1, width, height);
        g.setColor(Color.WHITE);
    }

    public boolean isExpired() {
        return duration >= MAX_DURATION;
    }

    /**
     * Extends duration by x
     *
     * @param x Is added to duration
     */
    public void extendDuration(int x) {
        duration += x;
    }

    public void resetDuration() {
        duration = 0;
    }

    public void resize() {
        width = CardsPanel.FRAME_HEIGHT / 25;
        height = CardsPanel.FRAME_HEIGHT / 25 * 2;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getHeight() {
        return height;
    }
    
    public int getWidth(){
        return width;
    }
}
