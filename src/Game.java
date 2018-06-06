import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Game {
    private final Color DEFAULT = Color.LIGHT_GRAY;
    private final int FPS = 60;
    private final int col = 10;
    private final int row = 20;

    private int tetrisT[][] = {{1, 1, 1}, {0, 1, 0}};
    private int tetrisL[][] = {{1, 0, 0}, {1, 1, 1}};
    private int tetrisJ[][] = {{0, 0, 1}, {1, 1, 1}};
    private int tetrisZ[][] = {{1, 1, 0}, {0, 1, 1}};
    private int tetrisS[][] = {{0, 1, 1}, {1, 1, 0}};
    private int tetrisO[][] = {{1, 1}, {1, 1}};
    private int tetrisI[][] = {{1}, {1}, {1}, {1}};

    private int nextPiece[][];
    private Color nextColor;

    private Color grid[][];
    private Color drop[][];
    private Point dropPos;
    private int offset;
    private TileGrid tileGrid;
    private Display display;
    private int totalLines;

    private boolean isDropping = false;
    private boolean gameOver = false;
    private int endDrop = 0;
    private int speed = FPS / 2;
    private int keySpeed = FPS / 4;

    public Game(Display d) {
        display = d;
        tileGrid = new TileGrid(col, row);
        grid = new Color[row][col];
        totalLines = 0;
        randomPiece();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = DEFAULT;
            }
        }

    }

    public void run() {
        int frame = 0;
        boolean skip = true;
        while (!gameOver) {
            long time = System.currentTimeMillis();
            Input in = display.getInput();

            //LOOP
            switch (totalLines / 10) {
                case 0:
                    speed = 30;
                    break;
                case 1:
                    speed = 25;
                    break;
                case 2:
                    speed = 20;
                    break;
                case 3:
                    speed = 15;
                    break;
                case 4:
                    speed = 10;
                    break;
                case 5:
                    speed = 5;
                    break;
                case 6:
                    speed = 4;
                    break;
                case 7:
                    speed = 3;
                    break;
                case 8:
                    speed = 2;
                    break;
                case 9:
                    speed = 1;
                    break;
            }
            if (!isDropping) {
                if (!skip) {
                    combineArrays();
                    removeRows();
                } else {
                    skip = false;
                }
                startDrop();
                Helper.waitTime(250);
            } else {
                if (frame % speed == 0 || in.isKeyWait(KeyEvent.VK_DOWN, 2)) {
                    drop();
                }
            }
            if (in.isKeyWait(KeyEvent.VK_LEFT, keySpeed)) {
                moveLeft();
            }
            if (in.isKeyWait(KeyEvent.VK_RIGHT, keySpeed)) {
                moveRight();
            }
            if (in.isKeyWait(KeyEvent.VK_UP, keySpeed)) {
                rotate();
            }

            updateTileGrid();
            display.setTileGrid(tileGrid);
            display.setVisible(true);
            frame++;

            time = (1000 / FPS) - (System.currentTimeMillis() - time);
            if (time > 0) {
                Helper.waitTime((int) time);
            }
        }
        Helper.waitTime(1000);
    }

    public void startDrop() {
        drop = new Color[nextPiece.length][nextPiece[0].length];
        dropPos = new Point((col/2) + offset, 0);
        for (int i = 0; i < nextPiece.length; i++) {
            for (int j = 0; j < nextPiece[i].length; j++) {
                if (nextPiece[i][j] == 1) {
                    drop[i][j] = nextColor;
                } else {
                    drop[i][j] = DEFAULT;
                }
            }
        }
        if (checkCollision(2)) {
            gameOver = true;
        }
        isDropping = true;
        randomPiece();
    }

    public void drop() {
        if (!checkCollision(0)) {
            dropPos.y++;
        } else {
            if (endDrop <= 5) {
                endDrop = 0;
                isDropping = false;
            }
            endDrop++;
        }
    }

    public void moveLeft() {
        if (!checkCollision(-1)) {
            dropPos.x--;
        }
    }

    public void moveRight() {
        if (!checkCollision(1)) {
            dropPos.x++;
        }
    }

    public boolean checkCollision(int direction) {
        //Checks if moving piece in direction will cause collision
        //Also checks if it will cause ArrayOutOfBounds

        //DOWN
        if (direction == 0) {
            if (dropPos.y + 1 > row - drop.length) {
                return true;
            }
            for (int i = 0; i < drop.length; i++) {
                for (int j = 0; j < drop[i].length; j++) {
                    Color c = drop[i][j];
                    Color gridC = grid[i + dropPos.y + 1][j + dropPos.x];
                    if (!c.equals(DEFAULT) && !gridC.equals(DEFAULT)) {
                        return true;
                    }
                }
            }
        }
        //LEFT
        if (direction == -1) {
            if (dropPos.x - 1 < 0) {
                return true;
            }
            for (int i = 0; i < drop.length; i++) {
                for (int j = 0; j < drop[i].length; j++) {
                    Color c = drop[i][j];
                    Color gridC = grid[i + dropPos.y][j + dropPos.x - 1];
                    if (!c.equals(DEFAULT) && !gridC.equals(DEFAULT)) {
                        return true;
                    }
                }
            }
        }
        //RIGHT
        if (direction == 1) {
            if (dropPos.x + 1 > col - drop[0].length) {
                return true;
            }
            for (int i = 0; i < drop.length; i++) {
                for (int j = 0; j < drop[i].length; j++) {
                    Color c = drop[i][j];
                    Color gridC = grid[i + dropPos.y][j + dropPos.x + 1];
                    if (!c.equals(DEFAULT) && !gridC.equals(DEFAULT)) {
                        return true;
                    }
                }
            }
        }
        if (direction == 2) {
            if (dropPos.y > row - drop.length) {
                return true;
            }
            if (dropPos.x > col - drop[0].length) {
                return true;
            }
            for (int i = 0; i < drop.length; i++) {
                for (int j = 0; j < drop[i].length; j++) {
                    Color c = drop[i][j];
                    Color gridC = grid[i + dropPos.y][j + dropPos.x];
                    if (!c.equals(DEFAULT) && !gridC.equals(DEFAULT)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void removeRows() {
        //Checks if any rows are full, and removes full rows
        ArrayList<Color[]> gridList = new ArrayList<Color[]>(Arrays.asList(grid));
        Collections.reverse(gridList);
        Color[] empty = new Color[col];
        for (int i = 0; i < empty.length; i++) {
            empty[i] = DEFAULT;
        }

        for (int i = 0; i < gridList.size(); i++) {
            boolean n = true;
            for (Color c : gridList.get(i)) {
                if (c.equals(DEFAULT)) {
                    n = false;
                }
            }
            if (n) {
                gridList.remove(i);
                gridList.add(empty);
                i--;
                totalLines++;
            }
        }
        System.out.println(totalLines);
        Collections.reverse(gridList);
        grid = gridList.toArray(new Color[row][col]);
    }

    public void randomPiece() {
        switch ((int) (Math.random() * 7)) {
            case 0:
                nextPiece = tetrisT.clone();
                offset = -1;
                nextColor = Color.MAGENTA;
                break;
            case 1:
                nextPiece = tetrisL.clone();
                offset = -1;
                nextColor = Color.ORANGE;
                break;
            case 2:
                nextPiece = tetrisJ.clone();
                offset = -1;
                nextColor = Color.BLUE;
                break;
            case 3:
                nextPiece = tetrisZ.clone();
                offset = -1;
                nextColor = Color.RED;
                break;
            case 4:
                nextPiece = tetrisS.clone();
                offset = -1;
                nextColor = Color.GREEN;
                break;
            case 5:
                nextPiece = tetrisO.clone();
                offset = -1;
                nextColor = Color.YELLOW;
                break;
            case 6:
                nextPiece = tetrisI.clone();
                offset = -1;
                nextColor = Color.CYAN;
                break;
        }
    }
    
    public void updateTileGrid() {
        //Updates GUI to represent grid[][] and drop[][]
        TileGrid tGrid = new TileGrid(col, row);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Tile t = new Tile(grid[i][j]);
                tGrid.setTile(j, i, t);
            }
        }
        for (int i = 0; i < drop.length; i++) {
            for (int j = 0; j < drop[i].length; j++) {
                Tile t = new Tile(drop[i][j]);
                if (!t.getColor().equals(DEFAULT)) {
                    tGrid.setTile(j + dropPos.x, i + dropPos.y, t);
                }
            }
        }
        tileGrid = tGrid;
    }

    public void rotate() {
        //Rotates piece
        Color[][] newDrop = new Color[drop[0].length][drop.length];
        for (int i = 0; i < drop.length; i++) {
            for (int j = 0; j < drop[i].length; j++) {
                newDrop[j][drop.length - 1 - i] = drop[i][j];
            }
        }
        Color[][] oldDrop = drop.clone();
        drop = newDrop.clone();
        if (checkCollision(2)) {
            drop = oldDrop;
        }
    }

    public void combineArrays() {
        for (int i = 0; i < drop.length; i++) {
            for (int j = 0; j < drop[i].length; j++) {
                Color c = drop[i][j];
                if (!c.equals(DEFAULT)) {
                    grid[i + dropPos.y][j + dropPos.x] = c;
                }
            }
        }
    }
}
