import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

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
        setContentPane(main);

        input = new Input(main);
        main.setFocusable(true);
        grid.add(tileGrid);
        setResizable(false);
        //tileGrid.setTile(0,0,new Tile(Color.green));
    }

    public void setTile(int col, int row, Tile tile) {
        tileGrid.setTile(col,row,tile);
    }
}
