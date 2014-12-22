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
import pingpong.AbleToResizeGUI;
/**
 *
 * @author Radek Bartyzal
 */
public class CardsPanel extends JPanel implements ComponentListener{

    public static int FRAME_HEIGHT = 500;
    public static int FRAME_WIDTH = 1000;
    public static int FONT_SIZE = 20;
    public static Dimension LABEL_SIZE = new Dimension(FRAME_WIDTH/5 + FONT_SIZE*2, FONT_SIZE + FONT_SIZE/4);
    
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
            if (p instanceof AbleToResizeGUI) {
                ((AbleToResizeGUI) p).resizeGUI();
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
