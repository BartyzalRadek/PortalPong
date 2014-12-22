/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pingpong.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JPanel;
import static pingpong.MainForm.FONT_SIZE;
import static pingpong.MainForm.FRAME_HEIGHT;
import static pingpong.MainForm.FRAME_WIDTH;
import static pingpong.MainForm.LABEL_SIZE;

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
        LABEL_SIZE = new Dimension(FRAME_WIDTH/5 + FONT_SIZE*2, FONT_SIZE + FONT_SIZE/4);
        
        for (Component p : getComponents()) {
            if (p instanceof MenuPanel) {
                ((MenuPanel) p).resetLabels();
                ((MenuPanel) p).resetLayout();
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
