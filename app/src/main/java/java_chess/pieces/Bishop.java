package java_chess.pieces;

import java_chess.board.Board;
import java_chess.board.Spot;

public class Bishop extends Piece {

    public Bishop(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        // Cannot move a Piece on a spot that has the same color as the current one
        if (end.getPiece().isWhite() == this.isWhite()) return false;
        
        // Distances
        int x = Math.abs(end.getX() - start.getX());
        int y = Math.abs(end.getY() - start.getY());

        // Moveset
        if (x >= 1 && y >= 1) return true; // Diagonnal movement
        if (x >= 1 && y == 0) return false; // Can't move horizontally
        if (x == 0 && y >= 1) return false; // Can't move vertically
        return false;
    }

}
