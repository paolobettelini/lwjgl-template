package ch.samtrevano.game;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL40.*;

import java.util.LinkedList;
import java.util.List;

import ch.samtrevano.glfw.Window;

public class Game extends Window {

    private Player player;
    private List<Bullet> bullets;

    private static final float[] LEVEL_0_VERTICES = {
        0.15f, 0.2f,
        0.15f, -0.2f,
        0.05f, -0.2f,
        -0.0f, -0.3f,
        -0.05f, -0.2f,
        -0.15f, -0.2f,
        -0.15f, 0.2f
    };

    private static final float[] LEVEL_1_VERTICES = {
        0.15f, 0.2f,
        0.15f, -0.2f,
        0.10f, -0.3f,
        0.05f, -0.2f,
        -0.05f, -0.2f,
        -0.1f, -0.3f,
        -0.15f, -0.2f,
        -0.15f, 0.2f
    };

    private static final float[] LEVEL_2_VERTICES = {
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

    private static final float[] BULLET_VERTICES = {
        0
    };

    public Game(String title, int width, int height) throws IllegalStateException {
        super(title, width, height);
        
        this.bullets = new LinkedList<>();
        this.player = new Player();

        start();
    }

    @Override
    protected void onMouseClick(int button, int action) {
        player.shoot();

        Weapon weapon = player.getCurrentWeapon();

        if (weapon == null) { // No weapon to shoot with
            return;
        }

        // Allocate bulllet
        float rot = (float) Math.atan2(getMouseX() - (getWidth() >> 1),
                getMouseY() - (getHeight() >> 1));

        bullets.add(new Bullet(
            player.getX(),
            player.getY(),
            (float) Math.cos(rot),
            (float) Math.sin(rot),
            0.1f,
            weapon.getDistance(),
            weapon.getDamage())
        );
    }

    @Override
    protected void onKeyDown(int key) {
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
            default:
                System.out.println(key);
                break;
        }
    }

    @Override
    protected void onMouseMove(int xpos, int ypos) {
        float rot = (float) Math.atan2(xpos - (getWidth() >> 1), ypos - (getHeight() >> 1));
        player.setRotation(rot);
    }

    @Override
    protected void draw() {
        // Process bullets
        List<Bullet> toRemove = new LinkedList<>();
        for (Bullet bullet : bullets) {
            if (!bullet.step()) {
                toRemove.add(bullet);
                continue;
            }

            // if collision

            drawVertices(BULLET_VERTICES, 0);////
        }

        for (Bullet bullet : toRemove) {
            bullets.remove(bullet);
        }

        // draw player
        float[] vertices = switch(player.getLevel()) {
            case 0 -> LEVEL_0_VERTICES;
            case 1 -> LEVEL_1_VERTICES;
            default -> LEVEL_2_VERTICES;
        };

        glColor3b((byte) 127, (byte) 55, (byte) 0);
        
        drawVertices(vertices, player.getRotation());

    }

    private static void drawVertices(float[] vertices, float rotation) {
        drawVertices(vertices, rotation, 0.0f, 0.0f);
    }

    private static void drawVertices(float[] vertices, float rotation, float offX, float offY) {
        // 2D matrix rotation
        float cosa = (float) Math.cos(rotation);
        float sina = (float) Math.sin(rotation);

        glBegin(GL_POLYGON);

        for (int i = 0; i < vertices.length; i += 2) {
            float x = vertices[i];
            float y = vertices[i + 1];
            glVertex2f(offX + x * cosa - y * sina, offY + x * sina + y * cosa);
        }

        glEnd();
    }

}