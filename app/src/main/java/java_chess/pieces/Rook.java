package java_chess.pieces;

import java_chess.board.Board;
import java_chess.board.Spot;

/**
 * The {@code Rook} class represents a Rook piece
 * from a chess game.
 * 
 * Inherits from {@code Piece}.
 */
public class Rook extends Piece {
    private boolean castlingDone = false;
    private final boolean firstMove = true;

    /**
     * Creates a new instance of {@code Rook}.
     * @param color
     */
    public Rook(PieceColor color) {
        super(color);
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
    public boolean canMove(Board board, Spot start, Spot end) throws Exception {
        // Cannot move a Piece on a spot that has the same color as the current one
        if (end.getPiece().getColor() == this.getColor()) return false;

        int x = Math.abs(start.getX() - end.getX()); // Distance X
        int y = Math.abs(start.getY() - end.getY()); // Distance Y

        // Moveset
        if (x != 0 && y != 0) return false; // Can only move vertically or horizontally
        for (int row = start.getX(); row < end.getX(); row++) {
            for (int col = start.getY(); col < end.getY(); col++) {
                if (board.getSpot(row, col).getPiece() == null) {
                    if (x >= 1 && y == 0) return true; // Horizontal movement
                    if (x == 0 && y >= 1) return true; // Vertical movement
                }
            }
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