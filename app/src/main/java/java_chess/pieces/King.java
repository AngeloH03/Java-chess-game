package java_chess.pieces;

import java_chess.board.Board;
import java_chess.board.Spot;

/**
 * The {@code King} class represents a King piece
 * from a chess game.
 * 
 * Inherits from {@code Piece}.
 */
public class King extends Piece { 
    // Attributes
    private boolean castlingDone = false; 
    private final boolean firstMove = true;
  
    // Constructor(s)
    /**
     * Creates a new instance of {@code King}.
     * @param white
     */
    public King(boolean white) { 
        super(white); 
    }
  
    // Getters
    public boolean isCastlingDone() { 
        return this.castlingDone; 
    }
  
    // Setters
    public void setCastlingDone(boolean castlingDone) { 
        this.castlingDone = castlingDone; 
    }
  
    @Override
    public boolean canMove(Board board, Spot start, Spot end) { 
        // Cannot move a Piece on a spot that has the same color as the current one
        if (end.getPiece().isWhite() == this.isWhite()) return false;
  
        int x = Math.abs(start.getX() - end.getX()); 
        int y = Math.abs(start.getY() - end.getY()); 
        if (x <= 1 && y <= 1) {
            // check if this move will not result in the king 
            // being attacked if so return true 
            return true; 
        }
        if (x == 2 && y == 0) return this.isValidCastling(board, start, end); // Check for castling
        return false;
    } 
  
    private boolean isValidCastling(Board board, Spot start, Spot end) { 
        if (this.isCastlingDone()) {
            return false; 
        }

        int x = Math.abs(start.getX() - end.getX()); // Distance X
        
        if (!this.firstMove) return false;
        if (x != 2) return false; // The piece is moving exactly 2 squares ahead

        if (end.getX() > start.getX()) { // Kingside
            try {
                // Check if there's no piece between king and rook
                if (board.getSpot(5, start.getY()).getPiece() != null ||
                    board.getSpot(6, start.getY()).getPiece() != null) return false;
            } catch (Exception e) {}
        } else { // Queenside
            try {
                // Check if there's no piece between king and rook
                if (board.getSpot(1, start.getY()).getPiece() != null || 
                    board.getSpot(2, start.getY()).getPiece() != null) return false;
            } catch (Exception e) {}
        }
        return true;
    }

}