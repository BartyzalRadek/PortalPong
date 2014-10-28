/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 *
 * @author Darmy
 */
public class MPGraphicsPanel extends GraphicsPanel {

    public MPGraphicsPanel() {
        drawableList.add(ball);
        drawableList.add(paddle1);
        drawableList.add(paddle2);
        powerUpList.add(t);
        timer3.start();
        timer4.start();
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
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
            case KeyEvent.VK_W:
                if (paddle1.y > 0) {
                    paddle1.y -= 20;
                }

                break;
            case KeyEvent.VK_S:
                if ((paddle1.y + paddle1.length) < this.getHeight()) {
                    paddle1.y += 20;
                }

                break;
            case KeyEvent.VK_E:
                if (t.numberA > 0) {
                    createTeleport();
                    t.numberA -= 1;
                }

                break;
            case KeyEvent.VK_P:
                if (t.numberB > 0) {
                    createTeleport();
                    t.numberB -= 1;
                }
                break;
            case KeyEvent.VK_Q:
                ball.vx += 1;
                ball.vy += 1;
                break;
            case KeyEvent.VK_A:
                ball.vx -= 1;
                ball.vy -= 1;
                break;
            case KeyEvent.VK_ENTER:
                if (endGame == 1) {
                    endGame = 2;
                }
                if (gamePaused) {
                    endGame = 2;
                }
            case KeyEvent.VK_ESCAPE:
                pauseGame();
                break;
            case KeyEvent.VK_SPACE:
                startGame = 1;
                break;
        }
    }
}
