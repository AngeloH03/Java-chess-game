package java_chess.pieces;

import java_chess.board.Board;
import java_chess.board.Spot;

public class King extends Piece { 
    private boolean castlingDone = false; 
    private boolean firstMove = true;
  
    public King(boolean white) { 
        super(white); 
    } 
  
    public boolean isCastlingDone() { 
        return this.castlingDone; 
    } 
  
    public void setCastlingDone(boolean castlingDone) { 
        this.castlingDone = castlingDone; 
    } 
  
    @Override
    public boolean canMove(Board board, Spot start, Spot end) { 
        // we can't move the piece to a Spot that  
        // has a piece of the same color 
        if (end.getPiece().isWhite() == this.isWhite()) { 
            return false; 
        } 
  
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}