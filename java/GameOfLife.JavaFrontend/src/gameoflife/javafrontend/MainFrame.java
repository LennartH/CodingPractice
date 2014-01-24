package gameoflife.javafrontend;

import gameoflife.javafrontend.impl.PanelBoardRenderer;
import gameoflife.javafrontend.impl.SimpleControlsPanel;
import gameoflife.javafrontend.impl.SimpleGameCreationPanel;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.gameoflife.backend.shared.GameBoardDTO;
import org.gameoflife.controller.GameController;
import org.gameoflife.controller.impl.SimpleGameController;
import org.gameoflife.controller.listener.NewGameCreatedListener;

public class MainFrame extends JFrame implements NewGameCreatedListener {
    private static final long serialVersionUID = -8519783848029227521L;

    public MainFrame() {
        super("Conways Game Of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        GameController gameController = new SimpleGameController();
        gameController.addNewGameCreatedListener(this);
        
        GameCreationPanel gameCreationPanel = new SimpleGameCreationPanel(gameController);
        add(gameCreationPanel.getComponent(), BorderLayout.NORTH);

//        GameControlsPanel controlsPanel = new SimpleControlsPanel(gameController);
//        add(controlsPanel.getComponent(), BorderLayout.NORTH);
//
//        BoardRenderer boardRenderer = new PanelBoardRenderer(gameController);
//        add(boardRenderer.getComponent(), BorderLayout.CENTER);
    }

    @Override
    public void createdNewGame(GameBoardDTO boardDTO) {
        // TODO Auto-generated method stub
        
    }

}
