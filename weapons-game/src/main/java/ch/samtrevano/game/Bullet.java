package ch.samtrevano.game;

public class Bullet {
    
    private int damage;
    private float posX;
    private float posY;
    private float xIncrement;
    private float yIncrement;
    private float maxDist;
    private float stepLength;
    private int steps;

    public Bullet(float posX, float posY, float dirX, float dirY, float velocity, float maxDist, int damage) {
        this.posX = posX;
        this.posY = posY;
        this.xIncrement = dirX * velocity;
        this.yIncrement = dirY * velocity;
        this.stepLength = (float) Math.sqrt(xIncrement * xIncrement + yIncrement * yIncrement);
        this.maxDist = maxDist;
        this.steps = 0;
    }

    public float getX() {
        return posX;
    }

    public float getY() {
        return posY;
    }

    public int getDamage() {
        return damage;
    }

    public boolean step() {
        posX += xIncrement;
        posY += yIncrement;

        return ++steps * stepLength < maxDist;
    }

}
