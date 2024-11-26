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
        if (color == PieceColor.WHITE) {
            image = setImage("/assets/wq");
        } else {
            image = setImage("/assets/bq");
        }
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        // Cannot move a Piece on a spot that has the same color as the current one
        if (end.getPiece() != null && end.getPiece().getColor() == this.getColor()) return false;

        // Start
        int startX = start.getX();
        int startY = start.getY();

        // End
        int endX = end.getX();
        int endY = end.getY();

        // Distances
        int x = Math.abs(end.getX() - start.getX());
        int y = Math.abs(end.getY() - start.getY());

        // Directions
        int directionX = (endX - startX) > 0 ? 1 : -1;
        int directionY = (endY - startY) > 0 ? 1 : -1;

        // Moveset
        if (x == 0) { // Vertical movement
            for (int col = startY + directionY; col != endY; col += directionY) {
                if (board.getSpot(startX, col).getPiece() != null) return false;
            }
        } else if (y == 0) { // Horizontal movement
            for (int row = startX + directionX; row != endX; row += directionX) {
                if (board.getSpot(row, startY).getPiece() != null) return false;
            }
        } else if (x != 0 && y != 0) { // Diagonal movement
            int currentX = startX + directionX;
            int currentY = startY + directionY;

            if (x != y) return false;
            while (currentX != endX && currentY != endY) {
                if (board.getSpot(currentX, currentY).getPiece() != null) return false;
                currentX += directionX;
                currentY += directionY;
            }
        }
        
        return true;
    }

}
