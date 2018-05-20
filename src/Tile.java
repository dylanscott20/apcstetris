import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {
    private final Color DEFAULT = Color.LIGHT_GRAY;

    public Tile(Color c) {
        setup(c);
    }
    public Tile() {
        setup(DEFAULT);
    }
    private void setup(Color c) {
        setPreferredSize(new Dimension(35,35));
        setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
        setBackground(c);
    }
}
