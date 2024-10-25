package java_chess.pieces;

import java_chess.board.Board;
import java_chess.board.Spot;

public class Knight extends Piece {

    public Knight(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        // Cannot move a Piece on a spot that has the same color as the current one
        if (end.getPiece().isWhite() == this.isWhite()) return false;

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
        return endX == startX + 2 && y == 1 || endY == startY +2 && x == 1;
    }
    
}
