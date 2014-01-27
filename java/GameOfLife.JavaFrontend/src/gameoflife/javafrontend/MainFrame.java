package gameoflife.javafrontend;

import gameoflife.javafrontend.impl.PanelBoardRenderer;
import gameoflife.javafrontend.impl.SimpleControlsPanel;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.gameoflife.backend.shared.dto.GameBoardDTO;
import org.gameoflife.controller.GameController;
import org.gameoflife.controller.factory.GameControllerFactory;
import org.gameoflife.controller.listener.GameBoardChangedListener;

public class MainFrame extends JFrame implements GameBoardChangedListener {
    private static final long serialVersionUID = -8519783848029227521L;

    private final GameController gameController;
    
//    private final GameCreationPanel gameCreationPanel;

    private final GameControlsPanel controlsPanel;
    private final BoardRenderer boardRenderer;

    public MainFrame() {
        super("Conways Game Of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        gameController = GameControllerFactory.createSimpleGameController();
        gameController.registerListener(this);
        
//        gameCreationPanel = new SimpleGameCreationPanel(gameController);
//        add(gameCreationPanel.getComponent(), BorderLayout.NORTH);

        controlsPanel = new SimpleControlsPanel(gameController);
        boardRenderer = new PanelBoardRenderer(gameController);
        
        gameController.createNewGame(25, 25);
    }

    @Override
    public void gameBoardHasChanged(GameBoardDTO boardDTO) {
        add(controlsPanel.getComponent(), BorderLayout.NORTH);
        add(boardRenderer.getComponent(), BorderLayout.CENTER);

        redraw();
    }

    private void redraw() {
        validate();
        repaint();
    }

}
