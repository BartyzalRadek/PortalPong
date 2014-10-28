package pingpong;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Radek Bartyzal
 */
public class Teleport implements Drawable {

    public int x1;
    public int y1;
    public int x2;
    public int y2;
    public int duration;

    public Teleport() {
        duration = 0;
    }
    
    public void setVariables(Ball ball){
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
}
