package java_chess.pieces;

import java_chess.board.Board;
import java_chess.board.Spot;

/**
 * The {@code Queen} class represents a Queen piece
 * from a chess game.
 * 
 * Inherits from {@code Piece}.
 */
public class Queen extends Piece {

    /**
     * Creates a new instance of {@code Queen}.
     * @param color
     */
    public Queen(PieceColor color) {
        super(color);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) throws Exception {
        // Cannot move a Piece on a spot that has the same color as the current one
        if (end.getPiece().getColor() == this.getColor()) return false;

        // Distances
        int x = Math.abs(end.getX() - start.getX());
        int y = Math.abs(end.getY() - start.getY());

        // Moveset
        for (int row = start.getX(); row < end.getX(); row++) {
            for (int col = start.getY(); col < end.getY(); col++) {
                if (board.getSpot(row, col).getPiece() == null) {
                    if (x >= 1 && y == 0) return true; // Horizontal movement
                    if (x == 0 && y >= 1) return true; // Vertical movement
                    if (x >= 1 && y >= 1) return true; // Diagonal movement
                }
            }
        }
        if (x >= 1 && y == 1) return false;
        if (x == 1 && y >= 1) return false;

        return false;
    }

}
