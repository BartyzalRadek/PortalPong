/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong.panels;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
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
public class FinalScorePanel extends JPanel implements AbleToResizeGUI {

    private javax.swing.JLabel jLabel1;
    private boolean newHighScore;
    private InputFrame nameDialog;
    private int[] finalScore;
    
    public boolean nameSet = false;
    public String name = "Darmy";

    public FinalScorePanel() {
        initComponents();
        
    }

    private void initComponents() {
        this.setBackground(Color.black);

        jLabel1 = new JLabel();
        setBackLabel();

        //Adding Continue label
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(20, FRAME_HEIGHT - 60)));
        this.add(jLabel1);

    }
    
    public void showInputFrame(){
        nameDialog = new InputFrame(this);
        nameDialog.setLocation(this.getLocationOnScreen().x + this.getWidth() / 2 - nameDialog.getWidth() / 2,
         this.getLocationOnScreen().y + this.getHeight() / 2 - nameDialog.getHeight() / 2);
        nameDialog.setVisible(true);
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
        g2.setColor(new Color(240, 240, 240));

        g2.drawString("Final score", 50, 60);
        drawText(g2);

        g2.dispose();
    }

    private void drawText(Graphics2D g) {
        int collumn1 = FRAME_WIDTH / 3;
        int collumn2 = collumn1 + (int) g.getFontMetrics().getStringBounds("Total  ", g).getWidth();
        int collumn3 = collumn2 + (int) g.getFontMetrics().getStringBounds("999 ", g).getWidth();
        int firstLineY = FRAME_HEIGHT / 3;
        int lineOffsetY = 2 * FONT_SIZE;

        String[][] finalScoreText = new String[3][5];
        finalScoreText[0][0] = "+";
        finalScoreText[0][1] = "-";
        finalScoreText[0][2] = "T";
        finalScoreText[0][3] = "pings";
        finalScoreText[0][4] = "Total";

        for (int i = 0; i < finalScoreText[1].length; i++) {
            finalScoreText[1][i] = String.valueOf(finalScore[i]);
        }

        finalScoreText[2][0] = "+ = +3 points";
        finalScoreText[2][1] = "-  = -3 points";
        finalScoreText[2][2] = "T = +3 points";
        finalScoreText[2][3] = "1 ping = +1 point";
        finalScoreText[2][4] = "";
        
        if(newHighScore){
            finalScoreText[2][4] = "NEW HIGHSCORE";
        }

        for (int i = 0; i < 5; i++) {
            g.drawString(finalScoreText[0][i], collumn1, firstLineY + i * lineOffsetY);
            g.drawString(finalScoreText[1][i], collumn2, firstLineY + i * lineOffsetY);
            g.drawString(finalScoreText[2][i], collumn3, firstLineY + i * lineOffsetY);
        }

        g.drawString("Your name:", collumn1, firstLineY + 5 * lineOffsetY);
        
        if (nameSet) {
            int x = (int) g.getFontMetrics().getStringBounds("Your name:  ", g).getWidth();
            g.drawString(name, collumn1 + x, firstLineY + 5 * lineOffsetY);
        }
    }
    
    public void setFinalScore(int[] score){
        finalScore = new int[score.length];
        System.arraycopy(score, 0, finalScore, 0, score.length);
        
    }
    
    public void submitNewScore() {
        for (Component p : getParent().getComponents()) {
            if (p instanceof LeaderboardsPanel) {
                ((LeaderboardsPanel) p).addScore(name, finalScore[4]);
                newHighScore = ((LeaderboardsPanel) p).isNewHighscore(finalScore[4]);
            }
        }
    }

    private void setBackLabel() {
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, FONT_SIZE));
        jLabel1.setMaximumSize(LABEL_SIZE);
        jLabel1.setMinimumSize(LABEL_SIZE);
        jLabel1.setPreferredSize(LABEL_SIZE);
        jLabel1.setAlignmentX(LEFT_ALIGNMENT);
        jLabel1.setText("Continue");
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

    private void resetLayout() {
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
