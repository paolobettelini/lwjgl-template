package ch.bettelini.glfw;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

public abstract class Window {

    private static boolean INIT = false;

    private long window;

    private int height;
    private int width;
    private int mouseX;
    private int mouseY;

    protected Window(String title, int width, int height)
            throws IllegalStateException {
        if (INIT) {
            throw new IllegalStateException("A window has already been initialized");
        }

        this.width = width;
        this.height = height;

        INIT = true;
        
        init(title);
    }

    protected abstract void draw();

    protected abstract void onMouseClick(int button, int action);

    protected abstract void onKeyDown(int key, int action);

    protected abstract void onMouseMove(int xpos, int ypos);

    protected abstract void onWindowsResize(int width, int height);

    public void start() {
        loop();
        release();
    }

    private void init(String title) {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // The window will stay hidden after creation
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

        // the window will be resizable
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        
        // Create the window
        window = glfwCreateWindow(width, height, title, NULL, NULL);
        
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Mouse Movement Listener
        glfwSetCursorPosCallback(window, (window, x, y) ->
            onMouseMove(mouseX = (int) x, mouseY = (int) y));
        
        // Key listener
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            // Close Window
            if (action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true);
            }
            
            onKeyDown(key, action);
        });
        
        // Mouse Click Listener
        glfwSetMouseButtonCallback(window, (window, button, action, mods) -> onMouseClick(button, action));

        // Resize Listener
        glfwSetWindowSizeCallback(window, (window, width, height) ->
            onWindowsResize(this.width = width, this.height = height));
        
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        // Center on screen
        glfwSetWindowPos(
            window,
            (vidmode.width() - width) >> 1,
            (vidmode.height() - height) >> 1
        );

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
    }

    private void loop() {
        // LWJGL's interoperation with GLFW's
        GL.createCapabilities();
        
        // Clear all
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glLoadIdentity();
            
            draw();

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    private void release() {
        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMouseX() {
        return mouseX;
    }
    
    public int getMouseY() {
        return mouseY;
    }

    public long getWindow() {
        return window;
    }
    
}