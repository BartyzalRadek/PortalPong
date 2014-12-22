/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong.panels;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import static pingpong.MainForm.ABOUT_PANEL;
import static pingpong.MainForm.FONT_SIZE;
import static pingpong.MainForm.LEADERBOARDS_PANEL;
import static pingpong.MainForm.MP_PANEL;
import static pingpong.MainForm.OPTIONS_PANEL;
import static pingpong.MainForm.SP_PANEL;

/**
 *
 * @author Radek Bartyzal
 */
public class MenuPanel extends MatrixPanel {

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private List<JLabel> labels;

    public MenuPanel() {
        super();
        init();
    }

    private void init() {
        labels = new LinkedList<JLabel>();

        jLabel1 = new javax.swing.JLabel("Singleplayer");
        jLabel2 = new javax.swing.JLabel("Multiplayer");
        jLabel3 = new javax.swing.JLabel("Options");
        jLabel4 = new javax.swing.JLabel("Leaderboards");
        jLabel5 = new javax.swing.JLabel("About");
        jLabel6 = new javax.swing.JLabel("Exit");

        labels.add(jLabel1);
        labels.add(jLabel2);
        labels.add(jLabel3);
        labels.add(jLabel4);
        labels.add(jLabel5);
        labels.add(jLabel6);
        
        labelActions();

        initLayout();
    }

    private void initLayout() {
        //JLabel labels[] = {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6};

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new Dimension(20, 100)));
        for (JLabel label : labels) {
            setLabelParam(label);
            this.add(label);
            this.add(Box.createRigidArea(new Dimension(20, 20)));
        }
    }

    private void labelActions() {
        //Singleplayer
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 20));
                CardLayout cl = (CardLayout) (getParent().getLayout());
                cl.show(getParent(), SP_PANEL);
            }

        });

        //Multiplayer
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2.setFont(new Font("Tahoma", Font.PLAIN, 20));
                CardLayout cl = (CardLayout) (getParent().getLayout());
                cl.show(getParent(), MP_PANEL);
            }

        });

        //Options
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3.setFont(new Font("Tahoma", Font.PLAIN, 20));
                CardLayout cl = (CardLayout) (getParent().getLayout());
                cl.show(getParent(), OPTIONS_PANEL);
            }

        });

        //Leaderboards
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4.setFont(new Font("Tahoma", Font.PLAIN, 20));
                CardLayout cl = (CardLayout) (getParent().getLayout());
                cl.show(getParent(), LEADERBOARDS_PANEL);
            }

        });

        //About
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5.setFont(new Font("Tahoma", Font.PLAIN, 20));
                CardLayout cl = (CardLayout) (getParent().getLayout());
                cl.show(getParent(), ABOUT_PANEL);
            }

        });

        //Exit
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.exit(1);
            }

        });

    }

    private void setLabelParam(final JLabel label) {
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setFont(new java.awt.Font("Tahoma", 0, FONT_SIZE));
        label.setForeground(new java.awt.Color(240, 240, 240));
        label.setMaximumSize(new java.awt.Dimension(200, 25));
        label.setMinimumSize(new java.awt.Dimension(200, 25));
        label.setPreferredSize(new java.awt.Dimension(200, 25));
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label.setFont(new Font("Tahoma", Font.BOLD, FONT_SIZE + (FONT_SIZE / 10)));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label.setFont(new Font("Tahoma", Font.PLAIN, FONT_SIZE));
            }
        });
    }

    public void resetLabels() {
        //JLabel labels[] = {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6};
        for (JLabel label : labels) {
            label.setFont(new java.awt.Font("Tahoma", 0, FONT_SIZE));
            label.setMaximumSize(new java.awt.Dimension(200, 25));
            label.setMinimumSize(new java.awt.Dimension(200, 25));
            label.setPreferredSize(new java.awt.Dimension(200, 25));
        }
    }
}
