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

        // Start
        int startX = start.getX();
        int startY = start.getY();

        // End
        int endX = end.getX();
        int endY = end.getY();

        // Distances
        int x = Math.abs(end.getX() - start.getX());
        int y = Math.abs(end.getY() - start.getY());

        // Moveset
        if (x == 0) { // Vertical movement
            int direction = (endY - startY) > 0 ? 1 : -1;
            for (int i = startY + direction; i != endY; i += direction) {
                if (board.getSpot(startX, i).getPiece() != null) return false;
            }
        } else if (y == 0) { // Horizontal movement
            int direction = (endX - startX) > 0 ? 1 : -1;
            for (int i = startX + direction; i != endY; i += direction) {
                if (board.getSpot(i, startY).getPiece() != null) return false;
            }
        } else if (x != 0 && y != 0) { // Diagonal movement
            int directionX = (endX - startX) > 0 ? 1 : -1;
            int directionY = (endY - startY) > 0 ? 1 : -1;
            int currentX = startX + directionX;
            int currentY = startY + directionY;
            for (int row = startX + directionX; row < endX; row += directionX) {
                for (int col = startY + directionY; col < endY; col += directionY) {
                    if (row == col) {
                        if (board.getSpot(row, col).getPiece() != null) return false;
                    }
                }
            }
        }

        if (x > 1 && y == 1) return false;
        if (x == 1 && y > 1) return false;

        return true;
    }

}
