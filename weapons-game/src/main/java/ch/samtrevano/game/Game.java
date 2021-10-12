package ch.samtrevano.game;

import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3b;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.util.LinkedList;
import java.util.List;

import ch.samtrevano.game.weapons.WeaponLevel1;
import ch.samtrevano.game.weapons.WeaponLevel2;
import ch.samtrevano.game.weapons.WeaponLevel3;
import ch.samtrevano.glfw.Window;

public class Game extends Window {

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
        -0.02f, -0.02f,
        -0.02f, 0.02f,
        0.02f, 0.02f,
        0.02f, -0.02f
    };

    private Player player;
    private List<Bullet> bullets;

    private boolean movingUp, movingDown, movingLeft, movingRight;

    private float[] currentVertices = LEVEL_0_VERTICES;

    public Game(String title, int width, int height) throws IllegalStateException {
        super(title, width, height);
        
        this.bullets = new LinkedList<>();
        this.player = new Player();
        movingUp = movingDown = movingLeft = movingRight = false;

        player.addWeapon(new WeaponLevel1());
        player.addWeapon(new WeaponLevel2());
        player.addWeapon(new WeaponLevel3());

        start();
    }

    @Override
    protected void onMouseClick(int button, int action) {
        if (action != 1) { // Use glfw variable
            return;
        }

        if (!player.shoot()) {
            return;
        }
        
        Weapon weapon = player.getCurrentWeapon();

        if (weapon == null) { // No weapon to shoot with
            return;
        }

        // Allocate bulllet
        float rot = (float) Math.atan2(getMouseX() - (getWidth() >> 1),
                getMouseY() - (getHeight() >> 1));

        rot -= Math.PI / 2.0;

        bullets.add(new Bullet(
            player.getX(),
            player.getY(),
            (float) Math.cos(rot),
            (float) Math.sin(rot),
            weapon.getBulletSpeed(),
            weapon.getDistance(),
            weapon.getDamage())
        );
    }

    @Override
    protected void onKeyDown(int key, int action) {
        if (action == 2) {
            return;
        }

        switch (key) {
            case 'W':
                movingUp = !movingUp;
                break;
            case 'A':
                movingLeft = !movingLeft;
                break;
            case 'S':
                movingDown = !movingDown;
                break;
            case 'D':
                movingRight = !movingRight;
                break;
            case 'R':
                if (action == 1) {
                    player.reloadWeapon();
                }
                break;
            case 'F':
                if (action == 1) { // Use glfw variable
                    player.changeWeapon();
                    player.setLevel((player.getLevel() + 1) % 3);

                    currentVertices = switch(player.getLevel()) {
                        case 0 -> LEVEL_0_VERTICES;
                        case 1 -> LEVEL_1_VERTICES;
                        default -> LEVEL_2_VERTICES;
                    };
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onMouseMove(int xpos, int ypos) {
        float rot = (float) Math.atan2(xpos - (getWidth() >> 1), ypos - (getHeight() >> 1));
        player.setRotation(rot);
    }

    private List<Bullet> bulletsToRemove = new LinkedList<>();
    
    @Override
    protected void draw() {
        // Process movement
        if (movingUp) {
            player.moveUp(0.025f);
        }

        if (movingDown) {
            player.moveDown(0.025f);
        }

        if (movingLeft) {
            player.moveLeft(0.025f);
        }

        if (movingRight) {
            player.moveRight(0.025f);
        }
        
        // Process bullets
        for (Bullet bullet : bullets) {
            if (!bullet.step()) {
                bulletsToRemove.add(bullet);
                continue;
            }

            glColor3b((byte) 66, (byte) 0, (byte) 22);
            drawVertices(BULLET_VERTICES, player.getRotation(), bullet.getX() - player.getX(), bullet.getY() - player.getY());
        }

        for (Bullet bullet : bulletsToRemove) {
            bullets.remove(bullet);
        }

        if (bulletsToRemove.size() != 0) {
            bulletsToRemove.clear();
        }

        // draw player
        glColor3b((byte) 127, (byte) 55, (byte) 0);
        drawVertices(currentVertices, player.getRotation());
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