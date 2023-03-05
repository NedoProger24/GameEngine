import umnik.com.engine.window.Window;

public class Main {
    public static void main(String[] args) {
        Window window = new Window("Окно");
        window.setSize(1200,700);
        window.setPosition(100,100);
        window.setRed(1f);
        window.setGreen(1);
        window.setBlue(1);
        window.create();
        window.run();
    }
}