/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pingpong.panels;

import java.awt.LayoutManager;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JPanel;
import static pingpong.MainForm.FRAME_HEIGHT;
import static pingpong.MainForm.FRAME_WIDTH;

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
        
        System.out.println("Height= " + FRAME_HEIGHT);
        System.out.println("Width= " + FRAME_WIDTH);
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
