public class Main {
    public static void main(String[] args) {
        Display d  = new Display("Tetris");
        while(true) {
            Game game = new Game(d);
            game.run();
        }
    }
}