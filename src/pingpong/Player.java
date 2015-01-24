package pingpong;

/**
 *
 * @author Radek Bartyzal
 */
public class Player {

    private int teleports;
    private int score;
    private int winningScore = 10;
    private int lives = 1; //A starting number of lives, its not changed after starting the game
    private static final int DEFAULT_TELEPORTS = 3; //default number of teleports

    //--Endless score variables start
    private int plusCount;
    private int minusCount;
    private int tCount;
    private int ballReturned;
    //--Endless score variables end

    public Player() {
        score = 0;
        teleports = DEFAULT_TELEPORTS;
    }

    public boolean useTeleport() {
        if (teleports > 0) {
            teleports--;
            return true;
        } else {
            return false;
        }
    }

    public boolean win() {
        return score >= winningScore;
    }

    public void score() {
        score++;
    }

    /**
     * Both players, AI and real player have set the number of lives => when AI
     * scores more times than how many lives the player has, the AI wins.
     */
    public boolean endlessWin() {
        return score >= lives;
    }

    public int endlessScore() {
        score = (plusCount * 3) - (minusCount * 3) + (tCount * 3) + ballReturned;
        return score;
    }

    public void reset() {
        score = 0;
        plusCount = 0;
        minusCount = 0;
        tCount = 0;
        ballReturned = 0;
        teleports = DEFAULT_TELEPORTS;
    }

    public void catchPowerUp(int type) {
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
    
    public void ballReturned(){
        ballReturned++;
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

    public int getScore() {
        return score;
    }

    public int getTeleports() {
        return teleports;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getBallReturned() {
        return ballReturned;
    }
    
    

}
