package pingpong;

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
        endGameTimer.start();
    }

    @Override
    public void mainTimer() {
        super.mainTimer();

        paddle2.AImove(ball, 500);
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

    /**
     * Different AI than the one in Endless Panel
     */
    private void AIteleport() {
        int difference = (ball.y > paddle2.getY() ? ball.y - paddle2.getY() : paddle2.getY() - ball.y);
        if (ball.x > 900 && difference > 200) {
            if (player2.teleports > 0) {
                createTeleport();
                player2.teleports -= 1;
            }
        }
    }
}
