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
import static pingpong.panels.CardsPanel.FRAME_WIDTH;
import static pingpong.panels.CardsPanel.LABEL_SIZE;

/**
 *
 * @author Radek Bartyzal
 */
public class AboutPanel extends JPanel implements AbleToResizeGUI{

    private javax.swing.JLabel jLabel1;
    

    public AboutPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setBackground(Color.black);

        jLabel1 = new JLabel();
        setBackLabel();

        //Adding BACK label
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(FRAME_WIDTH / 100, FRAME_HEIGHT - 110)));
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

        drawText(g2);
        
        
        g.dispose();
    }
    
    private void drawText(Graphics2D g){
        g.setFont(new Font("Tahoma", Font.PLAIN, FONT_SIZE));
        g.setColor(new Color(240,240,240));
        int x = getStringLocation(g, "Author: Radek Bartyzal", this.getWidth());
        g.drawString("Author: Radek Bartyzal", x, this.getHeight()/2 - FONT_SIZE);
        g.drawString("Created: December 2014", x, this.getHeight()/2 + FONT_SIZE);
        g.drawString("Email: rbartyzal1@gmail.com", x, this.getHeight()/2 + 3*FONT_SIZE);
        
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
        this.add(Box.createRigidArea(new Dimension(FRAME_WIDTH / 50, FRAME_HEIGHT - 60)));
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
