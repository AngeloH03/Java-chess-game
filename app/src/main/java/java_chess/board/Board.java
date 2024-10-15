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
    public void resetBoard() {
        // White Pieces
        boxes[0][0] = new Spot(0,0, null);
        boxes[0][1] = new Spot(0,1, null);
        boxes[0][2] = new Spot(0,2, null);
        boxes[0][3] = new Spot(0,3, null);
        boxes[0][4] = new Spot(0,4, null);
        boxes[0][5] = new Spot(0,5, null);
        boxes[0][6] = new Spot(0,6, null);
        boxes[0][7] = new Spot(0,7, null);

        boxes[1][0] = new Spot(1, 0, new Pawn(true));
        boxes[1][1] = new Spot(1, 1, new Pawn(true));
        boxes[1][2] = new Spot(1, 2, new Pawn(true));
        boxes[1][3] = new Spot(1, 3, new Pawn(true));
        boxes[1][4] = new Spot(1, 4, new Pawn(true));
        boxes[1][5] = new Spot(1, 5, new Pawn(true));
        boxes[1][6] = new Spot(1, 6, new Pawn(true));
        boxes[1][7] = new Spot(1, 7, new Pawn(true));

        // Black Pieces
        boxes[6][0] = new Spot(6, 0, new Pawn(false));
        boxes[6][1] = new Spot(6, 1, new Pawn(false));
        boxes[6][2] = new Spot(6, 2, new Pawn(false));
        boxes[6][3] = new Spot(6, 3, new Pawn(false));
        boxes[6][4] = new Spot(6, 4, new Pawn(false));
        boxes[6][5] = new Spot(6, 5, new Pawn(false));
        boxes[6][6] = new Spot(6, 6, new Pawn(false));
        boxes[6][7] = new Spot(6, 7, new Pawn(false));

        boxes[7][0] = new Spot(7,0, null);
        boxes[7][1] = new Spot(7,1, null);
        boxes[7][2] = new Spot(7,2, null);
        boxes[7][3] = new Spot(7,3, null);
        boxes[7][4] = new Spot(7,4, null);
        boxes[7][5] = new Spot(7,5, null);
        boxes[7][6] = new Spot(7,6, null);
        boxes[7][7] = new Spot(7,7, null);

        for (int x = 2; x < 6; x++) {
            for (int y = 0; y < 8; y++) {
                boxes[x][y] = new Spot(x, y, null);
            }
        }
    }
}
