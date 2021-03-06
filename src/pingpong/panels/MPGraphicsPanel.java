package pingpong.panels;

import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

/**
 *
 * @author Radek Bartyzal
 */
public class MPGraphicsPanel extends GraphicsPanel {

    public MPGraphicsPanel() {
        drawableList.add(ball);
        drawableList.add(paddle1);
        drawableList.add(paddle2);
        init();
    }

    private void init() {
        setKeyBindings();
    }

    @Override
    protected void setKeyBindings() {
        super.setKeyBindings();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), KeyEvent.VK_UP);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), KeyEvent.VK_UP + 100);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), KeyEvent.VK_DOWN);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), KeyEvent.VK_DOWN + 100);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), KeyEvent.VK_P);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), KeyEvent.VK_ENTER);

        am.put(KeyEvent.VK_UP, new Action(KeyEvent.VK_UP));
        am.put(KeyEvent.VK_UP + 100, new Action(KeyEvent.VK_UP + 100));
        am.put(KeyEvent.VK_DOWN, new Action(KeyEvent.VK_DOWN));
        am.put(KeyEvent.VK_DOWN + 100, new Action(KeyEvent.VK_DOWN + 100));
        am.put(KeyEvent.VK_P, new Action(KeyEvent.VK_P));
        am.put(KeyEvent.VK_ENTER, new Action(KeyEvent.VK_ENTER));
    }

}
