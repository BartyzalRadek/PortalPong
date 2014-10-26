/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import java.awt.Color;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Darmy
 */
public class LeaderboardsPanel extends JPanel {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private Date date;
    private String[][] leaderboard = new String[10][3];
    private BufferedReader bfr;
    private BufferedWriter bfw;
    private File names;
    private File scores;
    private File dates;

    public LeaderboardsPanel() {
        date = new Date();
        names = new File("names.txt");
        scores = new File("scores.txt");
        dates = new File("dates.txt");
        for (int i = 0; i < leaderboard.length; i++) {
            leaderboard[i][0] = "Player";
            leaderboard[i][1] = "0";
            leaderboard[i][2] = dateFormat.format(date);
        }

        try {
            updateLeaderboards(names, 0);
            updateLeaderboards(scores, 1);
            updateLeaderboards(dates, 2);
        } catch (Exception ex) {
            /*Nic nevyskakuje, abychom si nevystrasili uzivatele!*/
            /*JOptionPane.showMessageDialog(this, "Saves not found.\nCreating files...");*/
        } finally {
            updateFiles(names, 0);
            updateFiles(scores, 1);
            updateFiles(dates, 2);
        }

    }

    private void update() {
        updateFiles(names, 0);
        updateFiles(scores, 1);
        updateFiles(dates, 2);
    }
    /*Nacte data z names.txt, score.txt a nahrne je do leaderboard[][] pole
    nameScoreDate - name=0, score=1, date=2*/

    private void updateLeaderboards(File file, int nameScoreDate) throws IOException {
        String line = "";

        int i = 0;
        bfr = new BufferedReader(new FileReader(file));
        while ((line = bfr.readLine()) != null) {
            leaderboard[i][nameScoreDate] = line;
            i++;
        }
        bfr.close();
        i = 0;

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

    @Override
    public void paint(Graphics g) {
        g.setFont(new Font("Tahoma", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        

        update();

        for (int i = 0; i < leaderboard.length; i++) {
            if (i != 9) {
                g.drawString(String.valueOf(i + 1) + ".", 50, 30 * i + 80);
            } else {
                g.drawString(String.valueOf(i + 1) + ".", 40, 30 * i + 80);
            }
            g.drawString(leaderboard[i][0], 80, 30 * i + 80);
            g.drawString(leaderboard[i][1], 180, 30 * i + 80);
            g.drawString(leaderboard[i][2], 250, 30 * i + 80);
        }
    }
    
    public String[][] getLeaderboard() {
        return leaderboard;
    }
    
    public void setLeaderboard(String[][] finalArray) {
        for (int i = 0; i < finalArray.length; i++) {
            leaderboard[i][0] = finalArray[i][0];
            leaderboard[i][1] = finalArray[i][1];
            leaderboard[i][2] = finalArray[i][2];
            
            
        }
    }
}
