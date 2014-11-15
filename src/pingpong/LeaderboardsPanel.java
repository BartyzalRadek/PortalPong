package pingpong;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static pingpong.MainForm.FRAME_HEIGHT;
import static pingpong.MainForm.FRAME_WIDTH;
import static pingpong.MainForm.MENU_PANEL;

/**
 *
 * @author Leaderboards.java
 */
public class LeaderboardsPanel extends JPanel {

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private  LeaderboardsDrawPanel drawPanel;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private Date date;
    private String[][] leaderboard = new String[10][3];
    private BufferedReader bfr;
    private BufferedWriter bfw;
    /*private File names;
     private File scores;
     private File dates;*/
    private File statsFile;

    public LeaderboardsPanel() {
        init();

        /*names = new File("names.txt");
         scores = new File("scores.txt");
         dates = new File("dates.txt");

         try {
         updateLeaderboards(names, 0);
         updateLeaderboards(scores, 1);
         updateLeaderboards(dates, 2);
         } catch (Exception ex) {
         /*Nic nevyskakuje, abychom si nevystrasili uzivatele!*/
        /*JOptionPane.showMessageDialog(this, "Saves not found.\nCreating files...");*/
        /*} finally {
         updateFiles(names, 0);
         updateFiles(scores, 1);
         updateFiles(dates, 2);
         }*/
    }

    private void init() {
        statsFile = new File("stats.txt");
        date = new Date();
        
        for (int i = 0; i < leaderboard.length; i++) {
            leaderboard[i][0] = "Player";
            leaderboard[i][1] = "0";
            leaderboard[i][2] = dateFormat.format(date);
        }
        
        drawPanel = new LeaderboardsDrawPanel(leaderboard, 100,0);
        //drawPanel.setLocation(100, 0);
        initLabel1();
        initLabel2();
        initLayout();

        saveStats();

        //repaint();

        /*try {
         loadStats();
         } catch (IOException ex) {
         //IO ex while reading line of stats file
         }*/
    }

    
    private void loadStats() throws IOException {
        String line;
        String[] words;
        boolean fileFound = true;

        try {
            bfr = new BufferedReader(new FileReader(statsFile));
        } catch (FileNotFoundException ex) {
            //File not found, it will be created when saving the stats.
            fileFound = false;
        }

        if (fileFound) {
            for (int i = 0; i < leaderboard.length; i++) {
                line = bfr.readLine();
                words = line.split(" ");
                leaderboard[i] = words;
            }

            bfr.close();
        }

    }
    
    /**
     * Saves Leaderboard to the statsFile.
     * Firstly writes all the names then all the scores and then all the dates,
     * every item at new line.
     */
    private void saveStats(){
        int nameScoreDate = 0; ///< Names = 0, Scores = 1, Dates = 2
        int j; ///< Line in leaderboard
        try {
            bfw = new BufferedWriter(new FileWriter(statsFile));
            for (int i = 0; i < (leaderboard.length * leaderboard[0].length); i++) {
                j = i%10;
                if(i==leaderboard.length || i==leaderboard.length*2) nameScoreDate++;
                bfw.write(leaderboard[j][nameScoreDate]);
                bfw.newLine();
            }
            bfw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "File not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    /*Nacte data z leaderboard[][] pole a zapise je do souboru
     nameScoreDate - name=0, score=1, date=2*/
    private void updateFiles(File file, int nameScoreDate) {
        try {
            bfw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < leaderboard.length; i++) {
                bfw.write(leaderboard[i][nameScoreDate]);
                bfw.newLine();
            }
            bfw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "File not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initLayout() {
        setBackground(Color.black);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(20, 50)));
        add(jLabel1);
        add(Box.createRigidArea(new Dimension(20, 0)));
        add(drawPanel);
        //drawPanel.repaint();
        add(Box.createRigidArea(new Dimension(20, 0)));
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

    
}
