package pingpong;

/**
 *
 * @author Radek Bartyzal
 */
public class Player {
    
    public int score;
    public int teleports;
    private int winningScore = 10;
    private int lives = 1; //A starting number of lives, its not changed after starting the game
    
    //--Endless score variables start
    private int plusCount;
    private int minusCount;
    private int tCount;
    public int ballReturned;
    //--Endless score variables end

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    

    public Player() {
        score = 0;
        teleports = 3;
    }
    
    public boolean win() {
        return score >= winningScore;
    }
    
    /**
     * Both players, AI and real player have set the number of lives => when AI 
     * scores more times than how many lives the player has, the AI wins.
     */
    public boolean endlessWin() {
        return score >= lives;
    }
    
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
        teleports = 3;
    }
    
    public void catchPowerUp(int type){
        switch (type) {
                case 0:
                    teleports += 1;
                    tCount++;
                    break;
                case 1:
                    plusCount++;
                    break;
                case 2:
                    minusCount++;
                    break;
            }
    }

    public int getPlusCount() {
        return plusCount;
    }

    public int getMinusCount() {
        return minusCount;
    }

    public int gettCount() {
        return tCount;
    }

    public void setWinningScore(int winningScore) {
        this.winningScore = winningScore;
    }
    
    
    
    
    
    
    
}
