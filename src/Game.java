import java.awt.event.KeyEvent;

public class Game {

    public final int FPS = 30;

    private boolean isRunning = true;

    public Game() {

    }

    public void run() {
        Display display = new Display("Test");
        display.setVisible(true);
        while(isRunning) {
            //Loop
            Input in = display.getInput();
            if(in.isKeyDown(KeyEvent.VK_LEFT)) {
                display.setLeft(true);
            } else {
                display.setLeft(false);
            }
            if(in.isKeyDown(KeyEvent.VK_RIGHT)) {
                display.setRight(true);
            } else {
                display.setRight(false);
            }
            if(in.isKeyDown(KeyEvent.VK_UP)) {
                display.setUp(true);
            } else {
                display.setUp(false);
            }
            if(in.isKeyDown(KeyEvent.VK_DOWN)) {
                display.setDown(true);
            } else {
                display.setDown(false);
            }

            Helper.waitTime(1000/FPS);
        }

    }
}
