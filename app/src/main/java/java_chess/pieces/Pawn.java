package java_chess.pieces;

import java_chess.board.Board;
import java_chess.board.Spot;

/**
 * The {@code Pawn} class represents a Pawn piece
 * from a chess game.
 * 
 * Inherits from {@code Piece}.
 */
public class Pawn extends Piece {
    // Attributes
    boolean firstMove = true;
    boolean enPassant = false;

    // Constructor(s)
    /**
     * Creates a new instance of {@code Pawn}.
     * @param color
     */
    public Pawn(PieceColor color) {
        super(color);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) throws Exception {
        // Cannot move a Piece on a spot that has the same color as the current one
        if (end.getPiece() != null && end.getPiece().getColor() == this.getColor()) return false;

        // Start
        int startX = start.getX();
        int startY = start.getY();
        
        // End
        int endX = end.getX();
        int endY = end.getY();

        // Distances
        int x = Math.abs(endX - startX);
        int y = Math.abs(endY - startY);

        int direction = this.getColor() == PieceColor.WHITE ? -1 : 1;

        // Moveset
        if (x == 0 && endY == startY + direction) return true; // Move forward
        if (firstMove && x == 0 && endY == startY + (2*direction)) return true; // First move

        // Diagonal capture
        if (end.getPiece() != null && end.getPiece().getColor() != this.getColor() && x == 1 && y == 1) return true;

        // En passant
        if (start.getY() == 5 && direction == 1 || start.getY() == 4 && direction == -1) enPassant = true;

        if (enPassant && end.getPiece() == null && board.getSpot(startX+1, startY).getPiece() instanceof Pawn && x == 1 && y == 1) return true;

        return false;
    }
    
}
