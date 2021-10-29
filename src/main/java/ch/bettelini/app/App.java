package ch.bettelini.app;

import ch.bettelini.glfw.Window;
import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3b;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

public class App extends Window {

    public App(String title, int width, int height) throws IllegalStateException {
        super(title, width, height);
    }

    @Override
    protected void draw() {
        glColor3b((byte) 127, (byte) 0, (byte) 0);
        glBegin(GL_POLYGON);
        glVertex2f(0.0f, 0.5f);
        glVertex2f(-0.866f, -0.5f);
        glVertex2f(0.866f, -0.5f);
        glEnd();
    }

    @Override
    protected void onMouseClick(int button, int action) {

    }

    @Override
    protected void onKeyDown(int key, int action) {
        
    }

    @Override
    protected void onMouseMove(int xpos, int ypos) {

    }

    @Override
    protected void onWindowsResize(int width, int height) {
        
    }
    
}
