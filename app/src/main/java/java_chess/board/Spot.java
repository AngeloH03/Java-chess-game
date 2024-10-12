package java_chess.board;

import java_chess.pieces.Piece;

/**
 * The {@code Spot} class represents a spot on the board.
 * 
 * It can contain a piece and has its own coordinates.
 */
public class Spot {
    private Piece piece;
    private int x;
    private int y;

    // Constructor(s)
    /**
     * Creates a new instance of {@code Spot}.
     * @param x
     * @param y
     * @param piece
     */
    Spot(int x, int y, Piece piece) {
        this.piece = piece;
        this.x = x;
        this.y = y;
    }

    // Getters
    /**
     * Returns the current piece of the spot.
     * @return {@code Piece}
     */
    Piece getPiece() {
        return this.piece;
    }

    /**
     * Returns the X position of the current 
     * spot on the board.
     * @return {@code int}
     */
    int getX() {
        return this.x;
    }

    /**
     * Returns the Y position of the current 
     * spot on the board.
     * @return {@code int}
     */
    int getY() {
        return this.y;
    }

    // Setters
    /**
     * Sets the current {@code Piece} of the spot.
     * @param piece
     */
    void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Sets the X position of the current spot.
     * @param x
     */
    void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the Y position of the current spot.
     * @param y
     */
    void setY(int y) {
        this.y = y;
    }
}