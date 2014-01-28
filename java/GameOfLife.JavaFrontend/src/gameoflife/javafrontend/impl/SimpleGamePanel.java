package gameoflife.javafrontend.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import org.gameoflife.controller.GameController;

import gameoflife.javafrontend.BoardRenderer;
import gameoflife.javafrontend.GameControlsPanel;
import gameoflife.javafrontend.GamePanel;

public class SimpleGamePanel implements GamePanel {
    
    private final JPanel gamePanel;

    private final GameControlsPanel controlsPanel;
    private final BoardRenderer boardRenderer;

    public SimpleGamePanel(GameController gameController) {
        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());

        controlsPanel = new SimpleGameControlsPanel(gameController);
        boardRenderer = new PanelBoardRenderer(gameController);

        gamePanel.add(controlsPanel.getComponent(), BorderLayout.NORTH);
        gamePanel.add(boardRenderer.getComponent(), BorderLayout.CENTER);
    }
    
    @Override
    public void addActionListenerToNewGameControl(ActionListener listener) {
        controlsPanel.addActionListenerToNewGameControl(listener);
    }

    @Override
    public Component getComponent() {
        return gamePanel;
    }

}
