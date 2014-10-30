package pingpong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Radek Bartyzal
 */
public class EndlessPanel extends GraphicsPanel {

    private boolean showFinalScore = false;
    private boolean newHighScore = false;
    /*leaderboards variables start*/
    public String[][] endlessArray = new String[11][3];
    public String[][] leaderboardsArray = new String[10][3];
    public String[][] finalArray = new String[10][3];
    private boolean leaderboardSorted = false;
    private StringBuilder sb = new StringBuilder();
    private String name = "Player";
    /*leaderboards variables end*/
    private int tempBallReturned = 0;   //because of AITeleport()

    public EndlessPanel() {
        drawableList.add(ball);
        drawableList.add(paddle1);
        endGameTimer.start();
    }

    @Override
    protected void mainTimer() {
        super.mainTimer();
        AIteleport();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (showFinalScore) {
            drawFinalScore(g);
        } else {
            drawSomebodyWon(g);
        }
        g.dispose();
    }

    /*public void sortLeaderboards() {
        while (!leaderboardSorted) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            boolean sorted = false;

            if (player1.endlessScore() > Integer.valueOf(leaderboardsArray[9][1])) {
                newHighScore = true;
            }

            for (int i = 0; i < finalArray.length; i++) {
                endlessArray[i][0] = leaderboardsArray[i][0];
                endlessArray[i][1] = leaderboardsArray[i][1];
                endlessArray[i][2] = leaderboardsArray[i][2];

            }

            endlessArray[10][0] = name;
            endlessArray[10][1] = String.valueOf(player1.endlessScore());
            endlessArray[10][2] = dateFormat.format(date);

            do {
                sorted = true;
                for (int i = 0; i < endlessArray.length - 1; i++) {
                    if (Integer.valueOf(endlessArray[i][1])
                            < Integer.valueOf(endlessArray[i + 1][1])) {
                        String temp1 = endlessArray[i][1];
                        endlessArray[i][1] = endlessArray[i + 1][1];
                        endlessArray[i + 1][1] = temp1;
                        String temp2 = endlessArray[i][0];
                        endlessArray[i][0] = endlessArray[i + 1][0];
                        endlessArray[i + 1][0] = temp2;
                        String temp3 = endlessArray[i][2];
                        endlessArray[i][2] = endlessArray[i + 1][2];
                        endlessArray[i + 1][2] = temp3;
                        sorted = false;

                    }
                }
            } while (sorted == false);

            for (int i = 0; i < finalArray.length; i++) {
                finalArray[i][0] = endlessArray[i][0];
                finalArray[i][1] = endlessArray[i][1];
                finalArray[i][2] = endlessArray[i][2];

            }
            leaderboardSorted = true;
        }
    }
*/
    @Override
    public void keyTyped(KeyEvent e) {
        if (sb.length() < 10) {
            sb.append(e.getKeyChar());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_ENTER:
                if (showFinalScore || gamePaused) {
                    closePanel = true;
                }
                if (!showFinalScore && hasSomebodyWon()) {
                    showFinalScore = true;
                }
                break;
            case KeyEvent.VK_BACK_SPACE:
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                break;
        }
    }

    @Override
    protected boolean hasSomebodyWon() {
        return player2.endlessWin();
    }

    @Override
    protected void drawSomebodyWon(Graphics g) {
        g.setColor(gameOverColor);
        if (hasSomebodyWon()) {
            somebodyWon();
            g.drawString("Press enter to see your score", 400, 250);
            g.setFont(new Font("Tahoma", Font.BOLD, 50));
            g.drawString("GAME OVER !!!", 320, 230);
            g.setFont(new Font("Tahoma", Font.BOLD, 20));
            //sortLeaderboards();
        }
        g.setColor(Color.WHITE);
    }

    /*Tesne po odrazu probehne vetev createTeleport() misto aktualizace tempBallReturned, proto tempB. +1*/
    /*Na zacatku, pred prvnim odrazem je vse 0 - tudiz nefunguje pojistka: tempBallReturned = 0; proto se
     prvni odraz preskoci pomoci: && tempBallReturned != 0 */
    private void AIteleport() {
        if (ball.x < 100 && ball.vx > 0) {
            if (tempBallReturned + 1 == player1.ballReturned && tempBallReturned != 0) {
                if (player2.teleports > 0) {
                    createTeleport();
                    player2.teleports -= 1;
                    tempBallReturned = 0;
                }
            }
        } else {
            tempBallReturned = player1.ballReturned;
        }
    }

    @Override
    protected void drawScore(Graphics g) {
        g.setFont(new Font("Tahoma", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString("Score:", 400, 35);
        g.drawString("Lives:", 500, 35);
        g.drawString(String.valueOf(player1.endlessScore()), 470, 35);
        g.drawString(String.valueOf(player1.getLives() - player2.score), 560, 35);
    }

    @Override
    protected void drawHints(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.PLAIN, 15));
        g.drawString("Press E to teleport", 410, 15);
        g.drawString("Esc - Pause", 200, 15);
        g.drawString("W, S - Move paddles", 600, 15);
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString("T:" + String.valueOf(player1.teleports), 10, 440);
        g.drawString("T:" + String.valueOf(player2.teleports), 940, 440);

        if (gamePaused) {
            g.setColor(Color.GRAY);
            g.setFont(new Font("Tahoma", Font.BOLD, 40));
            g.drawString("GAME PAUSED", 350, 150);
            g.setFont(new Font("Tahoma", Font.PLAIN, 20));
            g.drawString("Press enter to go back to menu.", 355, 250);
            g.drawString("Press escape to continue playing.", 355, 280);

        }

        if (startGame == 0) {
            g.setColor(Color.GRAY);
            g.setFont(new Font("Tahoma", Font.BOLD, 40));
            g.drawString("PRESS SPACE TO START", 250, 150);
        }
    }

    private void drawFinalScore(Graphics g) {
        //Clean screen behind final score
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        //Draw final score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString("FINAL SCORE", 350, 100);
        g.setFont(new Font("Tahoma", Font.PLAIN, 20));
        g.drawString("+", 350, 150);
        g.drawString("-", 350, 190);
        g.drawString("T", 350, 230);
        g.drawString("pings", 350, 270);
        g.drawString("Total", 350, 310);
        g.drawString("+ = +3 points", 500, 150);
        g.drawString("-  = - 3 points", 500, 190); /*mezery jsou tam naschval*/

        g.drawString("T = +3 points", 500, 230);
        g.drawString("1 ping = +1 point", 500, 270);
        g.drawString(String.valueOf(player1.plusCount), 410, 150);
        g.drawString(String.valueOf(player1.minusCount), 410, 190);
        g.drawString(String.valueOf(player1.tCount), 410, 230);
        g.drawString(String.valueOf(player1.ballReturned), 410, 270);
        g.drawString(String.valueOf(player1.endlessScore()), 410, 310);
        g.drawString("Enter your name:", 350, 350);
        name = sb.toString();
        g.drawString(sb.toString(), 500, 350);
        g.drawString("Press enter to go back to menu.", 350, 400);
        if (newHighScore) {
            g.setColor(gameOverColor);
            g.drawString("NEW HIGHSCORE", 500, 310);
        }
    }

    @Override
    protected void reset() {
        showFinalScore = false;
        super.reset();
    }
}
