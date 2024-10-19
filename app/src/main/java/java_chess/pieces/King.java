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
  
        return this.isValidCastling(board, start, end); 
    } 
  
    private boolean isValidCastling(Board board, Spot start, Spot end) { 
  
        if (this.isCastlingDone()) { 
            return false; 
        }
  
        int x = Math.abs(start.getX() - end.getX());

        try {
            // Checks wether the king can move two squares aside and if there is a rook further
            if (firstMove 
                && x == 2 && board.getSpot(x + 2, end.getY()).getPiece() != null 
                || x == 2 && board.getSpot(x + 3, end.getY()).getPiece() != null) {
                return true;
            }
        } catch (Exception e) {}
        return false;
    }

}