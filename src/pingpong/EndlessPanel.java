/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Darmy
 */
public class EndlessPanel extends JPanel implements KeyListener {

    public boolean isTeleport = false;
    public PowerUp t = new PowerUp(0, 3);
    public Ball ball = new Ball();
    public Teleport teleport = new Teleport();
    public Paddle paddle1 = new Paddle(10, 1, 10); //skutecna rychlost palky rizene clovekem je nastavena u KeyPressed
    public Player player1 = new Player();
    public Player player2 = new Player();   //computer
    private List<Drawable> drawableList = new ArrayList<Drawable>();
    private List<PowerUp> powerUpList = new ArrayList<PowerUp>();
    private Color color = Color.WHITE; //kvuli endGame()
    private boolean colorChanged = false; //kvuli endGame()
    public int endGame = 0;    //kvuli endGame()
    private boolean gamePaused = false;
    public boolean fixedSpeed = true; /*speed of ball is not increasing in time*/

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
    private int tempBallReturned = 0;   //kvuli AITeleport()
    private int startGame = 0;

    public EndlessPanel() {
        drawableList.add(ball);
        drawableList.add(paddle1);
        powerUpList.add(t);
        timer3.start();
        timer4.start();
    }
    //Hlavni Timer
    Timer timer1 = new Timer(20, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < powerUpList.size(); i++) {
                if (powerUpList.get(i).isDeleted) {
                    powerUpList.remove(i);
                }
            }

            for (int i = 0; i < powerUpList.size(); i++) {
                powerUpList.get(i).move();
                powerUpList.get(i).bounceOffWalls();
                powerUpList.get(i).score(paddle1, player1);
            }

            ball.move();
            ball.bounceOffWalls(false);
            ball.scorePoint(player1, player2, false);
            ball.bounceOffPaddle(paddle1, player1);
            ball.teleport(isTeleport, teleport);

