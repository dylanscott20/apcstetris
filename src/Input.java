import java.awt.Component;
import java.awt.event.*;

public class Input implements KeyListener{

        private boolean[] keys = new boolean[256];
        private int[] wait = new int[256];
        public Input(Component e) {
            for(int i = 0; i < wait.length; i++) {
                wait[i] = 0;
            }
            e.addKeyListener(this);
        }

        public boolean isKeyDown(int keyCode)
        {
            if (keyCode > 0 && keyCode < 256)
            {
                return keys[keyCode];
            }

            return false;
        }

        public void keyPressed(KeyEvent e)
        {
            if (e.getKeyCode() > 0 && e.getKeyCode() < 256)
            {
                keys[e.getKeyCode()] = true;
            }
        }

        public void keyReleased(KeyEvent e)
        {
            if (e.getKeyCode() > 0 && e.getKeyCode() < 256)
            {
                keys[e.getKeyCode()] = false;
            }
        }
        public void keyTyped(KeyEvent e) {}

        public boolean isKeyWait(int keyCode, int t) {
            boolean result = false;
            if (keyCode > 0 && keyCode < 256) {
                if(isKeyDown(keyCode)) {
                    if (wait[keyCode] % t == 0) {
                        result = true;
                    }
                    wait[keyCode]++;
                } else {
                    wait[keyCode] = 0;
                }
            }
            return result;
        }
}

