package java_chess;

import java.util.ArrayList;
import java.util.List;

import java_chess.board.Board;
import java_chess.board.Spot;
import java_chess.pieces.King;
import java_chess.pieces.Pawn;
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
     * @return Board
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
        Piece selectedPiece = board.getSpot(row, col).getPiece();
        if (selectedSpot == null) {
            // Select a piece
            System.out.println("Selecting piece");
            if (selectedPiece != null &&
            selectedPiece.getColor() == (whiteTurn ? PieceColor.WHITE : PieceColor.BLACK)) {
                selectedSpot = board.getSpot(row, col);
                return false;
            }
        } else {
            // Move selected piece
            System.out.println("Attempting move...");
            boolean moveMade = makeMove(selectedSpot, board.getSpot(row, col));
            selectedSpot = null;
            return moveMade;
        }
        System.out.println("No piece selected");
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

        System.out.println(currentPiece.getClass().getSimpleName());

        if (currentPiece.canMove(board, start, end)) {
            System.out.println("can move");
            end.setPiece(currentPiece);
            start.setPiece(null);
            return true;
        }
        System.out.println("can't move");
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
    
    /**
     * Checks if a {@code Piece} is not out of bounds.
     */
    private boolean isPositionOnBoard(Spot spot) throws Exception {
        return board.getSpot(spot.getX(), spot.getY()).getX() >= 0 && board.getSpot(spot.getX(), spot.getY()).getX() < 8 &&
        board.getSpot(spot.getX(), spot.getY()).getY() >= 0 && board.getSpot(spot.getX(), spot.getY()).getY() < 8;
    }

    /**
     * Checks if a {@code King} would be in check after a move.
     * @param kingColor
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
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

    /**
     * Retrieves all legal moves a {@code Piece} can do.
     * @param spot
     * @return List<Spot>
     * @throws Exception
     */
    public List<Spot> getLegalMovesForPieceAt(Spot spot) throws Exception {
        Piece selectedPiece = board.getSpot(spot.getX(), spot.getY()).getPiece();

        if (selectedPiece == null) return new ArrayList<>();

        List<Spot> legalMoves = new ArrayList<>();
        switch (selectedPiece.getClass().getSimpleName()) {
            case "Pawn": 
                addPawnMoves(spot, selectedPiece.getColor(), legalMoves);
                break;
            case "Rook": 
                addLineMoves(spot, new int[][]{{1, 0}, {-1,0}, {0, 1}, {0, -1}}, legalMoves);
                break;
            case "Knight": 
                addSingleMoves(spot, new int[][]{{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}}, legalMoves);
                break;
            case "Bishop": 
                addLineMoves(spot, new int[][]{{1, 1}, {-1, -1}, {1, -1}, {-1, 1}}, legalMoves);
                break;
            case "Queen": 
                addLineMoves(spot, new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}}, legalMoves);
                break;
            case "King": 
                addSingleMoves(spot, new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}}, legalMoves);
                break;
            default: 
                throw new AssertionError();
        }
        return legalMoves;
    }

    /**
     * List of {@code Pawn} moves.
     * @param spot
     * @param color
     * @param legalMoves
     * @throws Exception
     */
    private void addPawnMoves(Spot spot, PieceColor color, List<Spot> legalMoves) throws Exception {
        int direction = color == PieceColor.WHITE ? -1 : 1;
        // Standard single move
        Spot newSpot = new Spot(spot.getX(), spot.getY() + direction, new Pawn(color));
        if (isPositionOnBoard(newSpot) && board.getSpot(newSpot.getX(), newSpot.getY()).getPiece() == null) legalMoves.add(newSpot);
        // Double move from starting position
        if (color == PieceColor.WHITE && spot.getY() == 6 || color == PieceColor.BLACK && spot.getY() == 1) {
            newSpot = new Spot(spot.getX(), spot.getY() + (2*direction), new Pawn(color));
            Spot intermidiateSpot = new Spot(newSpot.getX(), newSpot.getY() + direction, new Pawn(color));
            if (isPositionOnBoard(newSpot) && board.getSpot(newSpot.getX(), newSpot.getY()).getPiece() == null && 
            board.getSpot(intermidiateSpot.getX(), intermidiateSpot.getY()).getPiece() == null) {
                legalMoves.add(newSpot);
            }
        }

        // Captures
        int[] captureCols = {spot.getX() -1, spot.getX() +1};
        for (int col : captureCols) {
            newSpot = new Spot(col, spot.getY() + direction, new Pawn(color));
            if (isPositionOnBoard(newSpot) && board.getSpot(newSpot.getX(), newSpot.getY()).getPiece() != null && 
            board.getSpot(newSpot.getX(), newSpot.getY()).getPiece().getColor() != color) {
                legalMoves.add(newSpot);
            }
        }
    }

    /**
     * List of single moves for {@code Piece} such as the {@code King} and the {@code Knight}.
     * @param spot
     * @param moves
     * @param legalMoves
     * @throws Exception
     */
    private void addSingleMoves(Spot spot, int[][] moves, List<Spot> legalMoves) throws Exception {
        for (int[] move : moves) {
            Spot newPos = new Spot(spot.getX() + move[0], spot.getY() + move[1], null);
            if (isPositionOnBoard(newPos) && (board.getSpot(newPos.getX(), newPos.getY()).getPiece() == null ||
                board.getSpot(newPos.getX(), newPos.getY()).getPiece().getColor() != board.getSpot(spot.getX(), spot.getY()).getPiece().getColor())) {
                legalMoves.add(newPos);
            }
        }
    }

    /**
     * List of linear moves for {@code Piece} such as the {@code Queen}, the {@code Rook} and the {@code Bishop},
     * @param spot
     * @param directions
     * @param legalMoves
     * @throws Exception
     */
    private void addLineMoves(Spot spot, int[][] directions, List<Spot> legalMoves) throws Exception {
        for (int[] d : directions) {
            Spot newSpot = new Spot(spot.getX() + d[0], spot.getX() + d[1], null);
            while (isPositionOnBoard(newSpot)) {
                if (board.getSpot(newSpot.getX(), newSpot.getY()) == null) {
                    legalMoves.add(new Spot(newSpot.getX(), newSpot.getY(), null));
                    newSpot = new Spot(newSpot.getX() + d[0], newSpot.getY() + d[1], null);
                } else {
                    if (board.getSpot(newSpot.getX(), newSpot.getY()).getPiece().getColor() != board.getSpot(spot.getX(), spot.getY()).getPiece().getColor()) {
                        legalMoves.add(newSpot);
                    }
                    break;
                }
            }
        }
    }
}
