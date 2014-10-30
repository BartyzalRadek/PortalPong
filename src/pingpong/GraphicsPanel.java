package pingpong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Radek Bartyzal
 */
public class GraphicsPanel extends JPanel implements KeyListener {

    private boolean isTeleport = false;
    public Ball ball = new Ball();
    public Teleport teleport = new Teleport();
    public Paddle paddle1 = new Paddle(10, 1, 10); //True speed of paddle is set at KeyPressed
    public Paddle paddle2 = new Paddle(950, 2, 10); //True speed of paddle is set at KeyPressed
    protected Player player1 = new Player();
    protected Player player2 = new Player();
    protected List<Drawable> drawableList = new ArrayList<Drawable>();
    protected List<PowerUp> powerUpList = new ArrayList<PowerUp>();

    protected Color gameOverColor = Color.WHITE; //because of drawSomebodyWon() - switching colors
    protected boolean colorChanged = false; //because of drawSomebodyWon()

    protected boolean gamePaused = false;
    protected boolean fixedSpeed = false;  //speed of ball is not increasing in time
    protected boolean closePanel = false; //True = this panel should be closed or made invisible
    protected boolean hasStarted = false; //True if the game has already started

    /*public GraphicsPanel() {
     drawableList.add(ball);
     drawableList.add(paddle1);
     drawableList.add(paddle2);
     powerUpList.add(t);
     timer3.start();
     timer4.start();
        
     }*/
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
            //p.score(paddle2, player2); Handled in children because of Endless
        }

        ball.move();
        ball.bounceOffWalls(true);
        ball.scorePoint(player1, player2, true);
        ball.bounceOffPaddle(paddle1, player1);
        //ball.bounceOffPaddle(paddle2, player2); Handled in children because of Endless
        ball.teleport(isTeleport, teleport);

        paddleSizeReturn(paddle1);
        paddleSizeReturn(paddle2);
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

        //g.dispose(); Handled in children
    }

    protected void paddleSizeReturn(Paddle paddle) {
        if (paddle.length != 100) {
            paddle.duration++;
            if (paddle.duration == 1000) {
                paddle.length = 100;
                paddle.duration = 0;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
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
            case KeyEvent.VK_ESCAPE:
                pauseGame();
                break;
            case KeyEvent.VK_SPACE:
                startGame();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    protected void pauseGame() {
        if (hasSomebodyWon()) {
            return;
        }
        if (gamePaused == false) {
            mainTimer.stop();
            powerUpTimer.stop();
            gamePaused = true;

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
        closePanel = false;
        hasStarted = false;
        gamePaused = false;

        powerUpTimer.start();
        ball.reset();
    }

    public boolean isClosePanel() {
        return closePanel;
    }

}
