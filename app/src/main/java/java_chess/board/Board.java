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
 * instances of {@code Piece} can be placed.
 */
public class Board {
    // Attributes
    private Spot[][] boxes = {};

    // Constructor(s)
    public Board() {
        boxes = new Spot[8][8];
        this.resetBoard();
    }

    // Getters
    /**
     * Returns a specific {@code Spot} on the board
     * @param y
     * @param x
     * @return {@code Spot}
     * @throws Exception
     */
    public Spot getSpot(int x, int y) {
        /* if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new Exception("Index out of bound");
        } */
        return boxes[x][y];
    }

    public Spot[][] getBoxes() {
        return this.boxes;
    }

    // Setters
    /**
     * Sets the board back to its initial state
     */
    private void resetBoard() {
        // White Pieces
        boxes[0][0] = new Spot(0,0, new Rook(PieceColor.WHITE));
        boxes[0][1] = new Spot(0,1, new Knight(PieceColor.WHITE));
        boxes[0][2] = new Spot(0,2, new Bishop(PieceColor.WHITE));
        boxes[0][3] = new Spot(0,3, new Queen(PieceColor.WHITE));
        boxes[0][4] = new Spot(0,4, new King(PieceColor.WHITE));
        boxes[0][5] = new Spot(0,5, new Bishop(PieceColor.WHITE));
        boxes[0][6] = new Spot(0,6, new Knight(PieceColor.WHITE));
        boxes[0][7] = new Spot(0,7, new Rook(PieceColor.WHITE));

        boxes[1][0] = new Spot(1, 0, new Pawn(PieceColor.WHITE));
        boxes[1][1] = new Spot(1, 1, new Pawn(PieceColor.WHITE));
        boxes[1][2] = new Spot(1, 2, new Pawn(PieceColor.WHITE));
        boxes[1][3] = new Spot(1, 3, new Pawn(PieceColor.WHITE));
        boxes[1][4] = new Spot(1, 4, new Pawn(PieceColor.WHITE));
        boxes[1][5] = new Spot(1, 5, new Pawn(PieceColor.WHITE));
        boxes[1][6] = new Spot(1, 6, new Pawn(PieceColor.WHITE));
        boxes[1][7] = new Spot(1, 7, new Pawn(PieceColor.WHITE));

        // Black Pieces
        boxes[6][0] = new Spot(6, 0, new Pawn(PieceColor.BLACK));
        boxes[6][1] = new Spot(6, 1, new Pawn(PieceColor.BLACK));
        boxes[6][2] = new Spot(6, 2, new Pawn(PieceColor.BLACK));
        boxes[6][3] = new Spot(6, 3, new Pawn(PieceColor.BLACK));
        boxes[6][4] = new Spot(6, 4, new Pawn(PieceColor.BLACK));
        boxes[6][5] = new Spot(6, 5, new Pawn(PieceColor.BLACK));
        boxes[6][6] = new Spot(6, 6, new Pawn(PieceColor.BLACK));
        boxes[6][7] = new Spot(6, 7, new Pawn(PieceColor.BLACK));

        boxes[7][0] = new Spot(7,0, new Rook(PieceColor.BLACK));
        boxes[7][1] = new Spot(7,1, new Knight(PieceColor.BLACK));
        boxes[7][2] = new Spot(7,2, new Bishop(PieceColor.BLACK));
        boxes[7][3] = new Spot(7,3, new Queen(PieceColor.BLACK));
        boxes[7][4] = new Spot(7,4, new King(PieceColor.BLACK));
        boxes[7][5] = new Spot(7,5, new Bishop(PieceColor.BLACK));
        boxes[7][6] = new Spot(7,6, new Knight(PieceColor.BLACK));
        boxes[7][7] = new Spot(7,7, new Rook(PieceColor.BLACK));

        for (int x = 2; x < 6; x++) {
            for (int y = 0; y < 8; y++) {
                boxes[x][y] = new Spot(x, y, null);
            }
        }
    }
}
