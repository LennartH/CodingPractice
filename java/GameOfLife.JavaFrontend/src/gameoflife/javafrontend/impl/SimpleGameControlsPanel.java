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

public class SimpleGameControlsPanel implements GameControlsPanel, GameStartedListener {

    private final GameController gameController;
    
    private final JPanel controlsPanel;

    private JButton newGameButton;
    private final Collection<JButton> controlButtons;
    private final Map<JButton, Boolean> buttonIsEnabledWhenGameHasStarted;
    
    public SimpleGameControlsPanel(GameController gameController) {
        this.gameController = gameController;
        
        controlsPanel = new JPanel(new FlowLayout());
        
        controlButtons = new HashSet<>();
        buttonIsEnabledWhenGameHasStarted = new HashMap<>();
        
        newGameButton = createNewGameButton();
        addAllwaysActivatedButton(newGameButton);
        
        JButton startButton = createStartButton();
        addButtonWithChangingActivation(startButton, false);
        
        JButton nextGenerationButton = createNextGenerationButton();
        addButtonWithChangingActivation(nextGenerationButton, true);
        
        adjustActivatedButtons();
        gameController.registerListener(this);
    }

    private JButton createNewGameButton() {
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        return newGameButton;
    }

    private JButton createStartButton() {
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleGameControlsPanel.this.gameController.startGame();
            }
        });
        return startButton;
    }

    private JButton createNextGenerationButton() {
        JButton nextGenerationButton = new JButton("Next Generation");
        nextGenerationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SimpleGameControlsPanel.this.gameController.calculateNextGeneration();
            }
        });
        return nextGenerationButton;
    }

    private void addButtonWithChangingActivation(JButton button, boolean enabledWhenGameHasStarted) {
        buttonIsEnabledWhenGameHasStarted.put(button, enabledWhenGameHasStarted);
        controlButtons.add(button);
        addButtonToPanel(button);
    }

    private void addAllwaysActivatedButton(JButton button) {
        addButtonToPanel(button);
    }

    private void addButtonToPanel(JButton button) {
        controlsPanel.add(button);
    }
    
    @Override
    public void addActionListenerToNewGameControl(ActionListener listener) {
        newGameButton.addActionListener(listener);
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
