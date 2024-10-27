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
     * @throws Exception 
     */
    public boolean makeMove(Spot start, Spot end) throws Exception {
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

    /**
     * Checks if a {@code King} piece is in check.
     * @param kingColor
     * @return boolean
     * @throws Exception
     */
    public boolean isInCheck(PieceColor kingColor) throws Exception {
        Spot kingSpot = findKingSpot(kingColor); // Find the king
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; row++) {
                Piece piece = board.getSpot(row, col).getPiece();
                if (piece != null && piece.getColor() != kingColor) {
                    if (piece.canMove(board, new Spot(row, col, null), kingSpot)) return true; // An opponent piece can capture the king.
                }
            }
        }
        return false;
    }

    public boolean isCheckMate(PieceColor kingColor) throws Exception {
        // If king is not in check then it cannot be chackmate
        if (!isInCheck(kingColor)) return false;

        Spot kingSpot = findKingSpot(kingColor);
        King king = (King) board.getSpot(kingSpot.getX(), kingSpot.getY()).getPiece();

        // Find a move that gets the king out of check
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                if (rowOffset == 0 && colOffset == 0) {
                    continue; // Skip the current position of the king
                }
                Spot newSpot = new Spot(kingSpot.getX() + rowOffset, kingSpot.getY() + colOffset, null);
                // Check if movind the king to a new position is a legal move and will not result in a check
                if (isPositionOnBoard(newSpot) && 
                king.canMove(board, new Spot(rowOffset, colOffset, king), kingSpot) &&
                !wouldBeInCheckAfterMove(kingColor, kingSpot, newSpot)) {
                    return false; // Found a move that gets the king out of check so it's not checkmate
                }
            }
        }
        return true; // Checkmate
    }

    /**
     * Method used to locate a {@code King} piece by passing its color as a parameter.
     * @param color
     * @return Spot
     * @throws Exception
     */
    private Spot findKingSpot(PieceColor color) throws Exception {
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
    
    private boolean isPositionOnBoard(Spot spot) throws Exception {
        return board.getSpot(spot.getX(), spot.getY()).getX() >= 0 && board.getSpot(spot.getX(), spot.getY()).getX() < 8 &&
        board.getSpot(spot.getX(), spot.getY()).getY() >= 0 && board.getSpot(spot.getX(), spot.getY()).getY() < 8;
    }

    private boolean wouldBeInCheckAfterMove(PieceColor kingColor, Spot start, Spot end) throws Exception {
        // Simulate move temporarily
        Piece temp = board.getSpot(end.getX(), end.getY()).getPiece();
        board.getSpot(end.getX(), end.getY()).setPiece(board.getSpot(start.getX(), start.getY()).getPiece());
        board.getSpot(start.getX(), start.getY()).setPiece(null);

        boolean inCheck = isInCheck(kingColor);

        // Undo the move
        board.getSpot(start.getX(), start.getY()).setPiece(board.getSpot(end.getX(), end.getY()).getPiece());
        board.getSpot(end.getX(), end.getY()).setPiece(temp);

        return inCheck;
    }

}
