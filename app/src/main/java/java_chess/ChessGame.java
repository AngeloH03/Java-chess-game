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
    private Board board;
    private boolean whiteTurn = true;
    private Spot selectedSpot;

    // Constructor(s)
    /**
     * Creates an instance of {@code ChessGame}.
     */
    public ChessGame() {
        this.board = new Board();
    }

    // Methods
    /**
     * Returns the {@code Board}.
     * @return
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Resets the game.
     */
    public void resetGame() {
        this.board = new Board();
        this.whiteTurn = true;
    }

    /**
     * Returns the player's color.
     * @return Piececolor
     */
    public PieceColor getCurrentPlayerColor() {
        return whiteTurn ? PieceColor.WHITE : PieceColor.BLACK;
    }

    /**  
     * Checks if a piece has been selected.
    */
    public boolean isPieceSelected() {
        return selectedSpot != null;
    }

    public boolean handleSquareSelection(int row, int col) throws Exception {
        if (selectedSpot == null) {
            Piece selectedPiece = board.getSpot(row, col).getPiece();
            if (selectedPiece != null &&
            selectedPiece.getColor() == (whiteTurn ? PieceColor.WHITE : PieceColor.BLACK)) {
                selectedSpot = new Spot(row, col, selectedPiece);
            }
        }
        return false;
    }

    /**
     * The {@code makeMove} function will check if the selected {@code Spot} 
     * has an actual {@code Piece} and will handle its movement.
     * @param start
     * @param end
     * @return boolean
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

    /**
     * Checks if king is checkmate.
     * @param kingColor
     * @return boolean
     * @throws Exception
     */
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
                // Check if moving the king to a new position is a legal move and will not result in a check
                if (isPositionOnBoard(newSpot) && 
                king.canMove(board, kingSpot, newSpot) &&
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
                    return board.getSpot(row, col);
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
