package gameoflife.javafrontend;

import gameoflife.javafrontend.impl.SimpleGameCreationPanel;
import gameoflife.javafrontend.impl.SimpleGamePanel;

import javax.swing.JFrame;

import org.gameoflife.backend.shared.dto.GameBoardDTO;
import org.gameoflife.controller.GameController;
import org.gameoflife.controller.factory.GameControllerFactory;
import org.gameoflife.controller.listener.GameCreatedListener;

public class MainFrame extends JFrame implements GameCreatedListener {
    private static final long serialVersionUID = -8519783848029227521L;

    private final GameController gameController;
    
    private final GameCreationPanel gameCreationPanel;
    private final GamePanel gamePanel;

    public MainFrame() {
        super("Conways Game Of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        gameController = GameControllerFactory.createSimpleGameController();
        gameController.registerListener(this);
        
        gameCreationPanel = new SimpleGameCreationPanel(gameController);
        gamePanel = new SimpleGamePanel(gameController);
        
        add(gameCreationPanel.getComponent());
    }

    @Override
    public void newGameHasBeenCreated(GameBoardDTO boardDTO) {
        remove(gameCreationPanel.getComponent());
        add(gamePanel.getComponent());
        
        pack();
        repaint();
    }

}
