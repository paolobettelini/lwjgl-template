package ch.bettelini;

import java.nio.*;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL40.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public abstract class Window {
    
    private static boolean INIT = false;

	private long window;
    private int width;
    private int height;
    private String title;

    protected Window(String title, int width, int height)
            throws IllegalStateException {
        if (INIT) {
            throw new IllegalStateException("A window has already been initialized");
        }

        INIT = true;

        this.title = title;
        this.width = width;
        this.height = height;
        
        init();
        loop();
        release();
    }

    protected abstract void onMouseClick(int button, int action);

    protected abstract void onKeyDown(int key);

    protected abstract void onMouseMove(double xpos, double ypos);

    protected abstract void draw();

    private void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if (!glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        
        // Create the window
        window = glfwCreateWindow(width, height, title, NULL, NULL);
        
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Mouse Movement Listener
        glfwSetCursorPosCallback(window, (window, xpos, ypos) -> {
            onMouseMove(xpos, ypos);
        });
        
        // Key listener
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            // Close Window
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true);
            }
            
            onKeyDown(key);
        });
        
        // Mouse Click Listener
        glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
            onMouseClick(button, action);
        });

        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            glfwGetWindowSize(window, pWidth, pHeight);

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(
                window,
                (vidmode.width() - pWidth.get(0)) / 2,
                (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
	}

    private void loop() {
		// LWJGL's interoperation with GLFW's
		GL.createCapabilities();
		
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
		
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

    public String getTitle() {
        return title;
    }

    public long getWindow() {
        return window;
    }
    
}
