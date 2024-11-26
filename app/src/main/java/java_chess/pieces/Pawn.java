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
    boolean enPassant = false;

    // Constructor(s)
    /**
     * Creates a new instance of {@code Pawn}.
     * @param color
     */
    public Pawn(PieceColor color) {
        super(color);
        if (color == PieceColor.WHITE) {
            image = setImage("/assets/wp");
        } else {
            image = setImage("/assets/bp");
        }
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        // Cannot move a Piece on a spot that has the same color as the current one
        if (end.getPiece() != null && end.getPiece().getColor() == this.getColor()) return false;

        boolean isAtStartSpot = (start.getX() == 6 && start.getPiece().getColor() == PieceColor.WHITE || 
        start.getX() == 1 && start.getPiece().getColor() == PieceColor.BLACK); 

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
        if (end.getPiece() == null && endX == startX + direction && y == 0) {
            return true; // Move forward
        }
        if (isAtStartSpot && board.getSpot(startX + direction, startY).getPiece() == null && endX == startX + (2*direction) && y == 0) return true; // First move

        // Diagonal capture
        if (end.getPiece() != null && end.getPiece().getColor() != this.getColor() && endX == startX + direction && y == 1) {
            if (start.getPiece().getColor() == PieceColor.WHITE && direction == -1) return true;
            if (start.getPiece().getColor() == PieceColor.BLACK && direction == 1) return true;
        }

        // En passant
        if (start.getX() == 5 && direction == 1 || start.getX() == 4 && direction == -1) enPassant = true;
        return enPassant && end.getPiece() == null && board.getSpot(startX+1, startY).getPiece() instanceof Pawn && x == 1 && y == 1;
    }
    
}
