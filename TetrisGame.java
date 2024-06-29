import javax.swing.*;
import java.awt.*;

public class TetrisGame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Menu menu;
    private TetrisPanel gamePanel;

    public TetrisGame() {
        setTitle("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 600);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        menu = new Menu(this);
        gamePanel = new TetrisPanel(this);

        mainPanel.add(menu, "Menu");
        mainPanel.add(gamePanel, "Game");

        add(mainPanel);
    }

    public void showMenu() {
        cardLayout.show(mainPanel, "Menu");
    }

    public void startGame() {
        cardLayout.show(mainPanel, "Game");
        gamePanel.startGame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TetrisGame game = new TetrisGame();
            game.setVisible(true);
        });
    }
}