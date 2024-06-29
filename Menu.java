import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private TetrisGame mainGame;

    public Menu(TetrisGame game) {
        mainGame = game;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(300, 600)); // Match the game size

        JLabel title = new JLabel("Tetris");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton playButton = new JButton("Play");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.addActionListener(e -> mainGame.startGame());

        JButton quitButton = new JButton("Quit");
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.addActionListener(e -> System.exit(0));

        add(Box.createVerticalGlue());
        add(title);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(playButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(quitButton);
        add(Box.createVerticalGlue());
    }
}