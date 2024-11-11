package java_chess.pieces;

import java_chess.board.Board;
import java_chess.board.Spot;

/**
 * The {@code Knight} class represents a Knight piece
 * from a chess game.
 * 
 * Inherits from {@code Piece}.
 */
public class Knight extends Piece {

    /**
     * Creates a new instance of {@code Knight}.
     * @param color
     */
    public Knight(PieceColor color) {
        super(color);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        // Cannot move a Piece on a spot that has the same color as the current one
        if (end.getPiece() != null && end.getPiece().getColor() == this.getColor()) return false;

        // Start
        int startX = start.getX();
        int startY = start.getY();

        // End
        int endX = end.getX();
        int endY = end.getY();

        // Distance
        int x = Math.abs(endX - startX);
        int y = Math.abs(endY - startY);
        
        // Moveset
        return x == 2 && y == 1 || x == 1 && y == 2; // L moveset
    }
    
}
