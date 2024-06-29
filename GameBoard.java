import java.awt.*;
import java.util.Random;

public class GameBoard {
    private int width;
    private int height;
    private Color[][] board;
    private Tetromino currentPiece;
    private Random random;
    private int score;

    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        board = new Color[height][width];
        random = new Random();
    }

    public void newGame() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = Color.BLACK;
            }
        }
        score = 0;
        spawnNewPiece();
    }

    private void spawnNewPiece() {
        currentPiece = new Tetromino(random.nextInt(Tetromino.SHAPES.length));
    }

    public void update() {
        if (canMove(0, 1)) {
            currentPiece.setY(currentPiece.getY() + 1);
        } else {
            placePiece();
            clearLines();
            spawnNewPiece();
        }
    }

    public void moveLeft() {
        if (canMove(-1, 0)) {
            currentPiece.setX(currentPiece.getX() - 1);
        }
    }

    public void moveRight() {
        if (canMove(1, 0)) {
            currentPiece.setX(currentPiece.getX() + 1);
        }
    }

    public void moveDown() {
        if (canMove(0, 1)) {
            currentPiece.setY(currentPiece.getY() + 1);
        }
    }

    public void rotate() {
        currentPiece.rotate();
        if (!canMove(0, 0)) {
            currentPiece.rotate();
            currentPiece.rotate();
            currentPiece.rotate();
        }
    }

    public void dropDown() {
        while (canMove(0, 1)) {
            currentPiece.setY(currentPiece.getY() + 1);
        }
        placePiece();
        clearLines();
        spawnNewPiece();
    }

    private boolean canMove(int dx, int dy) {
        int[][] shape = currentPiece.getShape();
        int newX = currentPiece.getX() + dx;
        int newY = currentPiece.getY() + dy;

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    int x = newX + j;
                    int y = newY + i;
                    if (x < 0 || x >= width || y >= height || (y >= 0 && board[y][x] != Color.BLACK)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void placePiece() {
        int[][] shape = currentPiece.getShape();
        Color color = currentPiece.getColor();

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    int x = currentPiece.getX() + j;
                    int y = currentPiece.getY() + i;
                    if (y >= 0) {
                        board[y][x] = color;
                    }
                }
            }
        }
    }

    private void clearLines() {
        for (int i = height - 1; i >= 0; i--) {
            boolean lineIsFull = true;
            for (int j = 0; j < width; j++) {
                if (board[i][j] == Color.BLACK) {
                    lineIsFull = false;
                    break;
                }
            }
            if (lineIsFull) {
                for (int k = i; k > 0; k--) {
                    System.arraycopy(board[k - 1], 0, board[k], 0, width);
                }
                for (int j = 0; j < width; j++) {
                    board[0][j] = Color.BLACK;
                }
                score += 100;
                i++;
            }
        }
    }

    public boolean isGameOver() {
        return !canMove(0, 0);
    }

    public void draw(Graphics g, int blockSize) {
        // Draw the board
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                g.setColor(board[i][j]);
                g.fillRect(j * blockSize, i * blockSize, blockSize, blockSize);
                g.setColor(Color.DARK_GRAY);
                g.drawRect(j * blockSize, i * blockSize, blockSize, blockSize);
            }
        }

        // Draw the current piece
        int[][] shape = currentPiece.getShape();
        g.setColor(currentPiece.getColor());
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    int x = (currentPiece.getX() + j) * blockSize;
                    int y = (currentPiece.getY() + i) * blockSize;
                    g.fillRect(x, y, blockSize, blockSize);
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(x, y, blockSize, blockSize);
                    g.setColor(currentPiece.getColor());
                }
            }
        }

        // Draw the score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 25);
    }
}