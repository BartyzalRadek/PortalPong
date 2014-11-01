/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.StyleConstants;
import static pingpong.MainForm.FRAME_HEIGHT;
import static pingpong.MainForm.FRAME_WIDTH;
import static pingpong.MainForm.MENU_PANEL;

/**
 *
 * @author darmy
 */
public class SPChoicePanel extends JPanel {

    private static final int BORDER_THICKNESS = 1;
    private final String CLASSIC_DESCRIPTION = "<html>Classic singleplayer mode.<br>You are playing <br>against a computer.</html>";
    private final String ENDLESS_DESCRIPTION = "<html>Endless singleplayer mode.<br>Get the highest <br>score possible before you lose.</html>";
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;

    public SPChoicePanel() {
        initComponents();
    }

    private void initComponents() {
        this.setBackground(Color.white);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        JPanel panels[] = {jPanel1, jPanel2};
        JLabel labels[] = {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5};
        for (final JPanel p : panels) {
            setPanelSettings(p);
        }
        setLabelsFont(labels);
        setBackLabel();
        
        jLabel2.setText("Classic");
        jLabel3.setText("Endless");
        jLabel4.setText(CLASSIC_DESCRIPTION);
        //jLabel4.setMinimumSize(new Dimension(200,200));
        //jLabel4.setMaximumSize(new Dimension(200,200));
        //jLabel4.setPreferredSize(new Dimension(200,200));
        jLabel5.setText(ENDLESS_DESCRIPTION);
        
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        jPanel1.add(Box.createRigidArea(new Dimension(100, 10)));
        jPanel1.add(jLabel2);
        jPanel1.add(jLabel4);
        jPanel1.add(jLabel1);
        
        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.Y_AXIS));
        jPanel2.add(jLabel3);
        jPanel2.add(jLabel5);

        add(jPanel1);
        add(jPanel2);

    }

    private void setLabelsFont(JLabel[] labels) {
        for (JLabel label : labels) {
            label.setFont(new java.awt.Font("Tahoma", 0, 20)); 
            label.setForeground(new java.awt.Color(240, 240, 240));
            label.setAlignmentX(CENTER_ALIGNMENT);
        }
    }

    private void setBackLabel() {
        jLabel1.setText("Back");
        //jLabel1.setLocation(50, FRAME_HEIGHT - 40);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1.setFont(new Font("Tahoma", Font.BOLD, 22));
                jPanel1.setBorder(new LineBorder(Color.white, BORDER_THICKNESS, false));
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
        p.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                p.setBorder(new LineBorder(Color.white, BORDER_THICKNESS, false));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                p.setBorder(new LineBorder(Color.black, BORDER_THICKNESS, false));
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CardLayout cl = (CardLayout) (getParent().getLayout());
                cl.show(getParent(), MENU_PANEL);
            }
        });
    }

}
