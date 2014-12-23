package pingpong.panels;

import java.awt.CardLayout;
import java.awt.Color;
import static java.awt.Component.LEFT_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class LeaderboardsPanel extends JPanel implements AbleToResizeGUI {

    private javax.swing.JLabel jLabel1;

    
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private Date date;
    private final String[][] leaderboard = new String[10][3];
    private BufferedReader bfr;
    private BufferedWriter bfw;
    private File statsFile;

    public LeaderboardsPanel() {
        init();
    }

    private void init() {
        statsFile = new File("stats.txt");
        date = new Date();
        jLabel1 = new JLabel();
        this.setBackground(Color.black);

        initLeaderBoards();

        setBackLabel();
        resetLayout();

        //saveStats();
        try {
            loadStats();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error when reading the statsFile!", "Error", JOptionPane.ERROR_MESSAGE);
        }
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

        g2.drawString("Leaderboards", 50, 60);
        drawLeaderboard(g2);

        g2.dispose();
    }

    private void drawLeaderboard(Graphics2D g) {
        int top_offset = FRAME_HEIGHT/4;
        int lines_offset = FONT_SIZE + FONT_SIZE/2;
        int x_offset = FRAME_WIDTH/4;
        int numbers_offset = x_offset - g.getFontMetrics().stringWidth("9. ");
        int number10_offset = x_offset - g.getFontMetrics().stringWidth("10. ");
        int collumn1_offset = x_offset + g.getFontMetrics().stringWidth("AAAAAAAAAA");
        int collumn2_offset = collumn1_offset + g.getFontMetrics().stringWidth("999999");
        
        for (int i = 0; i < leaderboard.length; i++) {
            if (i != 9) {
                g.drawString(String.valueOf(i + 1) + ".", numbers_offset, lines_offset * i + top_offset);
            } else {
                g.drawString(String.valueOf(i + 1) + ".", number10_offset, lines_offset * i + top_offset);
            }
            g.drawString(leaderboard[i][0], x_offset, lines_offset * i + top_offset);
            g.drawString(leaderboard[i][1],collumn1_offset, lines_offset * i + top_offset);
            g.drawString(leaderboard[i][2],collumn2_offset, lines_offset * i + top_offset);
        }
    }

    public void addScore(String name, int score) {
        date = new Date();
        if (isNewHighscore(score)) {
            leaderboard[9][0] = name;
            leaderboard[9][1] = Integer.toString(score);
            leaderboard[9][2] = dateFormat.format(date);
            sortLeaderboards();
            repaint();
        }
        saveStats();
    }

    public boolean isNewHighscore(int score) {
        return (score >= Integer.parseInt(leaderboard[9][1]));
    }

    /**
     * Sort according to score (second collumn) then acc name (first column).
     */
    private void sortLeaderboards() {
        Arrays.sort(leaderboard, new Comparator<String[]>() {
            @Override
            public int compare(final String[] entry1, final String[] entry2) {
                if (entry1[1].compareTo(entry2[1]) == 0) {
                    return entry1[0].compareToIgnoreCase(entry2[0]);
                } else {
                    //Numbers need to be sorted in reverse, because they are sorted as a string
                    return entry1[1].compareToIgnoreCase(entry2[1]) * (-1);
                }
            }
        });
    }

    private void loadStats() throws IOException {
        String line;
        boolean fileFound = true;
        int nameScoreDate = 0; ///< Names = 0, Scores = 1, Dates = 2
        int j; ///< Line in leaderboard

        try {
            bfr = new BufferedReader(new FileReader(statsFile));
        } catch (FileNotFoundException ex) {
            //File not found, it will be created when saving the stats.
            fileFound = false;
        }

        if (fileFound) {
            for (int i = 0; i < (leaderboard.length * leaderboard[0].length); i++) {
                j = i % 10;
                if (i == leaderboard.length || i == leaderboard.length * 2) {
                    nameScoreDate++;
                }
                line = bfr.readLine();
                leaderboard[j][nameScoreDate] = line;
            }

            bfr.close();
        }

    }

    /**
     * Saves Leaderboard to the statsFile. Firstly writes all the names then all
     * the scores and then all the dates, every item at new line.
     */
    private void saveStats() {
        int nameScoreDate = 0; ///< Names = 0, Scores = 1, Dates = 2
        int j; ///< Line in leaderboard
        try {
            bfw = new BufferedWriter(new FileWriter(statsFile));
            for (int i = 0; i < (leaderboard.length * leaderboard[0].length); i++) {
                j = i % 10;
                if (i == leaderboard.length || i == leaderboard.length * 2) {
                    nameScoreDate++;
                }
                bfw.write(leaderboard[j][nameScoreDate]);
                bfw.newLine();
            }
            bfw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "File not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initLeaderBoards() {
        for (int i = 0; i < leaderboard.length; i++) {
            leaderboard[i][0] = "Player";
            leaderboard[i][1] = "0";
            leaderboard[i][2] = dateFormat.format(date);
        }
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
