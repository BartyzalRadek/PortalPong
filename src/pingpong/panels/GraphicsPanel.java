package pingpong.panels;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import pingpong.AbleToGetOptions;
import pingpong.AbleToResizeGUI;
import pingpong.Ball;
import pingpong.Drawable;
import pingpong.Paddle;
import pingpong.Player;
import pingpong.PowerUp;
import pingpong.Teleport;
import static pingpong.MainForm.MENU_PANEL;
import static pingpong.panels.CardsPanel.FONT_SIZE;
import static pingpong.panels.CardsPanel.FRAME_HEIGHT;
import static pingpong.panels.CardsPanel.FRAME_WIDTH;

/**
 *
 * @author Radek Bartyzal
 */
public class GraphicsPanel extends JPanel implements AbleToGetOptions, AbleToResizeGUI {

    protected boolean isEndless = false; ///< Whether the game mode is endless - for ball bouncing etc
    private boolean isTeleport = false;

    private final String gamePausedText = "GAME PAUSED";
    private final String pressEnterText = "Press enter to go back to menu.";
    private final String pressEscapeText = "Press escape to continue playing.";
    private final String pressSpaceText = "PRESS SPACE TO START";

    protected Ball ball = new Ball();
    protected Teleport teleport = new Teleport();
    protected Paddle paddle1 = new Paddle(true);
    protected Paddle paddle2 = new Paddle(false);
    protected Player player1 = new Player();
    protected Player player2 = new Player();
    protected List<Drawable> drawableList = new ArrayList<Drawable>();
    protected List<PowerUp> powerUpList = new ArrayList<PowerUp>();

    protected Color gameOverColor = Color.WHITE; ///< because of drawSomebodyWon() - switching colors
    protected boolean colorChanged = false; ///< because of drawSomebodyWon()

    protected boolean gamePaused = false;
    protected boolean hasStarted = false; ///< True if the game has already started
    protected boolean fixedSpeed = false;  ///< speed of ball is not increasing in time

    protected InputMap im = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
    protected ActionMap am = this.getActionMap();

