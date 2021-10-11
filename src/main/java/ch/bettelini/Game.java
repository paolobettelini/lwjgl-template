package ch.bettelini;

import ch.bettelini.game.Player;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL40.*;

public class Game extends Window {

    private Player player;

    public Game(String title, int width, int height) throws IllegalStateException {
        super(title, width, height);
        
        this.player = new Player();

        start();
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
                player.moveUp(0.1f);
                break;
            case 65: // a
                player.moveLeft(0.1f);
                break;
            case 83: // s
                player.moveDown(0.1f);
                break;
            case 68: // d
                player.moveLeft(0.1f);
                break;
        }
    }

    @Override
    protected void onMouseMove(double xpos, double ypos) {
        float rot = (float) Math.atan2(xpos - (getWidth() >> 1), ypos - (getHeight() >> 1));
        player.setRotation(rot);
    }

    private static float[] level0Vertices = {
        0.15f, 0.2f,
        0.15f, -0.2f,
        0.05f, -0.2f,
        -0.0f, -0.3f,
        -0.05f, -0.2f,
        -0.15f, -0.2f,
        -0.15f, 0.2f
    };

    private static float[] level1Vertices = {
        0.15f, 0.2f,
        0.15f, -0.2f,
        0.10f, -0.3f,
        0.05f, -0.2f,
        -0.05f, -0.2f,
        -0.1f, -0.3f,
        -0.15f, -0.2f,
        -0.15f, 0.2f
    };

    private static float[] level2Vertices = {
        0.15f, 0.2f,
        0.15f, -0.2f,
        0.10f, -0.3f,
        0.05f, -0.2f,
        -0.0f, -0.3f,
        -0.05f, -0.2f,
        -0.1f, -0.3f,
        -0.15f, -0.2f,
        -0.15f, 0.2f
    };

    @Override
    protected void draw() {
        // draw player
        float[] vertices = switch(player.getLevel()) {
            case 0 -> level0Vertices;
            case 1 -> level1Vertices;
            default -> level2Vertices;
        };

        // 2D matrix rotation
        float cosa = (float) Math.cos(player.getRotation());
        float sina = (float) Math.sin(player.getRotation());

        glColor3b((byte) 127, (byte) 55, (byte) 0);
        
        glBegin(GL_POLYGON);
        for (int i = 0; i < vertices.length; i += 2) {
            float x = vertices[i];
            float y = vertices[i + 1];
            glVertex2f(x * cosa - y * sina, x * sina + y * cosa);
        }
        glEnd();

    }

}