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
    public boolean canMove(Board board, Spot start, Spot end) {
        // Cannot move a Piece on a spot that has the same color as the current one
        if (end.getPiece().getColor() == color) return false;

        // Start
        int startX = start.getX();
        int startY = start.getY();
        
        // End
        int endX = end.getX();
        int endY = end.getY();

        int direction = color == PieceColor.WHITE ? -1 : 1;

        // Moveset
        // Move forward
        if (startX == endX && endY == startY + direction && end.getPiece() == null) return true;

        // First move
        if (firstMove && startX == endX && endY == startY + (2*direction) && end.getPiece() == null) return true;
        
        // En passant
        return enPassant && Math.abs(endX - startX) == 1 && endY == startY + direction && end.getPiece() == null;
    };
    
}
