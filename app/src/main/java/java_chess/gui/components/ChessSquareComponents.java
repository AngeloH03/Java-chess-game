package java_chess.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * The {@code ChessSquareComponents} class is a JButton component that
 * contain a chess piece
 */
public class ChessSquareComponents extends JButton{
    // Attributes
    private int row;
    private int col;

    // Constructor(s)
    public ChessSquareComponents(int row, int col) {
        this.row = row;
        this.col = col;
        initButton();
    }

    // Methods
    /**
     * Initialize component
     */
    private void initButton() {
        // Set fixed size for uniformity
        setPreferredSize(new Dimension(100, 100));

        // Set background color based on row and col for checkboard effects
        if ((row + col) % 2 == 0) setBackground(new Color(240, 217, 181));
        else setBackground(new Color(181, 136, 99));

        // Ensure text (Chess piece symbols) are centered
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);

        // Font settings
        setFont(new Font("Serif", Font.BOLD, 24));
    }

    /**
     * Set piece's symbol
     * @param symbol
     * @param color
     */
    public void setPieceSymbols(Image img, Color color) {
        Image processImage = img.getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT);
        this.setIcon(new ImageIcon(processImage));
        this.setForeground(color);
    }

    /**
     * Clear piece
     */
    public void clearPieceSymbols() {
        this.setIcon(null);
    }
}
