package pingpong.panels;

import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

/**
 *
 * @author Radek Bartyzal
 */
public class SPGraphicsPanel extends GraphicsPanel {

    public SPGraphicsPanel() {
        drawableList.add(ball);
        drawableList.add(paddle1);
        drawableList.add(paddle2);
        init();
    }
    
    private void init(){
        setKeyBindings();
    }

    @Override
    protected void setKeyBindings(){
        super.setKeyBindings();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), KeyEvent.VK_ENTER);
        
        am.put(KeyEvent.VK_ENTER, new Action(KeyEvent.VK_ENTER));
    }

    @Override
    public void mainTimer() {
        super.mainTimer();

        paddle2.AImove(ball, 500);
        AIteleport();
    }

    /**
     * Different AI than the one in Endless Panel
     */
    private void AIteleport() {
        int difference = (ball.getY() > paddle2.getY() ? ball.getY() - paddle2.getY() : paddle2.getY() - ball.getY());
        if (ball.getX() > 900 && difference > 200) {
            if (player2.useTeleport()) {
                createTeleport();
            }
        }
    }
}
