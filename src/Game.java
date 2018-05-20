import java.awt.*;
import java.awt.event.KeyEvent;

public class Game {

    public final int FPS = 60;

    private boolean isRunning = true;

    public Game() {

    }

    public void run() {
        Display display = new Display("Test");
        int i = 0;
        int j = 0;
        int r = 0;
        int l = 0;
        int d = 0;
        int u = 0;
        while(isRunning) {
            Input in = display.getInput();
            if(in.isKeyDown(KeyEvent.VK_B)) {
                display.setTile(i,j,new Tile(Color.green));
            }
            if (in.isKeyDown(KeyEvent.VK_RIGHT)) {
                if(r % 15 == 0) {
                    i++;
                }
                r++;
            } else {
                r = 0;
            }
            if (in.isKeyDown(KeyEvent.VK_LEFT)) {
                if(l % 15 == 0) {
                    i--;
                }
                l++;
            } else {
                l = 0;
            }
            if (in.isKeyDown(KeyEvent.VK_DOWN)) {
                if(d % 15 == 0) {
                    j++;
                }
                d++;
            } else {
                d = 0;
            }
            if (in.isKeyDown(KeyEvent.VK_UP)) {
                if(u % 15 == 0) {
                    j--;
                }
                u++;
            } else {
                u = 0;
            }
            System.out.println();
            display.setVisible(true);
            Helper.waitTime(1000/FPS);
        }

    }
}
