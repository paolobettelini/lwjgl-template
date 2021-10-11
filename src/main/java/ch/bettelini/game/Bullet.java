package ch.bettelini.game;

public class Bullet {
    
    private float posX, posY, xIncrement, yIncrement, maxDist, stepLength;
    private int steps = 0;

    public Bullet(float posX, float posY, float dirX, float dirY, float velocity, float maxDist) {
        this.posX = posX;
        this.posY = posY;
        this.xIncrement = dirX * velocity;
        this.yIncrement = dirY * velocity;
        this.stepLength = (float) Math.sqrt(xIncrement * xIncrement + yIncrement * yIncrement);
    }

    public float getX() {
        return posX;
    }

    public float getY() {
        return posY;
    }

    public boolean step() {
        posX += xIncrement;
        posY += yIncrement;

        return ++steps * stepLength > maxDist;
    }

}
