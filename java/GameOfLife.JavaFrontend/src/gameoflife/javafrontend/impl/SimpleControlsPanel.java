package gameoflife.javafrontend.impl;

import gameoflife.javafrontend.GameControlsPanel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.gameoflife.controller.GameController;
import org.gameoflife.controller.listener.GameStartedListener;

public class SimpleControlsPanel implements GameControlsPanel, GameStartedListener {

    private final GameController gameController;
    
    private final JPanel controlsPanel;
    
    private final Collection<JButton> controlButtons;
    private final Map<JButton, Boolean> buttonIsEnabledWhenGameHasStarted;
    
    public SimpleControlsPanel(GameController gameController) {
        this.gameController = gameController;
        
        controlsPanel = new JPanel(new FlowLayout());
        
        controlButtons = new HashSet<>();
        buttonIsEnabledWhenGameHasStarted = new HashMap<>();
        
        JButton startButton = createStartButton();
        addButton(startButton, false);
        
        JButton nextGenerationButton = createNextGenerationButton();
        addButton(nextGenerationButton, true);
        
        adjustActivatedButtons();
        gameController.registerListener(this);
    }

    private void addButton(JButton button, boolean enabledWhenGameHasStarted) {
        controlButtons.add(button);
        buttonIsEnabledWhenGameHasStarted.put(button, enabledWhenGameHasStarted);
        controlsPanel.add(button);
    }

    private JButton createStartButton() {
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleControlsPanel.this.gameController.startGame();
            }
        });
        return startButton;
    }

    private JButton createNextGenerationButton() {
        JButton nextGenerationButton = new JButton("Next Generation");
        nextGenerationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SimpleControlsPanel.this.gameController.calculateNextGeneration();
            }
        });
        return nextGenerationButton;
    }
    
    @Override
    public void gameHasStarted() {
        adjustActivatedButtons();
    }

    private void adjustActivatedButtons() {
        for (JButton button : controlButtons) {
            Boolean enabledWhenGameHasStarted = buttonIsEnabledWhenGameHasStarted.get(button);
            boolean enabled = gameController.isGameStarted() ? enabledWhenGameHasStarted : !enabledWhenGameHasStarted;
            button.setEnabled(enabled);
        }
    }

    @Override
    public Component getComponent() {
        return controlsPanel;
    }

}
