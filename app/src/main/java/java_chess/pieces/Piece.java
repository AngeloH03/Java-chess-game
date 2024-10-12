package java_chess.pieces;

/**
 * The {@code Piece} class provides all the properties that are used
 * to create a piece in a chess game.
 */
public abstract class Piece {
    boolean isWhite;
    boolean isKilled;

    /**
     * Create a new instance of {@code Piece}.
     * @param isWhite
     */
    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }



    
}
