package org.gameoflife.frontend.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.RepaintManager;

import org.gameoflife.backend.shared.dto.GameBoardDTO;
import org.gameoflife.controller.GameController;
import org.gameoflife.controller.factory.GameControllerFactory;
import org.gameoflife.controller.listener.GameCreatedListener;
import org.gameoflife.frontend.swing.impl.SimpleGameCreationPanel;
import org.gameoflife.frontend.swing.impl.SimpleGamePanel;
import org.gameoflife.frontend.swing.impl.component.FreezableRepaintManager;

public class MainFrame extends JFrame implements GameCreatedListener {
    private static final long serialVersionUID = -8519783848029227521L;

    private final GameController gameController;
    
    private final GameCreationPanel gameCreationPanel;
    private final GamePanel gamePanel;

    public MainFrame() {
        super("Conways Game Of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        RepaintManager.setCurrentManager(new FreezableRepaintManager());
        getRootPane().setDoubleBuffered(true);
        
        gameController = GameControllerFactory.createSimpleGameController();
        gameController.registerListener(this);
        
        gameCreationPanel = new SimpleGameCreationPanel(gameController);
        gamePanel = new SimpleGamePanel(gameController);
        gamePanel.addActionListenerToNewGameControl(new NewGameControlActionListener());
        
        add(gameCreationPanel.getComponent());
    }

    @Override
    public void newGameHasBeenCreated(GameBoardDTO boardDTO) {
        remove(gameCreationPanel.getComponent());
        add(gamePanel.getComponent());

        repaint();
        pack();
    }
    
    private class NewGameControlActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            remove(gamePanel.getComponent());
            add(gameCreationPanel.getComponent());

            repaint();
            pack();
        }
        
    }

}
