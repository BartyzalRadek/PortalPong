/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

/**
 *
 * @author Darmy
 */
public class Player {
    
    public int score;
    public int winningScore = 10;
    private  int lives = 1;
    public int plusCount;
    public int minusCount;
    public int tCount;
    public int ballReturned;

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    

    public Player() {
        score = 0;
    }
    
    public boolean win() {
        if(score >= winningScore) {
            return true;
        }
        else{return false;}
    }
    
    /*
    public boolean endlessWin() {
        return score >= lives;
    }*/
    
    public int endlessScore() {
        score = (plusCount*3) - (minusCount*3) + (tCount*3) + ballReturned;
        return score;
    }
    
    public void reset() {
        score = 0;
        plusCount = 0;
        minusCount = 0;
        tCount = 0;
        ballReturned = 0;
    }
    
    
    
}
