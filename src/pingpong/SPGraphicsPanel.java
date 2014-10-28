package pingpong;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 *
 * @author Radek Bartyzal
 */
public class SPGraphicsPanel extends GraphicsPanel {

    public SPGraphicsPanel() {
        drawableList.add(ball);
        drawableList.add(paddle1);
        drawableList.add(paddle2);
        powerUpList.add(t);
        endGameTimer.start();
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        drawSomebodyWon(g); //Cannot be in GraphicsPanel because of EndlessPanel - drawing over final score
        g.dispose();
    }

    @Override
    public void mainTimer() {
        super.mainTimer();
        
        for (PowerUp powerUp1 : powerUpList) {
            powerUp1.score(paddle2, player2);
        }
        ball.bounceOffPaddle(paddle2, player2);
        
        paddle2.move(ball, 500);
        AIteleport();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_ENTER:
                if(hasSomebodyWon() || gamePaused){
                    closePanel = true;
                }
        }
    }

    private void AIteleport() {
        int difference = (ball.y > paddle2.y ? ball.y - paddle2.y : paddle2.y - ball.y);
        if (ball.x > 900 && difference > 200) {
            if (t.numberB > 0) {
                createTeleport();
                t.numberB -= 1;
            }
        }
    }
}
