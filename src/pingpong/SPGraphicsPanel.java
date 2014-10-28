/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

/**
 *
 * @author Darmy
 */
public class SPGraphicsPanel extends GraphicsPanel {

    /*public boolean isTeleport = false;
     public PowerUp t = new PowerUp(0, 3);
     public Ball ball = new Ball();
     public Teleport teleport = new Teleport();
     public int paddleV = 10;
     public Paddle paddle1 = new Paddle(10, 1, 10); //skutecna rychlost palky rizene clovekem je nastavena u KeyPressed
     public Paddle paddle2 = new Paddle(950, 2, 3); //AI paddle
     public Player player1 = new Player();
     public Player player2 = new Player();
     private List<Drawable> drawableList = new ArrayList<Drawable>();
     private List<PowerUp> powerUpList = new ArrayList<PowerUp>();
     private Color color = Color.WHITE; //kvuli endGame()
     private boolean colorChanged = false; //kvuli endGame()
     public int endGame = 0;    //kvuli endGame()
     private boolean gamePaused = false;
     public boolean fixedSpeed = true; //speed of ball is not increasing in time
     private int startGame = 0;*/
    public SPGraphicsPanel() {
        drawableList.add(ball);
        drawableList.add(paddle1);
        drawableList.add(paddle2);
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
                powerUpList.get(i).score(paddle2, player2);
            }

            ball.move();
            ball.bounceOffWalls(true);
            ball.scorePoint(player1, player2, true);
            ball.bounceOffPaddle(paddle1, player1);
            ball.bounceOffPaddle(paddle2, player2);
            ball.teleport(isTeleport, teleport);

            paddle2.move(ball, 500);
            AIteleport();

        }
    });

    public void mainTimer() {
        super.mainTimer();
        paddle2.move(ball, 500);
        AIteleport();
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
                if (endGame == 1) {
                    endGame = 2;
                }
                if (gamePaused) {
                    endGame = 2;
                }
            case KeyEvent.VK_ESCAPE:
                pauseGame();
                break;
            case KeyEvent.VK_SPACE:
                startGame = 1;
                break;
        }
    }

    private void AIteleport() {
        int difference = (ball.y > paddle2.y ? ball.y - paddle2.y : paddle2.y - ball.y);
        if (ball.x > 900 && difference > 200) {
            if (t.numberB > 0) {
                createTeleport();
                t.numberB -= 1;
            }
        }
    }
}
