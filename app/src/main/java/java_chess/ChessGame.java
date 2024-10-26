package java_chess;

import java_chess.board.Board;
import java_chess.board.Spot;
import java_chess.pieces.King;
import java_chess.pieces.Piece;
import java_chess.pieces.PieceColor;

/**
 * The {@code ChessGame} class handles the game logic such as:
 * - Player's turn
 * - Check / Checkmate
 * - Moving pieces
 */
public class ChessGame {
    // Attributes
    private final Board board;
    private boolean whiteTurn = true;

    // Constructor(s)
    public ChessGame() {
        this.board = new Board();
    }

    /**
     * The {@code makeMove} function will check if the selected {@code Spot} 
     * has an actual {@code Piece} and will handle its movement
     * @param start
     * @param end
     * @return
     */
    public boolean makeMove(Spot start, Spot end) {
        Piece currentPiece = start.getPiece();

        // No piece at start position or not the player's turn
        if (currentPiece == null || currentPiece.getColor() !=  (whiteTurn ? PieceColor.WHITE : PieceColor.BLACK)) return false;

        if (currentPiece.canMove(board, start, end)) {
            end.setPiece(currentPiece);
            start.setPiece(null);
            return true;
        }
        return false;
    }

    public boolean isInCheck(PieceColor kingColor) throws Exception {
        Spot kingSpot = findKingSpot(kingColor);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; row++) {
                Piece piece = board.getSpot(row, col).getPiece();
                if (piece != null && piece.getColor() != kingColor) {
                    return true; // HEEELP !!!!!!!!
                }
            }
        }
        return false;
    }

    public Spot findKingSpot(PieceColor color) throws Exception {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getSpot(row, col).getPiece();
                if (piece instanceof King && piece.getColor() == color) {
                    return new Spot(row, col, null);
                }
            }
        }
        throw new RuntimeException("King not found, which should never happen.");
    }
    
}
