package pingpong;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 *
 * @author Radek Bartyzal
 */
public class MPGraphicsPanel extends GraphicsPanel {

    public MPGraphicsPanel() {
        drawableList.add(ball);
        drawableList.add(paddle1);
        drawableList.add(paddle2);
        powerUpList.add(t);
        endGameTimer.start();

    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        drawSomebodyWon(g);
        g.dispose();
    }

    @Override
    public void mainTimer() {
        super.mainTimer();

        for (int i = 0; i < powerUpList.size(); i++) {
            powerUpList.get(i).score(paddle2, player2);
        }
        ball.bounceOffPaddle(paddle2, player2);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (paddle2.y > 0) {
                    paddle2.y -= 20;
                }

                break;
            case KeyEvent.VK_DOWN:
                if ((paddle2.y + paddle2.length) < this.getHeight()) {
                    paddle2.y += 20;
                }

                break;
            case KeyEvent.VK_P:
                if (t.numberB > 0) {
                    createTeleport();
                    t.numberB -= 1;
                }
                break;
            case KeyEvent.VK_ENTER:
                if (hasSomebodyWon() || gamePaused) {
                    closePanel = true;
                }
        }
    }
}
