package pingpong.panels;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import pingpong.BlackHole;
import static pingpong.MainForm.FINAL_SCORE_PANEL;
import static pingpong.panels.CardsPanel.FONT_SIZE;
import static pingpong.panels.CardsPanel.FRAME_HEIGHT;

/**
 *
 * @author Radek Bartyzal
 */
public class EndlessPanel extends GraphicsPanel {

    private boolean isBlackHole = false;
    protected BlackHole blackHole = new BlackHole();
    
    private final String pressEnterText = "Press enter to see your score";

    //private boolean showFinalScore = false;
    private int tempBallReturned = 0;   //because of AITeleport()

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
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), KeyEvent.VK_D);

        am.put(KeyEvent.VK_ENTER, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (gamePaused) {
                    backToMenu();
                }
                if (hasSomebodyWon()) {
                    exportFinalScore();
                    reset();
                    showFinalScore();
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
        
        am.put(KeyEvent.VK_D, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                player1.sacrificeT();
            }
        });
    }

    @Override
    protected void mainTimer() {
        super.mainTimer();
        AIteleport();
    }
    
    @Override
    protected void powerUpTimer() {
        super.powerUpTimer();
        
        player2.catchPowerUp((int) Math.round(Math.random()*2)); //Add one Teleport to the AI
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
        return (player1.getLives() - player2.getScore()) <=0;
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

    private void showFinalScore() {
        CardLayout cl = (CardLayout) (getParent().getLayout());
        cl.show(getParent(), FINAL_SCORE_PANEL);
        
        for(Component p: this.getParent().getComponents()){
            if (p instanceof FinalScorePanel){
                ((FinalScorePanel) (p)).showInputFrame();
            }
        }
    }
    
    private void exportFinalScore(){
        int[] finalScore = new int[5];
        finalScore[0] = player1.getPlusCount();
        finalScore[1] = player1.getMinusCount();
        finalScore[2] = player1.gettCount();
        finalScore[3] = player1.getBallReturned();
        finalScore[4] = player1.endlessScore();
        
        for (Component p : getParent().getComponents()) {
            if (p instanceof FinalScorePanel) {
                ((FinalScorePanel) p).setFinalScore(finalScore);
            }
        }
    }
    
}