    protected void setKeyBindings() {
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), KeyEvent.VK_SPACE);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), KeyEvent.VK_W);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), KeyEvent.VK_S);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), KeyEvent.VK_E);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), KeyEvent.VK_ESCAPE);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), KeyEvent.VK_A);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0), KeyEvent.VK_Q);

        am.put(KeyEvent.VK_SPACE, new Action(KeyEvent.VK_SPACE));
        am.put(KeyEvent.VK_W, new Action(KeyEvent.VK_W));
        am.put(KeyEvent.VK_S, new Action(KeyEvent.VK_S));
        am.put(KeyEvent.VK_E, new Action(KeyEvent.VK_E));
        am.put(KeyEvent.VK_ESCAPE, new Action(KeyEvent.VK_ESCAPE));
        am.put(KeyEvent.VK_A, new Action(KeyEvent.VK_A));
        am.put(KeyEvent.VK_Q, new Action(KeyEvent.VK_Q));
    }

    //Moving the ball, powerUps, calculating collisions etc.
    protected Timer mainTimer = new Timer(20, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainTimer();
        }
    });
    //Creating new power ups + increasing ball speed
    protected Timer powerUpTimer = new Timer(8000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            powerUpTimer();
        }
    });
    //endGame() blinking game over text
    protected Timer endGameTimer = new Timer(400, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            endGameTimer();
        }
    });

    protected void mainTimer() {
        if (hasSomebodyWon()) {
            somebodyWon();
        }

        for (int i = 0; i < powerUpList.size(); i++) {
            if (powerUpList.get(i).isExpired()) {
                powerUpList.remove(i);
            }
        }

        for (PowerUp p : powerUpList) {
            p.move();
            p.bounceOffWalls();
            p.score(paddle1, player1);
            if (!isEndless) {
                p.score(paddle2, player2);
            }
        }

        ball.move();
        ball.bounceOffWalls(isEndless);
        ball.bounceOffPaddle(paddle1, player1);
        ball.scorePoint(player1, player2, isEndless);
        if (!isEndless) {
            ball.bounceOffPaddle(paddle2, player2);
        }
        ball.teleport(isTeleport, teleport);

        paddle1.lengthReturn();
        paddle2.lengthReturn();

        repaint();
    }

    protected void powerUpTimer() {
        int type = (int) (Math.round(Math.random() * 2));
        powerUpList.add(new PowerUp(type));
        if (fixedSpeed == false) {
            ball.increaseSpeed();
        }
    }

    protected void endGameTimer() {
        if (colorChanged == true) {
            gameOverColor = Color.BLUE;
            colorChanged = false;
        } else {
            gameOverColor = Color.RED;
            colorChanged = true;
        }
        Graphics g = this.getGraphics();
        drawSomebodyWon(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        g.setColor(gameOverColor);

        drawLists(g);
        drawHints(g);
        drawScore(g);
        if (isTeleport) {
            drawTeleport(g);
        }

        //g.dispose(); //If paint() is overriden, g must be disposed in overriding method
    }

    protected void pauseGame() {
        if (gamePaused == false) {
            mainTimer.stop();
            powerUpTimer.stop();
            gamePaused = true;
            repaint();

        } else {
            startGame();
        }
    }

    @Override
    public void getOptions() {
        for (Component p : getParent().getComponents()) {
            if (p instanceof OptionsPanel) {
                fixedSpeed = ((OptionsPanel) p).isFixedSpeed();
                player1.setLives(((OptionsPanel) p).getLives());
                player2.setLives(((OptionsPanel) p).getLives());
                player1.setWinningScore(((OptionsPanel) p).getWinningScore());
                player2.setWinningScore(((OptionsPanel) p).getWinningScore());
            }
        }

    }

    protected void startGame() {
        mainTimer.start();
        powerUpTimer.start();
        gamePaused = false;
        hasStarted = true;
    }

    protected boolean hasSomebodyWon() {
        return player1.win() || player2.win();
    }

    protected void somebodyWon() {
        mainTimer.stop();
        powerUpTimer.stop();
        endGameTimer.start();
    }

    protected void drawSomebodyWon(Graphics g) {
        g.setColor(gameOverColor);
        somebodyWon();
        g.setFont(new Font("Tahoma", Font.BOLD, FONT_SIZE * 2 + FONT_SIZE / 2));

        if (player1.win()) {
            g.drawString("PLAYER 1 WON !!!", getStringLocation(g, "PLAYER 1 WON !!!", this.getWidth()), FRAME_HEIGHT / 3 - FONT_SIZE * 2);
        }
        if (player2.win()) {
            g.drawString("PLAYER 2 WON !!!", getStringLocation(g, "PLAYER 2 WON !!!", this.getWidth()), FRAME_HEIGHT / 3 - FONT_SIZE * 2);
        }

        g.setColor(Color.GRAY);
        g.setFont(new Font("Tahoma", Font.BOLD, FONT_SIZE));

        g.drawString(pressEnterText, getStringLocation(g, pressEnterText, this.getWidth()), FRAME_HEIGHT / 2);

    }

    protected void createTeleport() {
        if (isTeleport) {
            teleport.resetDuration();
        }
        isTeleport = true;
        teleport.setLocations(ball);
    }

    protected void drawTeleport(Graphics g) {
        teleport.draw(g);

        teleport.extendDuration(1);
        if (teleport.isExpired()) {
            isTeleport = false;
            teleport.resetDuration();
        }
    }

    protected void drawLists(Graphics g) {

        for (Drawable d : drawableList) {
            d.draw(g);
        }
        for (PowerUp p : powerUpList) {
            p.draw(g);
        }
    }

    protected void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.BOLD, FONT_SIZE));
        g.drawString(String.valueOf(player1.getScore()), FRAME_WIDTH / 2 - 4 * FONT_SIZE, FONT_SIZE + FONT_SIZE / 2);
        g.drawString(":", FRAME_WIDTH / 2 - 2 * FONT_SIZE, FONT_SIZE + FONT_SIZE / 2);
        g.drawString(String.valueOf(player2.getScore()), FRAME_WIDTH / 2 - 0 * FONT_SIZE, FONT_SIZE + FONT_SIZE / 2);

    }

    protected void drawHints(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.PLAIN, FONT_SIZE));
        g.drawString("T:" + String.valueOf(player1.getTeleports()), FONT_SIZE * 2, FRAME_HEIGHT - 30);
        g.drawString("T:" + String.valueOf(player2.getTeleports()), FRAME_WIDTH - (FONT_SIZE * 4), FRAME_HEIGHT - 30);

        if (gamePaused) {
            g.setColor(Color.GRAY);

            g.setFont(new Font("Tahoma", Font.BOLD, FONT_SIZE * 2));
            g.drawString(gamePausedText, getStringLocation(g, gamePausedText, this.getWidth()), FRAME_HEIGHT / 3 - FONT_SIZE * 2);
            g.setFont(new Font("Tahoma", Font.PLAIN, FONT_SIZE));
            g.drawString(pressEnterText, getStringLocation(g, pressEnterText, this.getWidth()), FRAME_HEIGHT / 2);
            g.drawString(pressEscapeText, getStringLocation(g, pressEnterText, this.getWidth()), FRAME_HEIGHT / 2 + 2 * FONT_SIZE);
        }

        if (!hasStarted) {
            g.setColor(Color.GRAY);
            g.setFont(new Font("Tahoma", Font.BOLD, FONT_SIZE * 2));
            g.drawString(pressSpaceText, getStringLocation(g, pressSpaceText, this.getWidth()), FRAME_HEIGHT / 3 - FONT_SIZE * 2);
        }
    }

    protected int getStringLocation(Graphics g, String s, int widthOfComponent) {
        int strlen = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
        return (widthOfComponent / 2) - (strlen / 2);
    }

    protected void reset() {
        player1.reset();
        player2.reset();
        ball.reset();
        paddle1.reset();
        paddle2.reset();
        powerUpList.clear();
        colorChanged = false;
        hasStarted = false;
        gamePaused = false;

        mainTimer.stop();
        powerUpTimer.stop();
        endGameTimer.stop();
    }

    protected void backToMenu() {
        reset();
        CardLayout cl = (CardLayout) (getParent().getLayout());
        cl.show(getParent(), MENU_PANEL);
    }

    @Override
    public void resizeGUI() {
        paddle1.resize();
        paddle2.resize();
        ball.resize();
        teleport.resize();

        repaint();

    }

    /**
     * Nested class, to be able to access internal variables player1, paddle1
     * etc
     */
    public class Action extends AbstractAction {

        private final int keyCode;

        public Action(int keyCode) {
            this.keyCode = keyCode;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            switch (keyCode) {
                case KeyEvent.VK_W:
                    paddle1.moveUp();

                    break;
                case KeyEvent.VK_S:
                    paddle1.moveDown();

                    break;
                case KeyEvent.VK_UP:
                    paddle2.moveUp();
                    break;
                case KeyEvent.VK_DOWN:
                    paddle2.moveDown();
                    break;
                case KeyEvent.VK_P:
                    if (player2.useTeleport()) {
                        createTeleport();
                    }
                    break;
                case KeyEvent.VK_E:
                    if (player1.useTeleport()) {
                        createTeleport();
                    }

                    break;
                case KeyEvent.VK_Q:
                case KeyEvent.VK_A:
                    ball.increaseSpeed();
                    break;

                case KeyEvent.VK_ENTER:
                    if (hasSomebodyWon() || gamePaused) {
                        backToMenu();
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    if (!hasSomebodyWon() && hasStarted) {
                        pauseGame();
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    if (!gamePaused) {
                        startGame();
                    }
                    break;
            }
        }

    }

}
