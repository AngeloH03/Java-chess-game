package java_chess.board;

import java_chess.pieces.Pawn;

/**
 * The {@code Board} class creates an array of 8*8 squares where
 * {@code Pieces} can be placed
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
    public Spot getSpot(int y, int x) throws Exception {
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
        boxes[0][0] = new Spot(0,0, null);
        boxes[1][0] = new Spot(1,0, null);
        boxes[2][0] = new Spot(2,0, null);
        boxes[3][0] = new Spot(3,0, null);
        boxes[4][0] = new Spot(4,0, null);
        boxes[5][0] = new Spot(5,0, null);
        boxes[6][0] = new Spot(6,0, null);
        boxes[7][0] = new Spot(7,0, null);

        boxes[0][1] = new Spot(0, 1, new Pawn(true));
        boxes[1][1] = new Spot(1, 1, new Pawn(true));
        boxes[2][1] = new Spot(2, 1, new Pawn(true));
        boxes[3][1] = new Spot(3, 1, new Pawn(true));
        boxes[4][1] = new Spot(4, 1, new Pawn(true));
        boxes[5][1] = new Spot(5, 1, new Pawn(true));
        boxes[6][1] = new Spot(6, 1, new Pawn(true));
        boxes[7][1] = new Spot(7, 1, new Pawn(true));

        // Black Pieces
        boxes[0][6] = new Spot(0, 6, new Pawn(false));
        boxes[1][6] = new Spot(1, 6, new Pawn(false));
        boxes[2][6] = new Spot(2, 6, new Pawn(false));
        boxes[3][6] = new Spot(3, 6, new Pawn(false));
        boxes[4][6] = new Spot(4, 6, new Pawn(false));
        boxes[5][6] = new Spot(5, 6, new Pawn(false));
        boxes[6][6] = new Spot(6, 6, new Pawn(false));
        boxes[7][6] = new Spot(7, 6, new Pawn(false));

        boxes[0][7] = new Spot(0,7, null);
        boxes[1][7] = new Spot(1,7, null);
        boxes[2][7] = new Spot(2,7, null);
        boxes[3][7] = new Spot(3,7, null);
        boxes[4][7] = new Spot(4,7, null);
        boxes[5][7] = new Spot(5,7, null);
        boxes[6][7] = new Spot(6,7, null);
        boxes[7][7] = new Spot(7,7, null);

        for (int y = 2; y < 6; y++) {
            for (int x = 0; x < 8; x++) {
                boxes[x][y] = new Spot(x, y, null);
            }
        }
    }
}
