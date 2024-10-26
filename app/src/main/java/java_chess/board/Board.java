package java_chess.board;

import java_chess.pieces.Bishop;
import java_chess.pieces.King;
import java_chess.pieces.Knight;
import java_chess.pieces.Pawn;
import java_chess.pieces.PieceColor;
import java_chess.pieces.Queen;
import java_chess.pieces.Rook;

/**
 * The {@code Board} class creates an array of 8*8 squares where
 * instances of {@code Piece} can be placed
 */
public class Board {
    Spot[][] boxes;

    public Board() {
        this.resetBoard();
    }

    /**
     * Returns a specific {@code Spot} on the board
     * @param y
     * @param x
     * @return {@code Spot}
     * @throws Exception
     */
    public Spot getSpot(int x, int y) throws Exception {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new Exception("Index out of bound");
        }
        return boxes[x][y];
    }

    /**
     * Sets the board back to its initial state
     */
    private void resetBoard() {
        // White Pieces
        boxes[0][0] = new Spot(0,0, new Rook(PieceColor.WHITE));
        boxes[1][0] = new Spot(1,0, new Knight(PieceColor.WHITE));
        boxes[2][0] = new Spot(2,0, new Bishop(PieceColor.WHITE));
        boxes[3][0] = new Spot(3,0, new Queen(PieceColor.WHITE));
        boxes[4][0] = new Spot(4,0, new King(PieceColor.WHITE));
        boxes[5][0] = new Spot(5,0, new Bishop(PieceColor.WHITE));
        boxes[6][0] = new Spot(6,0, new Knight(PieceColor.WHITE));
        boxes[7][0] = new Spot(7,0, new Rook(PieceColor.WHITE));

        boxes[0][1] = new Spot(0, 1, new Pawn(PieceColor.WHITE));
        boxes[1][1] = new Spot(1, 1, new Pawn(PieceColor.WHITE));
        boxes[2][1] = new Spot(2, 1, new Pawn(PieceColor.WHITE));
        boxes[3][1] = new Spot(3, 1, new Pawn(PieceColor.WHITE));
        boxes[4][1] = new Spot(4, 1, new Pawn(PieceColor.WHITE));
        boxes[5][1] = new Spot(5, 1, new Pawn(PieceColor.WHITE));
        boxes[6][1] = new Spot(6, 1, new Pawn(PieceColor.WHITE));
        boxes[7][1] = new Spot(7, 1, new Pawn(PieceColor.WHITE));

        // Black Pieces
        boxes[0][6] = new Spot(0, 6, new Pawn(PieceColor.BLACK));
        boxes[1][6] = new Spot(1, 6, new Pawn(PieceColor.BLACK));
        boxes[2][6] = new Spot(2, 6, new Pawn(PieceColor.BLACK));
        boxes[3][6] = new Spot(3, 6, new Pawn(PieceColor.BLACK));
        boxes[4][6] = new Spot(4, 6, new Pawn(PieceColor.BLACK));
        boxes[5][6] = new Spot(5, 6, new Pawn(PieceColor.BLACK));
        boxes[6][6] = new Spot(6, 6, new Pawn(PieceColor.BLACK));
        boxes[7][6] = new Spot(7, 6, new Pawn(PieceColor.BLACK));

        boxes[0][7] = new Spot(0,7, new Rook(PieceColor.BLACK));
        boxes[1][7] = new Spot(1,7, new Knight(PieceColor.BLACK));
        boxes[2][7] = new Spot(2,7, new Bishop(PieceColor.BLACK));
        boxes[3][7] = new Spot(3,7, new Queen(PieceColor.BLACK));
        boxes[4][7] = new Spot(4,7, new King(PieceColor.BLACK));
        boxes[5][7] = new Spot(5,7, new Bishop(PieceColor.BLACK));
        boxes[6][7] = new Spot(6,7, new Knight(PieceColor.BLACK));
        boxes[7][7] = new Spot(7,7, new Rook(PieceColor.BLACK));

        for (int y = 2; y < 6; y++) {
            for (int x = 0; x < 8; x++) {
                boxes[x][y] = new Spot(x, y, null);
            }
        }
    }
}
