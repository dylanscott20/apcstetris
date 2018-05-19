import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Display extends JFrame {

    private JPanel Grid;
    private JRadioButton u;
    private JRadioButton r;
    private JRadioButton l;
    private JRadioButton d;

    private boolean[] keys = new boolean[256];
    private Input input;

    public Display(String label) {
        super(label);
        //setSize(150,150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(Grid);
        pack();

        input = new Input();
        Grid.addKeyListener(input);
        Grid.setFocusable(true);
    }

    public Input getInput() {
        return input;
    }

    public void setLeft(boolean t) {
        l.setSelected(t);
    }

    public void setRight(boolean t) {
        r.setSelected(t);
    }

    public void setUp(boolean t) {
        u.setSelected(t);
    }

    public void setDown(boolean t) {
        d.setSelected(t);
    }
}
