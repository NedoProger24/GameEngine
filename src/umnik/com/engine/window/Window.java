package umnik.com.engine.window;

import com.sun.org.glassfish.gmbal.Description;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import umnik.com.engine.render.Render;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11C.*;

public class Window {

    /* ==================================
    *  Author           - Islam Ismailov
    *  Version          - 0.1
    *  Data of creating - 05.03.2023
    *  Data of editing  - 05.03.2023
    *================================== */

    private float red = 0;
    private float green = 0;
    private float blue = 0;
    private float alpha = 0;
    private boolean isCenter = true;
    private int x;
    private int y;
    private int width;
    private int height;
    private String title;
    private Render render = null;
    private long id;

    public Window(String title) {
        this.title = title;
    }

    public void addRender(Render render) {
        this.render = render;
    }

    @Description(
            "This method sets the background color of window." +
            "Arguments are accepted in rgba format with data from 0 to 1"
    )
    public void setColor(float red, float green, float blue, float alpha)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public void close() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glfwSwapBuffers(id);
        glfwPollEvents();
        glfwFreeCallbacks(id);
        glfwDestroyWindow(id);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    //loops until the window is closed
    public boolean isShouldClose()
    {
        return glfwWindowShouldClose(id);
    }

    public void create()
    {
        GLFWErrorCallback.createPrint(System.err).set();
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        id = glfwCreateWindow(width, height, title, 0, 0);
        if ( id == 0 )
            throw new RuntimeException("Failed to create the GLFW window");
        init();
        GL.createCapabilities();
        glClearColor(red, green, blue, alpha);
    }

    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public void setPositionToCenter(boolean isCenter)
    {
        this.isCenter = isCenter;
    }

    public void setPosition(int x, int y)
    {
        isCenter = false;
        this.x = x;
        this.y = y;
    }

    public void update() {
        glfwPollEvents();
        glfwSwapBuffers(id);
    }

    private void init()
    {
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        MemoryStack stack = MemoryStack.stackPush();
        // Center the window
        IntBuffer pWidth = stack.mallocInt(width); // int*
        IntBuffer pHeight = stack.mallocInt(height);
        assert vidmode != null;
        initPos(vidmode,pWidth,pHeight);
        glfwGetWindowSize(id, pWidth, pHeight);
        glfwSetWindowAspectRatio(this.id, this.width, this.height);
        glfwMakeContextCurrent(id);
        glfwShowWindow(id);
    }
    private void initPos(GLFWVidMode vidMode, IntBuffer pWidth, IntBuffer pHeight){
        if (isCenter)
            glfwSetWindowPos(id,(vidMode.width() - pWidth.get(0)) / 2,(vidMode.height() - pHeight.get(0)) / 2);
        else
            glfwSetWindowPos(id,x,y);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public float getRed() {
        return red;
    }

    public void setRed(float red) {
        this.red = red;
    }

    public float getGreen() {
        return green;
    }

    public void setGreen(float green) {
        this.green = green;
    }

    public float getBlue() {
        return blue;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
}