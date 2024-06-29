import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TetrisPanel extends JPanel implements ActionListener {
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 20;
    private static final int BLOCK_SIZE = 30;

    private Timer timer;
    private GameBoard gameBoard;
    private TetrisGame mainGame;

    public TetrisPanel(TetrisGame game) {
        mainGame = game;
        setPreferredSize(new Dimension(BOARD_WIDTH * BLOCK_SIZE, BOARD_HEIGHT * BLOCK_SIZE));
        setBackground(Color.BLACK);
        gameBoard = new GameBoard(BOARD_WIDTH, BOARD_HEIGHT);

        timer = new Timer(500, this);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        gameBoard.moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        gameBoard.moveRight();
                        break;
                    case KeyEvent.VK_DOWN:
                        gameBoard.moveDown();
                        break;
                    case KeyEvent.VK_UP:
                        gameBoard.rotate();
                        break;
                    case KeyEvent.VK_SPACE:
                        gameBoard.dropDown();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        mainGame.showMenu();
                        timer.stop();
                        break;
                }
                repaint();
            }
        });

        setFocusable(true);
    }

    public void startGame() {
        gameBoard.newGame();
        timer.start();
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameBoard.draw(g, BLOCK_SIZE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameBoard.update();
        if (gameBoard.isGameOver()) {
            timer.stop();
            int option = JOptionPane.showConfirmDialog(this, "Game Over! Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                startGame();
            } else {
                mainGame.showMenu();
            }
        }
        repaint();
    }
}