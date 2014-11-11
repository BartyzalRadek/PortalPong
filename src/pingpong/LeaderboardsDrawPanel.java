/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pingpong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import static pingpong.MainForm.FRAME_HEIGHT;
import static pingpong.MainForm.FRAME_WIDTH;

/**
 *
 * @author darmy
 */
public class LeaderboardsDrawPanel extends JPanel {
    String[][] leaderboard;

    /**
     * Drawing leaderboard
     * @param leaderboard 
     * @param locX Location of this panel in parent component
     * @param locY Location of this panel in parent component
     */
    public LeaderboardsDrawPanel(String[][] leaderboard, int locX, int locY) {
        this.leaderboard = leaderboard;
        this.setBackground(Color.red);
        this.setSize(new Dimension(FRAME_WIDTH - locX, FRAME_HEIGHT-locY));
    }
    
    /*@Override
    public void paint(Graphics g) {
        g.setFont(new Font("Tahoma", Font.PLAIN, 20));
        g.setColor(Color.WHITE);

        //update();
        for (int i = 0; i < leaderboard.length; i++) {
            if (i != 9) {
                g.drawString(String.valueOf(i + 1) + ".", 150, 30 * i + 80);
            } else {
                g.drawString(String.valueOf(i + 1) + ".", 140, 30 * i + 80);
            }
            g.drawString(leaderboard[i][0], 180, 30 * i + 80);
            g.drawString(leaderboard[i][1], 280, 30 * i + 80);
            g.drawString(leaderboard[i][2], 350, 30 * i + 80);
        }
    }*/

    
}
