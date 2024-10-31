package java_chess;

import javax.swing.SwingUtilities;

import java_chess.gui.ChessGameGUI;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            SwingUtilities.invokeLater(ChessGameGUI::new);
        } catch (Exception e) {
            e.printStackTrace();
        } 
       //ChessGameGUI c = new ChessGameGUI();
    }
}
