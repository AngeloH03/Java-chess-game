package java_chess.pieces;

import java_chess.board.Board;
import java_chess.board.Spot;

/**
 * The {@code Piece} class provides all the properties that are used
 * to create a piece in a chess game.
 */
public abstract class Piece {
    // Attributes
    PieceColor color;
    boolean killed;

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
     * Checks wether the {@code Piece} is killed or not.
     * @return boolean
     */
    public boolean isKilled() {
        return killed;
    }

    // Setters
    /**
     * Sets the {@code isWhite} attribute of a {@code Piece}.
     * @param PieceColor
     */
    public void setColor(PieceColor color) {
        this.color = color;
    }

    /**
     * Sets the {@code isKilled} attribute of a {@code Piece}.
     * @param isWhite
     */
    public void setIsKilled(boolean killed) {
        this.killed = killed;
    }

    /**
     * Checks wether a {@code Piece} is able to move or not.
     * @param board
     * @param start
     * @param end
     * @return
     */
    public abstract boolean canMove(Board board, Spot start, Spot end);
}
