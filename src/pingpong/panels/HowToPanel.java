/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pingpong.panels;

import java.awt.CardLayout;
import java.awt.Color;
import static java.awt.Component.LEFT_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pingpong.AbleToResizeGUI;
import static pingpong.MainForm.MENU_PANEL;
import static pingpong.panels.CardsPanel.FONT_SIZE;
import static pingpong.panels.CardsPanel.FRAME_HEIGHT;
import static pingpong.panels.CardsPanel.LABEL_SIZE;

/**
 *
 * @author Radek Bartyzal
 */
public class HowToPanel extends JPanel implements AbleToResizeGUI {
    private javax.swing.JLabel jLabel1;
    
    private final String teleports= "Pickup T to get teleports";
    private final String plus= "Pickup + to get bigger paddle";
    private final String minus= "Pickup - to get smaller paddle";
    private final String pressEP= "Press E,P to teleport";
    private final String pressESC= "Esc - Pause";
    private final String pressWSUD= "W, S, Up, Down - Move paddles";
    

    public HowToPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setBackground(Color.black);

        jLabel1 = new JLabel();
        setBackLabel();

        //Adding BACK label
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(20, FRAME_HEIGHT - 60)));
        this.add(jLabel1);

    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2;
        g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        g2.setFont(new Font("Tahoma", Font.PLAIN, FONT_SIZE));
        g2.setColor(new Color(240,240,240));
        
        g2.drawString("How to play", 50, 60);
        drawText(g2);
        
        g2.dispose();
    }
    
    private void drawText(Graphics2D g){
        int x = getStringLocation(g, teleports, this.getWidth());
        int y = getHeight() / 2 - 4*FONT_SIZE;
        int offsetY = 2*FONT_SIZE;
         g.drawString(plus, x, y);
         g.drawString(minus, x, y + offsetY);
         g.drawString(teleports, x, y + 2*offsetY);
         g.drawString(pressEP, x, y + 3*offsetY);
         g.drawString(pressESC, x, y + 4*offsetY);
         g.drawString(pressWSUD, x, y + 5*offsetY);
        
    }
    
    private int getStringLocation(Graphics g, String s, int widthOfComponent) {
        int strlen = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
        return (widthOfComponent / 2) - (strlen / 2);
    }
    
    private void setBackLabel() {
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, FONT_SIZE));
        jLabel1.setMaximumSize(LABEL_SIZE);
        jLabel1.setMinimumSize(LABEL_SIZE);
        jLabel1.setPreferredSize(LABEL_SIZE);
        jLabel1.setAlignmentX(LEFT_ALIGNMENT);
        jLabel1.setText("Back");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1.setFont(new Font("Tahoma", Font.BOLD, FONT_SIZE + (FONT_SIZE / 10)));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1.setFont(new Font("Tahoma", Font.PLAIN, FONT_SIZE));
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CardLayout cl = (CardLayout) (getParent().getLayout());
                cl.show(getParent(), MENU_PANEL);
            }
        });
    }
    
    private void resetLayout(){
        this.removeAll();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(20, FRAME_HEIGHT - 60)));
        this.add(jLabel1);
    }
    
    private void resetLabels() {
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, FONT_SIZE));
        jLabel1.setMaximumSize(LABEL_SIZE);
        jLabel1.setMinimumSize(LABEL_SIZE);
        jLabel1.setPreferredSize(LABEL_SIZE);
    }

    @Override
    public void resizeGUI() {
        resetLabels();
        resetLayout();
        repaint();
    }
}
