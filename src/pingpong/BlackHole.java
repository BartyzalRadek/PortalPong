/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import static pingpong.panels.CardsPanel.FRAME_HEIGHT;
import static pingpong.panels.CardsPanel.FRAME_WIDTH;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Radek Bartyzal
 */
class BlackHole {

    private int x;
    private int y;
    private int width;
    private int height;
    private final int origWidth;
    private final int origHeight;

    //Pulsing variables
    private final int pulseSize = 5;
    private boolean enlarging = true;
    private int tmp;
    private int cnt = 0;

    //Create animation variables
    private boolean created = false;
    private boolean creating = false;
    private int curStateOfAnim = 0;
    private final int finalStateOfAnim = 20; //How long is the animation going to last
    private List<Particle> particles;

    /**
     * Initialize Blackhole instance
     *
     * @param x X position of top left corner
     * @param y Y position of top left corner
     * @param width Width of BlackHole
     * @param height Height of BlackHole
     */
    public BlackHole(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        origWidth = width;
        origHeight = height;
    }

    public void draw(Graphics2D g) {
        if (created) {
            drawCreated(g);
        } else {
            animateCreation(g);
        }
    }

    private void animateCreation(Graphics2D g) {
        if (creating == false) {
            creating = true;
            particles = new ArrayList<Particle>();

            for (int i = 0; i < 100; i++) {
                particles.add(new Particle((int) (Math.round(Math.random() * FRAME_WIDTH)),
                        (int) (Math.round(Math.random() * FRAME_HEIGHT))));
            }
        }

        for (Particle p : particles) {
            p.move();
            p.draw(g);
        }

        if (curStateOfAnim == finalStateOfAnim) {
            created = true;
            creating = false;
            System.out.println("FINISHED ANIMATION");
        }
        curStateOfAnim++;
    }

    class Particle {
        private final Point p;
        private final int r;
        private final double dx;
        private final double dy;

        public Particle(int x1, int y1) {
            this.p = new Point(x1, y1);
            r = (int) (Math.round(Math.random() * 2));
            dx = (x - p.x) * 1.0 / finalStateOfAnim;
            dy = (y - p.y) * 1.0 / finalStateOfAnim;
        }

        public void move() {
            p.x += (int) dx;
            p.y += (int) dy;
        }

        public void draw(Graphics2D g) {
            g.drawOval(p.x, p.y, r, r);
        }

    }

    private void drawCreated(Graphics2D g) {
        pulse();

        float alpha;
        for (int i = 0; i < 10; i++) {
            alpha = (float) ((10 - i) / 10.0);
            g.setColor(new Color(1, 1, 1, alpha));
            g.drawOval(x - i, y - i, width + 2 * i, height + 2 * i);
        }
    }

    /**
     * Makes the black hole pulsate. Recommended timer tick rate is 20 milliseconds.
     */
    public void pulse() {
        if (cnt == 5) {
            cnt = 0;
            if (enlarging) {
                tmp++;
                enlarge();
            } else {
                tmp--;
                shrink();
            }

            correctSize();

            if (tmp == 0) {
                enlarging = true;
            }
            if (tmp == pulseSize) {
                enlarging = false;
            }
        } else {
            cnt++;
        }
    }

    private void correctSize() {
        if (width <= origWidth / 2) {
            width++;
        }
        if (width >= origWidth * 2) {
            width--;
        }
        if (height <= origHeight / 2) {
            height++;
        }
        if (height >= origHeight * 2) {
            height--;
        }
    }

    private void enlarge() {
        if (Math.random() >= 0.5) {
            width++;
        }
        if (Math.random() >= 0.5) {
            height++;
        }
    }

    private void shrink() {
        if (Math.random() >= 0.5) {
            width--;
        }
        if (Math.random() >= 0.5) {
            height--;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    

}
