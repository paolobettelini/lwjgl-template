package ch.bettelini;

import ch.bettelini.game.Player;

public class Game extends Window {

    private Player player;

    protected Game(String title, int width, int height) throws IllegalStateException {
        super(title, width, height);

        this.player = new Player();
    }

    @Override
    protected void onMouseClick(int button, int action) {
        // player.shoot();
    }

    @Override
    protected void onKeyDown(int key) {
        System.out.println(key);
        switch (key) {
            case 87: // w
                // player.moveUp(velocity);
                break;
            case 65: // a
            // player.moveUp(velocity);
                break;
            case 83: // s
            // player.moveUp(velocity);
                break;
            case 68: // d
            // player.moveUp(velocity);
                break;
        }
    }

    @Override
    protected void onMouseMove(double xpos, double ypos) {
        System.out.println("x: " + xpos + " y: " + ypos);
    }

    @Override
    protected void draw() {
        // TODO Auto-generated method stub
        
    }
    
}
