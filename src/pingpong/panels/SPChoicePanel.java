/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong.panels;

import java.awt.CardLayout;
import java.awt.Color;
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
import static pingpong.MainForm.CLASSIC_PANEL;
import static pingpong.MainForm.ENDLESS_PANEL;
import static pingpong.MainForm.MENU_PANEL;
import static pingpong.panels.CardsPanel.FONT_SIZE;
import static pingpong.panels.CardsPanel.FRAME_HEIGHT;
import static pingpong.panels.CardsPanel.FRAME_WIDTH;
import static pingpong.panels.CardsPanel.LABEL_SIZE;

/**
 *
 * @author Radek Bartyzal
 */
public class SPChoicePanel extends JPanel implements AbleToResizeGUI {

    private static final int BORDER_THICKNESS = 1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private boolean activeLeftPanel = true;
    private javax.swing.JLabel jLabel1;

    public SPChoicePanel() {
        initComponents();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2;
        g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

        if (activeLeftPanel) {
            g2.setColor(new java.awt.Color(240, 240, 240));
            drawLeftPanel(g2);
            g2.setColor(Color.gray);
            drawRightPanel(g2);
        } else {
            g2.setColor(Color.gray);
            drawLeftPanel(g2);
            g2.setColor(new java.awt.Color(240, 240, 240));
            drawRightPanel(g2);
        }
        g.dispose();
    }

    private void drawLeftPanel(Graphics g) {
        g.setFont(new Font("Tahoma", Font.BOLD, FONT_SIZE));
        g.drawString("Classic mode", getStringLocation(g, "Classic mode", jPanel1.getWidth()), 50);
        g.setFont(new Font("Tahoma", Font.PLAIN, FONT_SIZE - FONT_SIZE / 5));
        g.drawString("You are playing", getStringLocation(g, "You are playing", jPanel1.getWidth()), 125);
        g.drawString("against a computer.", getStringLocation(g, "against a computer.", jPanel1.getWidth()), 125 + FONT_SIZE + FONT_SIZE/2);

    }

    private int getStringLocation(Graphics g, String s, int widthOfComponent) {
        int strlen = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
        return widthOfComponent / 2 - strlen / 2;
    }

    private void drawRightPanel(Graphics g) {
        g.setFont(new Font("Tahoma", Font.BOLD, FONT_SIZE));
        g.drawString("Endless mode", jPanel1.getWidth() + getStringLocation(g, "Endless mode", jPanel1.getWidth()), 50);
        g.setFont(new Font("Tahoma", Font.PLAIN, FONT_SIZE - FONT_SIZE / 5));
        g.drawString("Get the highest", jPanel1.getWidth() + getStringLocation(g, "Get the highest", jPanel1.getWidth()), 125);
        g.drawString("score possible before you lose.", jPanel1.getWidth() + getStringLocation(g, "score possible before you lose.", jPanel1.getWidth()), 125 + FONT_SIZE + FONT_SIZE/2);

    }

    private void initComponents() {
        this.setBackground(Color.black);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        jLabel1 = new JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        JPanel panels[] = {jPanel1, jPanel2};
        for (final JPanel p : panels) {
            setPanelSettings(p);
        }
        setBackLabel();
        initPanel1();
        initPanel2();

        //Adding BACK label
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        jPanel1.add(Box.createRigidArea(new Dimension(FRAME_WIDTH / 100, FRAME_HEIGHT - 110)));
        jPanel1.add(jLabel1);

        add(jPanel1);
        add(jPanel2);

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

    private void setPanelSettings(final JPanel p) {
        p.setMinimumSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT));
        p.setMaximumSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT));
        p.setPreferredSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT));
        p.setBackground(new java.awt.Color(0, 0, 0));

    }

    private void initPanel1() {
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                activeLeftPanel = true;
                repaint();
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CardLayout cl = (CardLayout) (getParent().getLayout());
                cl.show(getParent(), CLASSIC_PANEL);
            }
        });
    }

    private void initPanel2() {
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                activeLeftPanel = false;
                repaint();
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CardLayout cl = (CardLayout) (getParent().getLayout());
                cl.show(getParent(), ENDLESS_PANEL);
            }
        });
    }

    private void resetPanels() {
        setPanelSettings(jPanel1);
        jPanel1.removeAll();
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        jPanel1.add(Box.createRigidArea(new Dimension(50, FRAME_HEIGHT - 60)));
        jPanel1.add(jLabel1);

        setPanelSettings(jPanel2);
    }

    private void resetLabels() {
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, FONT_SIZE));
        jLabel1.setMaximumSize(LABEL_SIZE);
        jLabel1.setMinimumSize(LABEL_SIZE);
        jLabel1.setPreferredSize(LABEL_SIZE);
    }

    @Override
    public void resizeGUI() {
        resetPanels();
        resetLabels();
        repaint();
    }

}
