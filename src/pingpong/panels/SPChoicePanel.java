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
import static pingpong.MainForm.CLASSIC_PANEL;
import static pingpong.MainForm.ENDLESS_PANEL;
import static pingpong.MainForm.FRAME_HEIGHT;
import static pingpong.MainForm.FRAME_WIDTH;
import static pingpong.MainForm.MENU_PANEL;

/**
 *
 * @author darmy
 */
public class SPChoicePanel extends JPanel {

    private static final int BORDER_THICKNESS = 1;
    //private final String CLASSIC_DESCRIPTION = "Classic singleplayer mode."You are playing <br>against a computer.</html>";
    //private final String ENDLESS_DESCRIPTION = "<html>Endless singleplayer mode.<br>Get the highest <br>score possible before you lose.</html>";
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private boolean activeLeftPanel = false;
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
            g.setColor(new java.awt.Color(240, 240, 240));
            drawLeftPanel(g);
            g.setColor(Color.gray);
            drawRightPanel(g);
        }else{
            g.setColor(Color.gray);
            drawLeftPanel(g);
            g.setColor(new java.awt.Color(240, 240, 240));
            drawRightPanel(g);
        }
        g.dispose();
    }

    private void drawLeftPanel(Graphics g) {
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString("Classic mode", 200, 50);
        g.setFont(new Font("Tahoma", Font.PLAIN, 15));
        g.drawString("You are playing", 210, 125);
        g.drawString("against a computer.", 200, 150);

    }

    private void drawRightPanel(Graphics g) {
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString("Endless mode", 590, 50);
        g.setFont(new Font("Tahoma", Font.PLAIN, 15));
        g.drawString("Get the highest", 610, 125);
        g.drawString("score possible before you lose.", 570, 150);

    }

    private void initComponents() {
        this.setBackground(Color.white);
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

        //Addind BACK label
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        jPanel1.add(Box.createRigidArea(new Dimension(10, 390)));
        jPanel1.add(jLabel1);

        add(jPanel1);
        add(jPanel2);

    }

    /*private void setLabelsFont(JLabel[] labels) {
     for (JLabel label : labels) {
     label.setFont(new java.awt.Font("Tahoma", 0, 20));
     label.setForeground(new java.awt.Color(240, 240, 240));
     label.setAlignmentX(CENTER_ALIGNMENT);
     }
     }*/
    private void setBackLabel() {
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20));
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setAlignmentX(LEFT_ALIGNMENT);
        jLabel1.setText("Back");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1.setFont(new Font("Tahoma", Font.BOLD, 22));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 20));
            }

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

            /*public void mouseExited(java.awt.event.MouseEvent evt) {
                activeLeftPanel = false;
                repaint();
            }*/

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

            /*public void mouseExited(java.awt.event.MouseEvent evt) {
                activeLeftPanel = true;
                repaint();
            }*/

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CardLayout cl = (CardLayout) (getParent().getLayout());
                cl.show(getParent(), ENDLESS_PANEL);
            }
        });
    }

}
