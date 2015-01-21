package pingpong.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import static pingpong.panels.CardsPanel.FRAME_HEIGHT;
import static pingpong.panels.CardsPanel.FRAME_WIDTH;

/**
 *
 * @author Radek Bartyzal
 */
public class EndlessPanel extends GraphicsPanel {

    /*leaderboards variables start*/
    public static String NAME = "Darmy";
    private boolean newHighScore = false;
    public boolean nameNotSet = true;
    /*leaderboards variables end*/
    private boolean showFinalScore = false;
    private int tempBallReturned = 0;   //because of AITeleport()
    private boolean screenCleaned = false;

    private InputFrame frame;

    public EndlessPanel() {
        drawableList.add(ball);
        drawableList.add(paddle1);
        isEndless = true;
        init();
    }

    private void init() {
        setKeyBindings();
    }

    @Override
    protected void setKeyBindings() {
        super.setKeyBindings();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), KeyEvent.VK_ENTER);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), KeyEvent.VK_SPACE);

        am.put(KeyEvent.VK_ENTER, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (showFinalScore || gamePaused) {
                    backToMenu();
                }
                if (!showFinalScore && hasSomebodyWon()) {
                    showFinalScore = true;
                    drawFinalScore();
                    //repaint();
                }
            }
        });

        am.put(KeyEvent.VK_SPACE, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!gamePaused && !hasSomebodyWon() && !showFinalScore) {
                    startGame();
                }
            }
        });
    }

    @Override
    protected void mainTimer() {
        super.mainTimer();
        AIteleport();
    }

    /**
     * Different fixedSpeed than the one updating in Graphics Panel
     */
    @Override
    public void getOptions() {
        super.getOptions();
        for (Component p : getParent().getComponents()) {
            if (p instanceof OptionsPanel) {
                fixedSpeed = ((OptionsPanel) p).isEndlessFixedSpeed();
            }
        }
    }

    @Override
    protected void endGameTimer() {
        if (colorChanged == true) {
            gameOverColor = Color.BLUE;
            colorChanged = false;
        } else {
            gameOverColor = Color.RED;
            colorChanged = true;
        }

        Graphics g = this.getGraphics();
        if (showFinalScore) {
            if (newHighScore) {
                drawNewHighScore(g);
            }
        } else {
            drawSomebodyWon(g);
        }
    }

    @Override
    protected boolean hasSomebodyWon() {
        return player2.endlessWin();
    }

    public void submitNewScore() {
        for (Component p : getParent().getComponents()) {
            if (p instanceof LeaderboardsPanel) {
                ((LeaderboardsPanel) p).addScore(NAME, player1.endlessScore());
                newHighScore = ((LeaderboardsPanel) p).isNewHighscore(player1.endlessScore());
            }
        }
    }

    @Override
    protected void drawSomebodyWon(Graphics g) {
        g.setColor(gameOverColor);
        g.setFont(new Font("Tahoma", Font.BOLD, 50));
        g.drawString("GAME OVER !!!", 320, 230);

        g.setColor(Color.GRAY);
        g.setFont(new Font("Tahoma", Font.BOLD, 20));

        FontMetrics fontMetrics = g.getFontMetrics();
        int strL = fontMetrics.stringWidth("Press enter to see your score.");
        g.drawString("Press enter to see your score.", FRAME_WIDTH / 2 - strL / 2, FRAME_HEIGHT / 2 + 50);
    }

    /*Tesne po odrazu probehne vetev createTeleport() misto aktualizace tempBallReturned, proto tempB. +1*/
    /*Na zacatku, pred prvnim odrazem je vse 0 - tudiz nefunguje pojistka: tempBallReturned = 0; proto se
     prvni odraz preskoci pomoci: && tempBallReturned != 0 */
    private void AIteleport() {
        if (ball.getX() < 100 && ball.getVx() > 0) {
            if (tempBallReturned + 1 == player1.getBallReturned() && tempBallReturned != 0) {
                if (player2.useTeleport()) {
                    createTeleport();
                    tempBallReturned = 0;
                }
            }
        } else {
            tempBallReturned = player1.getBallReturned();
        }
    }

    @Override
    protected void drawScore(Graphics g) {
        g.setFont(new Font("Tahoma", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString("Score:", 400, 35);
        g.drawString("Lives:", 500, 35);
        g.drawString(String.valueOf(player1.endlessScore()), 470, 35);
        g.drawString(String.valueOf(player1.getLives() - player2.getScore()), 560, 35);
    }

    @Override
    protected void drawHints(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.PLAIN, 15));
        g.drawString("Press E to teleport", 410, 15);
        g.drawString("Esc - Pause", 200, 15);
        g.drawString("W, S - Move paddles", 600, 15);
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString("T:" + String.valueOf(player1.getTeleports()), 10, 440);
        g.drawString("T:" + String.valueOf(player2.getTeleports()), 940, 440);

        if (gamePaused) {
            g.setColor(Color.GRAY);
            g.setFont(new Font("Tahoma", Font.BOLD, 40));
            g.drawString("GAME PAUSED", 350, 150);
            g.setFont(new Font("Tahoma", Font.PLAIN, 20));
            g.drawString("Press enter to go back to menu.", 355, 250);
            g.drawString("Press escape to continue playing.", 355, 280);

        }

        if (!hasStarted) {
            g.setColor(Color.GRAY);
            g.setFont(new Font("Tahoma", Font.BOLD, 40));
            g.drawString("PRESS SPACE TO START", 250, 150);
        }
    }

    public void drawFinalScore() {
        Graphics g = this.getGraphics();
        //Clean screen behind final score
        //if (!screenCleaned) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
            
        //    screenCleaned = true;
        //}
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
        g.drawString(String.valueOf(player1.getPlusCount()), 410, 150);
        g.drawString(String.valueOf(player1.getMinusCount()), 410, 190);
        g.drawString(String.valueOf(player1.gettCount()), 410, 230);
        g.drawString(String.valueOf(player1.getBallReturned()), 410, 270);
        g.drawString(String.valueOf(player1.endlessScore()), 410, 310);
        
        if(nameNotSet){
        frame = new InputFrame(this);
        frame.setVisible(true);
        frame.setLocation(this.getLocationOnScreen().x + this.getWidth()/2 - frame.getWidth()/2, 
                this.getLocationOnScreen().y + this.getHeight()/2 - frame.getHeight()/2);}

        g.drawString("Your name:", 350, 350);
        g.drawString("Press enter to go back to menu.", 350, 400);
        drawNewHighScore(g);
    }

    public void drawNAME() {
        Graphics g = this.getGraphics();
        g.setFont(new Font("Tahoma", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString(NAME, 500, 350);
    }

    private void drawNewHighScore(Graphics g) {
        g.setFont(new Font("Tahoma", Font.PLAIN, 20));
        g.setColor(gameOverColor);
        g.drawString("NEW HIGHSCORE", 500, 310);
    }

    @Override
    protected void reset() {
        showFinalScore = false;
        screenCleaned = false;
        newHighScore = false;
        nameNotSet = true;
        super.reset();
    }
}
