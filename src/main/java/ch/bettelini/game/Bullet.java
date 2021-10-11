package ch.bettelini.game;

public class Bullet {
    
    float posX, posY, dirX, dirY, velocity;

    public Bullet(float posX, float posY, float dirX, float dirY, float velocity) {
        this.posX = posX;
        this.posY = posY;
        this.dirX = dirX;
        this.dirY = dirY;
        this.velocity = velocity;
    }

    public float getX() {
        return posX;
    }

    public float getY() {
        return posY;
    }

    public void step() {

    }

}
