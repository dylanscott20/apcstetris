import javax.swing.*;

public class Display extends JFrame{
    private int col = 10;
    private int row = 20;

    private JPanel main;
    private TileGrid tileGrid;
    private Input input;


    public Display(String label) {
        super(label);
        setup();
        pack();
    }

    public Input getInput() {
            return input;
    }

    private void setup() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tileGrid = new TileGrid(col,row);
        main = new JPanel();
        setContentPane(main);
        input = new Input(main);
        main.setFocusable(true);
        main.add(tileGrid);
        setResizable(false);
    }

    public void setTileGrid(TileGrid t) {
            main.removeAll();
            main.add(t);
            main.setVisible(true);
    }
}
