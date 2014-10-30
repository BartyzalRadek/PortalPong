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

        for (PowerUp p : powerUpList) {
            p.score(paddle2, player2);
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
                if (player2.teleports > 0) {
                    createTeleport();
                    player2.teleports -= 1;
                }
                break;
            case KeyEvent.VK_ENTER:
                if (hasSomebodyWon() || gamePaused) {
                    closePanel = true;
                }
        }
    }
}
