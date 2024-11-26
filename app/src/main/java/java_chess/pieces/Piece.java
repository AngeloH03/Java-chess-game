package java_chess.pieces;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java_chess.board.Board;
import java_chess.board.Spot;

/**
 * The {@code Piece} class provides all the properties that are used
 * to create a piece in a chess game.
 */
public abstract class Piece {
    // Attributes
    public BufferedImage image;
    protected PieceColor color;

    // Constructor(s)
    /**
     * Create a new instance of {@code Piece}.
     * @param color
     */
    public Piece(PieceColor color) {
        this.color = color;
    }

    // Getters
    /**
     * Checks wether the {@code Piece} is white or not.
     * @return boolean
     */
    public PieceColor getColor() {
        return color;
    }

    /**
     * Returns an image of which path is passed as an argument.
     * @param path
     * @return
     */

    // Setters
    /**
     * Sets the {@code color} attribute of a {@code Piece}.
     * @param PieceColor
     */
    public void setColor(PieceColor color) {
        this.color = color;
    }

    /**
     * Sets a {@code Piece}'s image.
     * @param path
     * @return
     */
    public BufferedImage setImage(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * Checks wether a {@code Piece} is able to move or not.
     * @param board
     * @param start
     * @param end
     * @return boolean
     */
    public abstract boolean canMove(Board board, Spot start, Spot end);
}
