import umnik.com.engine.render.Render;
import umnik.com.engine.window.Window;

public class Main {
    public static void main(String[] args) {
        Render render = new Render();
        float[] v_triangle =  { 0.0f,  0.5f, 0f,    -0.5f, -0.5f, 0f,  0.5f, -0.5f, 0f };


        Window window = new Window("Окно");
        window.setSize(1200,700);
        window.setPosition(100,100);
        window.setRed(0);
        window.setGreen(0);
        window.setBlue(1);
        window.create();
        //render.clear(1,0,1,1);
        render.addTriangle(Render.convertToBuffer(v_triangle));
        while (!window.isShouldClose()) {
            render.clear(0,1,1,1);
            render.update();

            //render.clear(1,0,1,1);
            window.update();
        }
        window.close();
    }
}