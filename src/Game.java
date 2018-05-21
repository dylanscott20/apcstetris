import java.awt.*;
import java.awt.event.KeyEvent;

public class Game {
    private final Color DEFAULT = Color.LIGHT_GRAY;
    private final int FPS = 30;
    private final int col = 10;
    private final int row = 20;

    private int tetrisT[][] = {{1,1,1},{0,1,0}};
    private int tetrisL[][] = {{1,0,0},{1,1,1}};
    private int tetrisJ[][] = {{0,0,1},{1,1,1}};
    private int tetrisZ[][] = {{1,1,0}, {0,1,1}};
    private int tetrisS[][] = {{0,1,1}, {1,1,0}};
    private int tetrisO[][] = {{1,1}, {1,1}};
    private int tetrisI[][] = {{1},{1},{1},{1}};
    private int nextPiece[][];

    private Color grid[][];
    private Color drop[][];
    private TileGrid tileGrid;
    private Display display;
    private Score score;

    private boolean isDropping = false;
    private int speed = 30;
    private int keySpeed = 15;

    public Game() {
        display = new Display("Test");
        tileGrid = new TileGrid(col, row);
        grid = new Color[row+2][col];
        nextPiece = randomPiece().clone();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                grid[i][j] = DEFAULT;
            }
        }
    }

    public void run() {
        int frame = 0;
        while(true) {
            long time = System.currentTimeMillis();

            Input in = display.getInput();

            //LOOP

            if(!isDropping) {
                if (checkCollision()) {
                    removeRow();
                } else {
                    startDrop(nextPiece);
                }
            } else {
                if(frame % speed == 0 || in.isKeyWait(KeyEvent.VK_DOWN,5)) {
                    drop();
                }
            }

            if(in.isKeyWait(KeyEvent.VK_LEFT,15)) {
                moveLeft();
            }
            if(in.isKeyWait(KeyEvent.VK_RIGHT,15)) {
                moveRight();
            }
            if(in.isKeyWait(KeyEvent.VK_UP,15)) {
                rotate();
            }

            display.setTileGrid(tileGrid);
            display.setVisible(true);
            frame++;
            
            time = (1000/FPS) - (System.currentTimeMillis() - time);
            if(time > 0) {
                Helper.waitTime((int) time);
            }
        }
    }
    public void startDrop(int[][] piece) {
        //Puts a random piece in a separate array
        //Sets isDropping to true
        //Sets next piece to new random piece
    }
    public void drop() {
        //Moves the dropped piece down one tile
    }
    public void moveLeft() {
        //Moves piece to left
    }
    public void moveRight() {
        //Moves piece to right
    }
    public void rotate() {
        //Rotates piece clockwise
    }
    public boolean checkCollision() {
        //Checks for collision, allows to slide left or right for a few seconds
        return false;
    }
    public void removeRow() {
        //Clears bottom row
    }
    public int[][] randomPiece() {
        //Picks a random piece
        return tetrisI;
    }
    public int[][] getNextPiece() {
        return nextPiece;
    }
    public void updateTileGrid() {

    }
}
