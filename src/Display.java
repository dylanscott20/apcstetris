import javax.swing.*;

public class Display extends JFrame{
    private int col = 10;
    private int row = 20;

    private JPanel main;
    private JPanel grid;
    private TileGrid tileGrid;
    private JPanel scoreboard;
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
        grid = new JPanel();
        main.add(grid);
        input = new Input(grid);
        grid.setFocusable(true);
        grid.add(tileGrid);
        setResizable(false);
    }

    public void setTileGrid(TileGrid t) {
            grid.removeAll();
            grid.add(t);
            grid.setVisible(true);
    }
}
