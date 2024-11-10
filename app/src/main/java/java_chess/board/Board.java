package java_chess.board;

import java_chess.pieces.Knight;
import java_chess.pieces.Pawn;
import java_chess.pieces.PieceColor;
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
        for (int row = 0; row < 8; row++) {
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
        }

        /* // Black Pieces
        boxes[0][0] = new Spot(0,0, new Rook(PieceColor.BLACK));
        boxes[1][0] = new Spot(1,0, new Knight(PieceColor.BLACK));
        boxes[2][0] = new Spot(2,0, new Bishop(PieceColor.BLACK));
        boxes[3][0] = new Spot(3,0, new Queen(PieceColor.BLACK));
        boxes[4][0] = new Spot(4,0, new King(PieceColor.BLACK));
        boxes[5][0] = new Spot(5,0, new Bishop(PieceColor.BLACK));
        boxes[6][0] = new Spot(6,0, new Knight(PieceColor.BLACK));
        boxes[7][0] = new Spot(7,0, new Rook(PieceColor.BLACK));

        for (int col = 0; col < 8; col++) {
            boxes[col][1] = new Spot(1, col, new Pawn(PieceColor.BLACK));
        }

        // White Pieces
        for (int col = 0; col < 8; col++) {
            boxes[col][6] = new Spot(6, col, new Pawn(PieceColor.WHITE));
        }

        boxes[0][7] = new Spot(0,7, new Rook(PieceColor.WHITE));
        boxes[1][7] = new Spot(1,7, new Knight(PieceColor.WHITE));
        boxes[2][7] = new Spot(2,7, new Bishop(PieceColor.WHITE));
        boxes[3][7] = new Spot(3,7, new Queen(PieceColor.WHITE));
        boxes[4][7] = new Spot(4,7, new King(PieceColor.WHITE));
        boxes[5][7] = new Spot(5,7, new Bishop(PieceColor.WHITE));
        boxes[6][7] = new Spot(6,7, new Knight(PieceColor.WHITE));
        boxes[7][7] = new Spot(7,7, new Rook(PieceColor.WHITE));

        for (int y = 2; y < 6; y++) {
            for (int x = 0; x < 8; x++) {
                boxes[x][y] = new Spot(x, y, null);
            }
        } */
    }
}
