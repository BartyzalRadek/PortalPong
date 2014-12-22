/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pingpong.panels;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JPanel;
import static pingpong.MainForm.FRAME_HEIGHT;
import static pingpong.MainForm.FRAME_WIDTH;
import static pingpong.MainForm.FONT_SIZE;

/**
 *
 * @author Radek Bartyzal
 */
public class CardsPanel extends JPanel implements ComponentListener{

    public CardsPanel(LayoutManager lm) {
        super(lm);
        addComponentListener(this);
    }

    @Override
    public void componentResized(ComponentEvent ce) {
        FRAME_HEIGHT = this.getHeight();
        FRAME_WIDTH = this.getWidth();
        FONT_SIZE = (FRAME_HEIGHT / 100) * 4;
        
        for (Component p : getComponents()) {
            if (p instanceof MenuPanel) {
                ((MenuPanel) p).resetLabels();
            }
        }
    }

    @Override
    public void componentMoved(ComponentEvent ce) {
    }

    @Override
    public void componentShown(ComponentEvent ce) {
    }

    @Override
    public void componentHidden(ComponentEvent ce) {
    }

}
