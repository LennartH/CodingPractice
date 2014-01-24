package gameoflife.frontend.impl;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import gameoflife.controller.GameController;
import gameoflife.controller.listener.GameStartedListener;
import gameoflife.frontend.ControlsPanel;

public class SimpleControlsPanel implements ControlsPanel, GameStartedListener {

    private final GameController gameController;
    
    private final JPanel controlsPanel;
    
    public SimpleControlsPanel(GameController gameController) {
        this.gameController = gameController;
        controlsPanel = new JPanel(new FlowLayout());
        
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleControlsPanel.this.gameController.startGame();
            }
        });
        controlsPanel.add(startButton);
        
        addThisAsGameControllerListeners();
    }

    private void addThisAsGameControllerListeners() {
        gameController.addGameStartedListener(this);
    }
    
    @Override
    public void gameHasStarted() {
        System.out.println("Game started");
    }

    @Override
    public Component getComponent() {
        return controlsPanel;
    }

}
