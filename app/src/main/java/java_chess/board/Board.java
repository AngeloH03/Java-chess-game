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
    /**
     * Creates a {@code Board}.
     */
    public Board() {
        boxes = new Spot[8][8];
        this.resetBoard();
    }

    // Getters
    /**
     * Returns a specific {@code Spot} on the board
     * @param x
     * @param y
     * @return {@code Spot}
     * @throws Exception
     */
    public Spot getSpot(int x, int y) {
        return boxes[x][y];
    }

    public Spot[][] getBoard() {
        return this.boxes;
    }

    // Setters
    /**
     * Sets the board back to its initial state
     */
    private void resetBoard() {
        /* for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (row == 6 && col == 4) {
                    boxes[row][col] = new Spot(row, col, new Knight(PieceColor.WHITE));
                } else if (row == 2 && col == 5) {
                    boxes[row][col] = new Spot(row, col, new Pawn(PieceColor.BLACK));
                } else if (row == 2 && col == 4) {
                    boxes[row][col] = new Spot(row, col, new Pawn(PieceColor.BLACK));
                } else if (row == 2 && col == 3) {
                    boxes[row][col] = new Spot(row, col, new Pawn(PieceColor.BLACK));
                } else {
                    boxes[row][col] = new Spot(row, col, null);
                }
            }
        } */

        // Black Pieces
        boxes[0][0] = new Spot(0,0, new Rook(PieceColor.BLACK));
        boxes[0][1] = new Spot(0,1, new Knight(PieceColor.BLACK));
        boxes[0][2] = new Spot(0,2, new Bishop(PieceColor.BLACK));
        boxes[0][3] = new Spot(0,3, new Queen(PieceColor.BLACK));
        boxes[0][4] = new Spot(0,4, new King(PieceColor.BLACK));
        boxes[0][5] = new Spot(0,5, new Bishop(PieceColor.BLACK));
        boxes[0][6] = new Spot(0,6, new Knight(PieceColor.BLACK));
        boxes[0][7] = new Spot(0,7, new Rook(PieceColor.BLACK));

        for (int col = 0; col < 8; col++) {
            boxes[1][col] = new Spot(1, col, new Pawn(PieceColor.BLACK));
        }

        // White Pieces
        for (int col = 0; col < 8; col++) {
            boxes[6][col] = new Spot(6, col, new Pawn(PieceColor.WHITE));
        }

        boxes[7][0] = new Spot(7,0, new Rook(PieceColor.WHITE));
        boxes[7][1] = new Spot(7,1, new Knight(PieceColor.WHITE));
        boxes[7][2] = new Spot(7,2, new Bishop(PieceColor.WHITE));
        boxes[7][3] = new Spot(7,3, new Queen(PieceColor.WHITE));
        boxes[7][4] = new Spot(7,4, new King(PieceColor.WHITE));
        boxes[7][5] = new Spot(7,5, new Bishop(PieceColor.WHITE));
        boxes[7][6] = new Spot(7,6, new Knight(PieceColor.WHITE));
        boxes[7][7] = new Spot(7,7, new Rook(PieceColor.WHITE));

        for (int x = 2; x < 6; x++) {
            for (int y = 0; y < 8; y++) {
                boxes[x][y] = new Spot(x, y, null);
            }
        }
    }
}
