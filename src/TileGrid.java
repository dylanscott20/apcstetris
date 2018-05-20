import javax.swing.*;
import java.awt.*;

public class TileGrid extends JPanel {
    public JPanel[][] tiles;

    public TileGrid(int col, int row) {
        setLayout(new GridLayout(row, col, 0, 0));
        tiles = new JPanel[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
                tiles[i][j] = panel;
                add(tiles[i][j]);
                tiles[i][j].add(new Tile());
            }
        }
    }
    public void setTile(int col, int row, Tile tile) {
        tiles[row][col].removeAll();
        tiles[row][col].add(tile);
    }
}
