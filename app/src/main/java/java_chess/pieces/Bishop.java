package java_chess.pieces;

import java_chess.board.Board;
import java_chess.board.Spot;

/**
 * The {@code Rook} class represents a Rook piece
 * from a chess game.
 * 
 * Inherits from {@code Piece}.
 */
public class Bishop extends Piece {

    /**
     * Creates a new instance of {@code Bishop}.
     * @param color
     */
    public Bishop(PieceColor color) {
        super(color);
        if (color == PieceColor.WHITE) {
            image = getImage("/assets/wb");
        } else {
            image = getImage("/assets/bb");
        }
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) throws Exception {
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

        // Moveset
        if (x != 0 && y != 0) { // Diagonal movement
            int directionX = (endX - startX) > 0 ? 1 : -1;
            int directionY = (endY - startY) > 0 ? 1 : -1;
            int currentX = startX + directionX;
            int currentY = startY + directionY;

            while (currentX != endX && currentY != endY) {
                if (board.getSpot(currentX, currentY).getPiece() != null) return false;
                currentX += directionX;
                currentY += directionY;
            }
        }
        if (x != y) return false; // No horizontal or vertical movement
        
        return true;
    }

}
