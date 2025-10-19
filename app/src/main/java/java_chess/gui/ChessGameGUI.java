package java_chess.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java_chess.ChessGame;
import java_chess.board.Board;
import java_chess.board.Spot;
import java_chess.gui.components.ChessSquareComponents;
import java_chess.pieces.King;
import java_chess.pieces.Piece;
import java_chess.pieces.PieceColor;

/**
 * The {@code ChessGameGUI} class represents the main window that'll contain
 * all the visible content of the chess game.
 */
public class ChessGameGUI extends JFrame {
    // Attributes
    private final ChessSquareComponents[][] squares = new ChessSquareComponents[8][8];
    private final ChessGame game = new ChessGame();

    // Constructor(s)
    /**
     * Sets the main window.
     * @throws Exception
     */
    public ChessGameGUI() {
        super("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 8));
        initializeBoard();
        addGameResetOption();
        pack();
        setVisible(true);
    }

    // Methods
    /**
     * The {@code initializeBoard} method fills the {@code ChessSquareComponents} 2D array
     * to display the chess board.
     * @throws Exception
     */
    private void initializeBoard() {
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
                        } catch (Exception e1) {}
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
    private void refreshBoard() {
        Board board = game.getBoard();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getSpot(row, col).getPiece();
                if (piece != null) {
                    Image img = piece.image;
                    Color color = (piece.getColor() == PieceColor.WHITE) ? Color.WHITE : Color.BLACK;
                    squares[row][col].setPieceSymbols(img, color);
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
        boolean moveResult = game.handleSquareSelection(row, col);
        clearHighlights();
        if (moveResult) {
            refreshBoard();
            checkGameState();
            checkGameOver();
        } else if (game.isPieceSelected()) {
            highlightLegalMoves(game.getBoard().getSpot(row, col));
        }
        refreshBoard();
    }
  
    /**
     * Checks the game state.
     */
    private void checkGameState() throws Exception {
        PieceColor currentPlayer = game.getCurrentPlayerColor(); // Current player color
        boolean inCheck = game.isInCheck(currentPlayer);

        if (inCheck) {
            Spot kingSpot = game.findKingSpot(currentPlayer);
            squares[kingSpot.getX()][kingSpot.getY()].setBackground(Color.RED);
        }
    }

    /**
     * Highlights legal moves to make with a selected {@code Piece}.
     * @param spot
     * @throws Exception 
     */
    private void highlightLegalMoves(Spot spot) throws Exception {
        List<Spot> legalMoves = game.getLegalMovesForPieceAt(spot);
        for (Spot move : legalMoves) {
            if (game.getBoard().getSpot(move.getX(), move.getY()).getPiece() instanceof King == false)
            squares[move.getX()][move.getY()].setBackground(Color.GREEN);
        }
    }

    /**
     * Clears the highlighted routes of selected {@coode Piece}.
          * @throws Exception 
          */
         private void clearHighlights() throws Exception {
        PieceColor currentPlayer = game.getCurrentPlayerColor();
        Spot kinSpot = game.findKingSpot(currentPlayer);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (row == kinSpot.getX() && col == kinSpot.getY()) continue;
                squares[row][col].setBackground((row + col) % 2 == 0 ? new Color(240, 217, 181) : new Color(181, 136, 99));
            }
        }
    }

    /**
     * Sets a menu bar to reset the game at any point.
     */
    private void addGameResetOption() {
        JMenuBar menuBar  = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenuItem resetItem = new JMenuItem("Reset");
        resetItem.addActionListener(e -> {
            try {
                clearHighlights();
                resetGame();
            } catch (Exception error) {}
        });
        gameMenu.add(resetItem);
        menuBar.add(gameMenu);
        setJMenuBar(menuBar);
    }

    /**
     * Resets the game.
     * @throws Exception
     */
    private void resetGame() throws Exception {
        game.resetGame();
        refreshBoard();
        clearHighlights();
    }

    /**
     * Check for checkmate and display dialog window
     * @throws Exception 
     * @throws HeadlessException 
     */
    private void checkGameOver() throws HeadlessException, Exception {
        if (game.isCheckMate(game.getCurrentPlayerColor())) {
            int response = JOptionPane.showConfirmDialog(this, 
            "Echec et mat ! Voulez-vous rejouer ?", 
            "Game over", 
            JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
        }
    }
}
