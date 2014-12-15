package pingpong.panels;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static pingpong.MainForm.MENU_PANEL;

/**
 *
 * @author Radek Bartyzal
 */
public class LeaderboardsPanel extends JPanel {

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private LeaderboardsDrawPanel drawPanel;

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

        initLeaderBoards();

        drawPanel = new LeaderboardsDrawPanel(100, 0);
        initLabel1();
        initLabel2();
        initLayout();

        //saveStats();
        try {
            loadStats();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error when reading the statsFile!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addScore(String name, int score) {
        date = new Date();
        if (isNewHighscore(score)) {
            leaderboard[9][0] = name;
            leaderboard[9][1] = Integer.toString(score);
            leaderboard[9][2] = dateFormat.format(date);
            sortLeaderboards();
            drawPanel.repaint();
        }
        saveStats();
    }
    
    public boolean isNewHighscore(int score){
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
                    return entry1[1].compareToIgnoreCase(entry2[1])*(-1);
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

    private void initLayout() {
        setBackground(Color.black);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(100, 50)));
        add(jLabel1);
        add(Box.createRigidArea(new Dimension(100, 20)));
        
        add(drawPanel);
        //drawPanel.repaint();
        //add(Box.createRigidArea(new Dimension(20, 0)));
        add(jLabel2);

    }

    private void initLabel1() {
        jLabel1 = new javax.swing.JLabel("Leaderboards");
        jLabel1.setAlignmentX(LEFT_ALIGNMENT);
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20));
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
    }

    private void initLabel2() {
        jLabel2 = new javax.swing.JLabel("Back");
        jLabel2.setAlignmentX(LEFT_ALIGNMENT);
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20));
        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2.setFont(new Font("Tahoma", Font.PLAIN, 20));
                CardLayout cl = (CardLayout) (getParent().getLayout());
                cl.show(getParent(), MENU_PANEL);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2.setFont(new Font("Tahoma", Font.BOLD, 22));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2.setFont(new Font("Tahoma", Font.PLAIN, 20));
            }
        });
    }

    public String[][] getLeaderboard() {
        return leaderboard;
    }

    

}
