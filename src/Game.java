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
    private Color nextColor;

    private Color grid[][];
    private Color drop[][];
    private Point dropPos;
    private int rotate;
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
        randomPiece();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                grid[i][j] = DEFAULT;
            }
        }
    }

    public void run() {
        int frame = 0;
        int rotate = 0;
        while(true) {
            long time = System.currentTimeMillis();

            Input in = display.getInput();

            //LOOP

            if(!isDropping) {
                if (checkCollision()) {
                    removeRow();
                } else {
                    startDrop();
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
                if(rotate < 4) {
                    rotate++;
                } else {
                    rotate = 0;
                }
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
    public void startDrop() {
        dropPos = new Point(col/2,0);
        drop = new Color[nextPiece.length][nextPiece[0].length];
        for(int i = 0; i < nextPiece.length; i++) {
            for(int j = 0; j < nextPiece[i].length; j++) {
                if(nextPiece[i][j] == 1) {
                    drop[i][j] = nextColor;
                } else {
                    drop[i][j] = DEFAULT;
                }
            }
        }
        isDropping = true;
        randomPiece();
    }
    public void drop() {
        dropPos.y--;
    }
    public void moveLeft() {
        dropPos.x--;
    }
    public void moveRight() {
        dropPos.x++;
    }
    public boolean checkCollision() {
        //Checks for collision, allows to slide left or right for a few seconds
        return false;
    }
    public void removeRow() {
        //Clears bottom row
    }
    public void randomPiece() {
        switch((int) (Math.random() * 7)) {
            case 0:
                nextPiece = tetrisT.clone();
                nextColor = Color.MAGENTA;
                break;
            case 1:
                nextPiece = tetrisL.clone();
                nextColor = Color.ORANGE;
                break;
            case 2:
                nextPiece = tetrisJ.clone();
                nextColor = Color.BLUE;
                break;
            case 3:
                nextPiece = tetrisZ.clone();
                nextColor = Color.RED;
                break;
            case 4:
                nextPiece = tetrisS.clone();
                nextColor = Color.GREEN;
                break;
            case 5:
                nextPiece = tetrisO.clone();
                nextColor = Color.YELLOW;
                break;
            case 6:
                nextPiece = tetrisI.clone();
                nextColor = Color.CYAN;
                break;
        }
    }
    public int[][] getNextPiece() {
        return nextPiece;
    }
    public Color getNextColor() {
        return nextColor;
    }
    public void updateTileGrid() {

    }
}
