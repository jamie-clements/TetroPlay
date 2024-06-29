import java.awt.*;

public class Tetromino {
    private int[][] shape;
    private Color color;
    private int x, y;

    public static final int[][][] SHAPES = {
        {{1, 1, 1, 1}},                         // I
        {{1, 1}, {1, 1}},                       // O
        {{1, 1, 1}, {0, 1, 0}},                 // T
        {{1, 1, 1}, {1, 0, 0}},                 // L
        {{1, 1, 1}, {0, 0, 1}},                 // J
        {{0, 1, 1}, {1, 1, 0}},                 // S
        {{1, 1, 0}, {0, 1, 1}}                  // Z
    };

    public static final Color[] COLORS = {
        Color.CYAN, Color.YELLOW, Color.MAGENTA, Color.ORANGE,
        Color.BLUE, Color.GREEN, Color.RED
    };

    public Tetromino(int shapeIndex) {
        shape = SHAPES[shapeIndex];
        color = COLORS[shapeIndex];
        x = 4;
        y = 0;
    }

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void rotate() {
        int[][] rotated = new int[shape[0].length][shape.length];
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                rotated[j][shape.length - 1 - i] = shape[i][j];
            }
        }
        shape = rotated;
    }
}