package pingpong;

import java.awt.CardLayout;
import java.awt.Color;
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
import static pingpong.MainForm.FRAME_WIDTH;
import static pingpong.MainForm.MENU_PANEL;

/**
 *
 * @author Radek Bartyzal
 */
public class GraphicsPanel extends JPanel {

    protected boolean isEndless = false; ///< Whether the game mode is endless - for ball bouncing etc
    private boolean isTeleport = false;

    protected Ball ball = new Ball();
    protected Teleport teleport = new Teleport();
    protected Paddle paddle1 = new Paddle(10);
    protected Paddle paddle2 = new Paddle(FRAME_WIDTH - 50);
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

    //Main timer
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
    //endGame() blinking lights
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
            if (powerUpList.get(i).isDeleted) {
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
        ball.scorePoint(player1, player2, isEndless);
        ball.bounceOffPaddle(paddle1, player1);
        if (!isEndless) {
            ball.bounceOffPaddle(paddle2, player2);
        }
        ball.teleport(isTeleport, teleport);

        paddle1.lengthReturn();
        paddle2.lengthReturn();

        repaint();
    }

    protected void powerUpTimer() {
        int type = (int) (Math.round(Math.random() + 1));
        powerUpList.add(new PowerUp(type));
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

    protected void endGameTimer() {
        if (colorChanged == true) {
            gameOverColor = Color.BLUE;
            colorChanged = false;
        } else {
            gameOverColor = Color.RED;
            colorChanged = true;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(Color.BLACK);
        g.setColor(gameOverColor);

        drawLists(g);
        drawHints(g);
        drawScore(g);
        if (isTeleport) {
            drawTeleport(g);
        }
        if (!isEndless) {
            drawSomebodyWon(g);
            g.dispose(); //If paint() is overriden, g must be disposed in overriding method
        }
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
    }

    protected void drawSomebodyWon(Graphics g) {
        g.setColor(gameOverColor);
        if (player1.win()) {
            somebodyWon();
            g.drawString("Press enter to go back to menu.", 400, 250);
            g.setFont(new Font("Tahoma", Font.BOLD, 50));
            g.drawString("PLAYER 1 WON !!!", 250, 230);
            g.setFont(new Font("Tahoma", Font.BOLD, 20));
        }
        if (player2.win()) {
            somebodyWon();
            g.drawString("Press enter to go back to menu.", 400, 250);
            g.setFont(new Font("Tahoma", Font.BOLD, 50));
            g.drawString("PLAYER 2 WON !!!", 250, 230);
            g.setFont(new Font("Tahoma", Font.BOLD, 20));
        }
        g.setColor(Color.WHITE);
    }

    protected void createTeleport() {
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
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString(String.valueOf(player1.score), 420, 35);
        g.drawString(":", 452, 35);
        g.drawString(String.valueOf(player2.score), 480, 35);

    }

    protected void drawHints(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.PLAIN, 12));
        g.drawString("Press E,P to teleport", 410, 15);
        g.drawString("Esc - Pause", 200, 15);
        //g.drawString("Q, A - Modify speed", 200, 15);
        g.drawString("W, S, Up, Down - Move paddles", 600, 15);
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

        if (!hasStarted) {
            g.setColor(Color.GRAY);
            g.setFont(new Font("Tahoma", Font.BOLD, 40));
            g.drawString("PRESS SPACE TO START", 250, 150);
        }
    }

    protected void reset() {

        player1.reset();
        player2.reset();
        powerUpList.clear();
        colorChanged = false;
        hasStarted = false;
        gamePaused = false;

        powerUpTimer.start();
        ball.reset();
    }

    protected void backToMenu() {
        reset();
        CardLayout cl = (CardLayout) (getParent().getLayout());
        cl.show(getParent(), MENU_PANEL);
    }

    /**
     * Nested class, to be able to access internal varibles player1, paddle1 etc
     */
    public class Action extends AbstractAction {

        private int keyCode;

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
                    if (player2.teleports > 0) {
                        createTeleport();
                        player2.teleports -= 1;
                    }
                    break;
                case KeyEvent.VK_E:
                    if (player1.teleports > 0) {
                        createTeleport();
                        player1.teleports -= 1;
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
