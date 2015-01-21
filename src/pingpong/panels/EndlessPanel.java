package pingpong.panels;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import static pingpong.MainForm.FINAL_SCORE_PANEL;
import static pingpong.panels.CardsPanel.FONT_SIZE;
import static pingpong.panels.CardsPanel.FRAME_HEIGHT;
import static pingpong.panels.CardsPanel.FRAME_WIDTH;

/**
 *
 * @author Radek Bartyzal
 */
public class EndlessPanel extends GraphicsPanel {

    private final String pressEnterText = "Press enter to see your score";

    /*leaderboards variables start*/
    public static String NAME = "Darmy";
    private boolean newHighScore = false;
    public boolean nameNotSet = true;
    /*leaderboards variables end*/
    //private boolean showFinalScore = false;
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
                if (gamePaused) {
                    backToMenu();
                }
                if (hasSomebodyWon()) {
                    CardLayout cl = (CardLayout) (getParent().getLayout());
                    cl.show(getParent(), FINAL_SCORE_PANEL);
                    //drawFinalScore();
                    //repaint();
                }
            }
        });

        am.put(KeyEvent.VK_SPACE, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!gamePaused && !hasSomebodyWon()) {
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
        somebodyWon();
        g.setFont(new Font("Tahoma", Font.BOLD, FONT_SIZE * 2 + FONT_SIZE / 2));

        g.drawString("GAME OVER !!!", getStringLocation(g, "GAME OVER !!!", this.getWidth()), FRAME_HEIGHT / 3 - FONT_SIZE * 2);

        g.setColor(Color.GRAY);
        g.setFont(new Font("Tahoma", Font.BOLD, FONT_SIZE));

        g.drawString(pressEnterText, getStringLocation(g, pressEnterText, this.getWidth()), FRAME_HEIGHT / 2);
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
        g.setFont(new Font("Tahoma", Font.PLAIN, FONT_SIZE));
        g.setColor(Color.WHITE);
        int x = getStringLocation(g, "Score: 999 Lives: 999", this.getWidth());
        int wordLength = (int) g.getFontMetrics().getStringBounds("Score:", g).getWidth();
        int y = FONT_SIZE + FONT_SIZE / 2;
        g.drawString("Score:", x, y);
        g.drawString(String.valueOf(player1.endlessScore()), x + wordLength + FONT_SIZE / 2, y);
        g.drawString("Lives:", x + 2 * wordLength, y);
        g.drawString(String.valueOf(player1.getLives() - player2.getScore()), x + 3 * wordLength, y);
    }

    public void drawFinalScore() {
        Graphics g = this.getGraphics();
        
        /*
         if (nameNotSet) {
         frame = new InputFrame(this);
         frame.setVisible(true);
         frame.setLocation(this.getLocationOnScreen().x + this.getWidth() / 2 - frame.getWidth() / 2,
         this.getLocationOnScreen().y + this.getHeight() / 2 - frame.getHeight() / 2);
         }*/
        /*g.drawString("Your name:", 350, 350);
        g.drawString("Press enter to go back to menu.", 350, 400);
        drawNewHighScore(g);*/
    }

    public void drawNAME() {
        Graphics g = this.getGraphics();
        g.setFont(new Font("Tahoma", Font.PLAIN, FONT_SIZE));
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
        screenCleaned = false;
        newHighScore = false;
        nameNotSet = true;
        super.reset();
    }
}
