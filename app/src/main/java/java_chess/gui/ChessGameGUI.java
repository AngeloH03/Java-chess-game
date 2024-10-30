package java_chess.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java_chess.ChessGame;
import java_chess.board.Board;
import java_chess.gui.components.ChessSquareComponents;
import java_chess.pieces.Bishop;
import java_chess.pieces.King;
import java_chess.pieces.Knight;
import java_chess.pieces.Pawn;
import java_chess.pieces.Piece;
import java_chess.pieces.PieceColor;
import java_chess.pieces.Queen;
import java_chess.pieces.Rook;

/**
 * The {@code ChessGameGUI} class represents the main window that'll contain
 * all the main visible content of the chess game.
 */
public class ChessGameGUI extends JFrame {
    // Attributes
    private final ChessSquareComponents[][] squares = new ChessSquareComponents[8][8];
    private final ChessGame game = new ChessGame();

    private final Map<Class<? extends Piece>, String> pieceUnicodeMap = new HashMap<>()
    {
        {
            put(Pawn.class, "\u265F");
            put(Rook.class, "\u265C");
            put(Knight.class, "\u265E");
            put(Bishop.class, "\u265D");
            put(Queen.class, "\u265B");
            put(King.class, "\u265A");
        }
    };

    // Constructor(s)
    /**
     * Sets the main window.
     * @throws Exception
     */
    public ChessGameGUI() throws Exception {
        setTitle("Shatter-Chess");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 8));
        initializeBoard();
        pack();
        setVisible(true);
    }

    // Methods
    /**
     * The {@code initializeBoard} method fills the {@code ChessSquareComponents} 2D array
     * to display the chess board.
     * @throws Exception
     */
    private void initializeBoard() throws Exception {
        for (int row = 0; row < squares.length; row++){
            for (int col = 0; col < squares[row].length; col++) {
                final int finalRow = row;
                final int finalCol = col;
                ChessSquareComponents square = new ChessSquareComponents(row, col);
                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            handleSquareClick(finalRow, finalCol);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                });
                add(square);
                squares[row][col] = square;
            }
        }
        refreshBoard();
    }

    /**
     * The {@code refreshBoard} method sets the piece's symbol of every squares that
     * has an instance of {@code Piece}.
     * @throws Exception
     */
    private void refreshBoard() throws Exception {
        Board board = new Board();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getSpot(row, col).getPiece();
                if (piece != null) {
                    // If using unicode symbols
                    String symbol = pieceUnicodeMap.get(piece.getClass());
                    Color color = (piece.getColor() == PieceColor.WHITE) ? Color.WHITE : Color.BLACK;
                    squares[row][col].setPieceSymbols(symbol, color);
                } else squares[row][col].clearPieceSymbols();
            }
        }
    }

    /**
     * The {@code handleSquareClick} method bridges user action with game logic to perform
     * a move or to choose a destination.
     * @param row
     * @param col
     * @throws Exception 
     */
    private void handleSquareClick(int row, int col) throws Exception {
        if (game.handleSquareSelection(row, col)) {
            refreshBoard();
            checkGameState();
        }
    }
  
    /**
     * The {@code checkGameState} method...
     */
    private void checkGameState() throws Exception {
        PieceColor currentPlayer = game.getCurrentPlayerColor(); // Current player color
        boolean inCheck = game.isInCheck(currentPlayer);

        if (inCheck) JOptionPane.showMessageDialog(this, currentPlayer + " is in check!");
    }
}
