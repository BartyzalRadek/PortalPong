package pingpong;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Radek Bartyzal
 */
public class Paddle implements Drawable {

    public int x;
    public int y;
    public int v = 10;
    public int length;
    public int width;
    public int player; //kvuli PowerUp.score()
    public int center;
    public int duration; //kvuli GraphicsPanel.paddleSizeReturn()

    public Paddle(int x, int player, int v) {
        this.x = x;
        this.player = player;
        this.v = v;
        y = 10;
        length = 100;
        width = 20;
        

    }

    public void move(Ball ball,int height) {
        center = (y + (length/2));
        
        if (center < ball.y) {
            if ((y + length) < height) {
                y += v;
            }
        }
        else{
            if (y > 0) {
                y -= v;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, length);
    }
    
    public void lengthChange(){
        
    }
}
