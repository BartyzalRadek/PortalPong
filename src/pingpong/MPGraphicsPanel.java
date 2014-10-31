package pingpong;

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
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                paddle2.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                paddle2.moveDown();
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
