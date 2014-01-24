package gameoflife.frontend;

import gameoflife.controller.GameController;
import gameoflife.controller.impl.SimpleGameController;
import gameoflife.frontend.impl.PanelBoardRenderer;
import gameoflife.frontend.impl.SimpleControlsPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = -8519783848029227521L;

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

    public MainFrame() {
        super("Conways Game Of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        GameController gameController = new SimpleGameController();

        ControlsPanel controlsPanel = new SimpleControlsPanel(gameController);
        add(controlsPanel.getComponent(), BorderLayout.NORTH);

        BoardRenderer boardRenderer = new PanelBoardRenderer(gameController);
        add(boardRenderer.getComponent(), BorderLayout.CENTER);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

}
