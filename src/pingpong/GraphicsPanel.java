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

    public boolean isTeleport = false;
    public PowerUp t = new PowerUp(0, 3);
    public Ball ball = new Ball();
    public Teleport teleport = new Teleport();
    public Paddle paddle1 = new Paddle(10, 1, 10); //skutecna rychlost palky rizene clovekem je nastavena u KeyPressed
    public Paddle paddle2 = new Paddle(950, 2, 10); //skutecna rychlost palky rizene clovekem je nastavena u KeyPressed
    public Player player1 = new Player();
    public Player player2 = new Player();
    protected List<Drawable> drawableList = new ArrayList<Drawable>();
    protected List<PowerUp> powerUpList = new ArrayList<PowerUp>();
    protected Color color = Color.WHITE; //kvuli endGame()
    protected boolean colorChanged = false; //kvuli endGame()
    protected boolean gamePaused = false;
    protected boolean fixedSpeed = false;   /*speed of ball is not increasing in time*/
    protected boolean closePanel = false; //True = this panel should be closed or made invisible

    protected int startGame = 0;

    /*public GraphicsPanel() {
     drawableList.add(ball);
     drawableList.add(paddle1);
     drawableList.add(paddle2);
     powerUpList.add(t);
     timer3.start();
     timer4.start();
        
     }*/
    //Hlavni timer
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
    //endGame() blikajici napis + startGame
    protected Timer endGameTimer = new Timer(400, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            endGameTimer();
        }
    });

    protected void mainTimer() {
        if(hasSomebodyWon()) somebodyWon();
        
        for (int i = 0; i < powerUpList.size(); i++) {
            if (powerUpList.get(i).isDeleted) {
                powerUpList.remove(i);
            }
        }

        for (int i = 0; i < powerUpList.size(); i++) {
            powerUpList.get(i).move();
            powerUpList.get(i).bounceOffWalls();
            powerUpList.get(i).score(paddle1, player1);
            //powerUpList.get(i).score(paddle2, player2);
        }

        ball.move();
        ball.bounceOffWalls(true);
        ball.scorePoint(player1, player2, true);
        ball.bounceOffPaddle(paddle1, player1);
        //ball.bounceOffPaddle(paddle2, player2);
        ball.teleport(isTeleport, teleport);
    }

    protected void powerUpTimer() {
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

    protected void endGameTimer() {
        if (startGame == 1) {
            if (!mainTimer.isRunning()) {
                mainTimer.start();
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

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(Color.BLACK);
        g.setColor(color);

        //drawSomebodyWon(g);

        drawLists(g);
        drawTeleport(g);
        drawHints(g);
        drawScore(g);

        paddleSizeReturn();

        //g.dispose();
    }

    protected void paddleSizeReturn() {
        if (paddle1.length != 100) {
            paddle1.duration++;
            if (paddle1.duration == 1000) {
                paddle1.length = 100;
                paddle1.duration = 0;
            }
        }
        if (paddle2.length != 100) {
            paddle2.duration++;
            if (paddle2.duration == 1000) {
                paddle2.length = 100;
                paddle2.duration = 0;
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
            case KeyEvent.VK_ESCAPE:
                pauseGame();
                break;
            case KeyEvent.VK_SPACE:
                startGame = 1;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    protected void pauseGame() {
        if(hasSomebodyWon()) return;
        if (gamePaused == false) {
            mainTimer.stop();
            powerUpTimer.stop();
            gamePaused = true;

        } else {
            mainTimer.start();
            powerUpTimer.start();
            gamePaused = false;
        }

    }

    protected boolean hasSomebodyWon() {
        if (player1.win() || player2.win()) {
            return true;
        }
        return false;
    }

    protected void somebodyWon() {
        mainTimer.stop();
        powerUpTimer.stop();
    }

    protected void drawSomebodyWon(Graphics g) {
        g.setColor(color);
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
        teleport.setVariables(ball);
    }

    protected void drawTeleport(Graphics g) {
        if (isTeleport == true) {
            teleport.draw(g);

            teleport.duration++;
            if (teleport.duration == 40) {
                isTeleport = false;
                teleport.duration = 0;
            }
        }
    }

    protected void drawLists(Graphics g) {

        for (int i = 0; i < drawableList.size(); i++) {
            drawableList.get(i).draw(g);

        }
        for (int i = 0; i < powerUpList.size(); i++) {
            powerUpList.get(i).draw(g);
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

    public void reset() {

        player1.reset();
        player2.reset();
        powerUpList.clear();
        powerUpList.add(t);
        colorChanged = false;
        closePanel = false;
        startGame = 0;
        gamePaused = false;

        powerUpTimer.start();
        ball.reset();
    }

    public boolean isClosePanel() {
        return closePanel;
    }
    
    
}
