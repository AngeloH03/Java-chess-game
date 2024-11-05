package java_chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java_chess.board.Board;
import java_chess.board.Spot;
import java_chess.pieces.Bishop;
import java_chess.pieces.Knight;
import java_chess.pieces.Pawn;
import java_chess.pieces.Piece;
import java_chess.pieces.PieceColor;
import java_chess.pieces.Rook;

public class ChessTest {
    
    @Test void piecesTest() {
        // Pawn
        Pawn pawn = new Pawn(PieceColor.WHITE);
        Assertions.assertEquals(PieceColor.WHITE, pawn.getColor());

        // Rook
        Rook rook = new Rook(PieceColor.BLACK);
        Assertions.assertEquals(PieceColor.BLACK, rook.getColor());

        // Knight
        Knight knight = new Knight(PieceColor.WHITE);
        Assertions.assertEquals(false, knight.isKilled());

        // Bishop
        Bishop bishop = new Bishop(PieceColor.BLACK);
        bishop.setIsKilled(true);
        Assertions.assertEquals(true, bishop.isKilled());
    }

    @Test void spotTest() {
        Spot spot = new Spot(0, 0, new Rook(PieceColor.WHITE));
        Assertions.assertInstanceOf(Rook.class, spot.getPiece());
        Assertions.assertEquals(PieceColor.WHITE, spot.getPiece().getColor());
        Assertions.assertEquals(0, spot.getX());
        Assertions.assertEquals(0, spot.getY());
    }

    @Test void canMoveTest() throws Exception {
        Board board = new Board();
        Piece pawn = new Pawn(PieceColor.WHITE);
        Assertions.assertEquals("Pawn", pawn.getClass().getSimpleName());

        Spot start = new Spot(0, 0, pawn);
        Spot end = new Spot(0, 1, null);

        Assertions.assertEquals(true, pawn.canMove(board, start, end));
    }

    @Test void boardTest() {
        Board board = new Board();
    }
}
