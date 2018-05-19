import java.awt.event.KeyEvent;

public class Game {

    public Game() {

    }

    public void run() {
        Display display = new Display();
        Input input = new Input(display);

        while(true) {
            System.out.println(input.isKeyDown(KeyEvent.VK_B));
            Helper.waitTime(50);
        }
    }
}
