/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import static pingpong.panels.CardsPanel.FRAME_HEIGHT;
import static pingpong.panels.CardsPanel.FRAME_WIDTH;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Radek Bartyzal
 */
public class BlackHole implements Drawable {

    private int x;
    private int y;
    private int width;
    private int height;
    private int origWidth;
    private int origHeight;
    private int duration; //How long has been the black hole on screen
    private static final int MAX_DURATION = 20; //How many SECONDS should be Black hole on screen

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
    private List<Particle> particles = new ArrayList<Particle>();

    public BlackHole() {
        reset();
    }

    @Override
    public void draw(Graphics g) {
        if (created) {
            drawCreated(g);
        } else {
            animateCreation(g);
        }
    }

    private void animateCreation(Graphics g) {
        if (creating == false) {
            creating = true;
            //particles = new ArrayList<Particle>();

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

        public void draw(Graphics g) {
            g.drawOval(p.x, p.y, r, r);
        }

    }

    private void drawCreated(Graphics g) {
        pulse();

        float alpha;
        int auraSize = FRAME_WIDTH / 100;
        for (int i = 0; i < auraSize; i++) {
            alpha = (float) ((auraSize - i) * 1.0 / auraSize);
            g.setColor(new Color(1, 1, 1, alpha));
            g.drawOval(x - i, y - i, width + 2 * i, height + 2 * i);
        }
    }

    /**
     * Makes the black hole pulsate. Recommended timer tick rate is 20
     * milliseconds.
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

    public boolean isExpired() {
        return duration >= MAX_DURATION * 1000 / 20;
    }

    /**
     * Extends duration by x
     *
     * @param x Is added to duration
     */
    public void extendDuration(int x) {
        duration += x;
    }

    public final void reset() {
        x = (int) (Math.round(Math.random() * (FRAME_WIDTH - (FRAME_WIDTH / 5)) + (FRAME_WIDTH / 10)));
        y = (int) Math.round(Math.random() * (FRAME_HEIGHT - (FRAME_HEIGHT / 5)) + (FRAME_HEIGHT / 10));
        width = FRAME_WIDTH / 100;
        height = width;
        origWidth = width;
        origHeight = height;
        duration = 0;
        created = false;
        creating = false;
        curStateOfAnim = 0;
        particles.clear();

    }
    
    public void resize() {
        width = FRAME_WIDTH / 100;
        height = width;
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
