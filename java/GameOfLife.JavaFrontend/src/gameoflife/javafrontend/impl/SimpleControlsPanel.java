package gameoflife.javafrontend.impl;

import gameoflife.javafrontend.GameControlsPanel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.gameoflife.controller.GameController;
import org.gameoflife.controller.listener.GameStartedListener;

public class SimpleControlsPanel implements GameControlsPanel, GameStartedListener {

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
