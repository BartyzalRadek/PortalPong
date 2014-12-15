
package pingpong.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import static pingpong.MainForm.FRAME_HEIGHT;
import static pingpong.MainForm.FRAME_WIDTH;

/**
 *
 * @author Radek Bartyzal
 */
public class LeaderboardsDrawPanel extends JPanel {
    String[][] leaderboard;
    private static final int TOP_OFFSET = 20;
    
    /**
     * Drawing leaderboard
     * @param locX Location of this panel in parent component
     * @param locY Location of this panel in parent component
     */
    public LeaderboardsDrawPanel(int locX, int locY) {
        this.setSize(new Dimension(FRAME_WIDTH - locX, FRAME_HEIGHT-locY));
        init();
    }
    
    private void init(){
        setBackground(Color.red);
        setMinimumSize(new Dimension(FRAME_WIDTH-100, FRAME_HEIGHT-200));
        setMaximumSize(new Dimension(FRAME_WIDTH-100, FRAME_HEIGHT-200));
        setPreferredSize(new Dimension(FRAME_WIDTH-100, FRAME_HEIGHT-200));
        setAlignmentX(LEFT_ALIGNMENT);
    }
    
    @Override
    public void paint(Graphics g) {
        leaderboard = ((LeaderboardsPanel)getParent()).getLeaderboard();
        g.setFont(new Font("Tahoma", Font.PLAIN, 20));
        g.setColor(Color.WHITE);

        for (int i = 0; i < leaderboard.length; i++) {
            if (i != 9) {
                g.drawString(String.valueOf(i + 1) + ".", 150, 30 * i + TOP_OFFSET);
            } else {
                g.drawString(String.valueOf(i + 1) + ".", 140, 30 * i + TOP_OFFSET);
            }
            g.drawString(leaderboard[i][0], 180, 30 * i + TOP_OFFSET);
            g.drawString(leaderboard[i][1], 280, 30 * i + TOP_OFFSET);
            g.drawString(leaderboard[i][2], 350, 30 * i + TOP_OFFSET);
        }
    }
    
    
}