            AIteleport();

        }
    });
    //Creating new power ups + increasing ball speed
    Timer timer2 = new Timer(8000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            int type = (int) (Math.round(Math.random() + 1));
            powerUpList.add(new PowerUp(type, 0));
            if (fixedSpeed == false) {
                if (ball.vx > 0) {
                    ball.vx++;
                } else {
                    ball.vx--;
                }
                if (ball.vy > 0) {
                    ball.vy++;
                } else {
                    ball.vy--;
                }

            }
        }
    });
    //endGame() blikajici napis + startGame
    Timer timer3 = new Timer(400, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (startGame == 1) {
                if (!timer1.isRunning()) {
                    timer1.start();
                    startGame = 2;
                }

            }

            if (colorChanged == true) {
                color = Color.BLUE;
                colorChanged = false;
            } else {
                color = Color.RED;
                colorChanged = true;
            }
        }
    });
    //Hotfix pro zaseknuty micek v X/Y ose
    Timer timer4 = new Timer(1000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (ball.getChangeVx() > 5 || ball.getChangeVy() > 5) {
                ball.toCenter();
            }
            ball.setChangeVx(0);
            ball.setChangeVy(0);
        }
    });

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(Color.BLACK);

        endGame(g);
        drawFinalScore(g);

        drawLists(g);
        drawTeleport(g);

        paddleSizeReturn();

        g.dispose();
    }

    public void sortLeaderboards() {
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

    private void paddleSizeReturn() {
        if (paddle1.length != 100) {
            paddle1.duration++;
            if (paddle1.duration == 1000) {
                paddle1.length = 100;
                paddle1.duration = 0;
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (sb.length() < 10) {
            sb.append(e.getKeyChar());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        switch (keyCode) {

            case KeyEvent.VK_W:
                if (paddle1.y > 0) {
                    paddle1.y -= 20;
                }

                break;
            case KeyEvent.VK_S:
                if ((paddle1.y + paddle1.length) < this.getHeight()) {
                    paddle1.y += 20;
                }

                break;
            case KeyEvent.VK_E:
                if (t.numberA > 0) {
                    createTeleport();
                    t.numberA -= 1;
                }

                break;

            case KeyEvent.VK_Q:
                ball.vx += 1;
                ball.vy += 1;
                break;
            case KeyEvent.VK_A:
                ball.vx -= 1;
                ball.vy -= 1;
                break;
            case KeyEvent.VK_ENTER:
                switch (endGame) {
                    case 1:
                        showFinalScore = true;
                        break;
                    case 2:
                        endGame = 3;
                        break;
                }

                if (gamePaused) {
                    endGame = 3;
                }
                break;
            case KeyEvent.VK_ESCAPE:
                pauseGame();
                break;
            case KeyEvent.VK_SPACE:
                startGame = 1;
                break;
            case KeyEvent.VK_BACK_SPACE:
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void pauseGame() {
        if (gamePaused == false) {
            timer1.stop();
            timer2.stop();
            gamePaused = true;

        } else {
            timer1.start();
            timer2.start();
            gamePaused = false;
        }

    }

    private void endGame(Graphics g) {
        g.setColor(color);
        if (player1.endlessWin() && showFinalScore == false) {
            endGame = 1;
            g.drawString("What have you done?", 400, 250);
            g.setFont(new Font("Tahoma", Font.BOLD, 50));
            g.drawString("THIS SHOULD NOT HAVE HAPPENED !!!", 250, 230);
            timer1.stop();
            timer2.stop();
            g.setFont(new Font("Tahoma", Font.BOLD, 20));
            sortLeaderboards();
        }
        if (player2.endlessWin() && showFinalScore == false) {
            endGame = 1;
            g.drawString("Press enter to see your score", 400, 250);
            g.setFont(new Font("Tahoma", Font.BOLD, 50));
            g.drawString("GAME OVER !!!", 320, 230);
            timer1.stop();
            timer2.stop();
            g.setFont(new Font("Tahoma", Font.BOLD, 20));
            sortLeaderboards();
        }
        g.setColor(Color.WHITE);
    }

    /*Tesne po odrazu probehne vetev createTeleport() misto aktualizace tempBallReturned, proto tempB. +1*/
    /*Na zacatku, pred prvnim odrazem je vse 0 - tudiz nefunguje pojistka: tempBallReturned = 0; proto se
    prvni odraz preskoci pomoci: && tempBallReturned != 0 */
    private void AIteleport() {
        if (ball.x < 100 && ball.vx > 0) {
            if (tempBallReturned + 1 == player1.ballReturned && tempBallReturned != 0) {
                if (t.numberB > 0) {
                    createTeleport();
                    t.numberB -= 1;
                    tempBallReturned = 0;
                }
            }
        } else {
            tempBallReturned = player1.ballReturned;
        }
    }

    private void createTeleport() {
        isTeleport = true;
        teleport.setVariables(ball);
    }

    private void drawTeleport(Graphics g) {
        if (isTeleport == true) {
            teleport.draw(g);

            teleport.duration++;
            if (teleport.duration == 40) {
                isTeleport = false;
                teleport.duration = 0;
            }
        }
    }

    private void drawLists(Graphics g) {
        if (!showFinalScore) {
            for (int i = 0; i < drawableList.size(); i++) {
                drawableList.get(i).draw(g);

            }
            for (int i = 0; i < powerUpList.size(); i++) {
                powerUpList.get(i).draw(g);
            }
            drawHints(g);
            drawScore(g);
        }
    }

    private void drawScore(Graphics g) {
        g.setFont(new Font("Tahoma", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString("Score:", 400, 35);
        g.drawString("Lives:", 500, 35);
        g.drawString(String.valueOf(player1.endlessScore()), 470, 35);
        g.drawString(String.valueOf(player1.lives - player2.score), 560, 35);
    }

    private void drawHints(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.PLAIN, 15));
        g.drawString("Press E to teleport", 410, 15);
        g.drawString("Esc - Pause", 200, 15);
        g.drawString("W, S - Move paddles", 600, 15);
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString("T:" + String.valueOf(t.numberA), 10, 440);
        g.drawString("T:" + String.valueOf(t.numberB), 940, 440);

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
        if (showFinalScore) {
            g.setFont(new Font("Tahoma", Font.BOLD, 20));
            g.drawString("FINAL SCORE", 350, 100);
            g.setFont(new Font("Tahoma", Font.PLAIN, 20));
            g.setColor(Color.WHITE);
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
                g.setColor(color);
                g.drawString("NEW HIGHSCORE", 500, 310);
            }
            endGame = 2;
        }
    }

    public void reset() {
        showFinalScore = false;
        player1.reset();
        player2.reset();
        powerUpList.clear();
        powerUpList.add(t);
        colorChanged = false;
        endGame = 0;
        startGame = 0;
        gamePaused = false;

        timer2.start();
        ball.reset();
    }
}
