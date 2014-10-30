package pingpong;

import java.awt.Color;
import java.awt.Graphics;

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
    private static final int MAX_DURATION = 40;

    public Teleport() {
        duration = 0;
    }
    
    public void setLocations(Ball ball){
        x1 = (ball.vx > 0 ? ball.x + 20 : ball.x - 30);
        y1 = (ball.vy > 0 ? ball.y - 5 : ball.y - 35);
        x2 = (int) (Math.floor(Math.random() * 900 + 100));
        y2 = (int) (Math.floor(Math.random() * 420));
    }
    
    @Override
    public void draw(Graphics g){
        g.setColor(Color.BLUE);
            g.drawOval(x2, y2, 20, 40);
            g.setColor(Color.ORANGE);
            g.drawOval(x1, y1, 20, 40);
            g.setColor(Color.WHITE);
    }
    
    public boolean isExpired(){
        return duration >= MAX_DURATION;
    }

    /**
     * Extends duration by x
     * @param x Is added to duration
     */
    public void extendDuration(int x) {
        duration += x;
    }
    
    public void resetDuration(){
        duration = 0;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }
}
