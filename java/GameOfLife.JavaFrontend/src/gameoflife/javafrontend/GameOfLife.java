package gameoflife.javafrontend;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GameOfLife {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame mainFrame = new MainFrame();
                mainFrame.pack();
                mainFrame.setVisible(true);
            }
        });
    }

}
