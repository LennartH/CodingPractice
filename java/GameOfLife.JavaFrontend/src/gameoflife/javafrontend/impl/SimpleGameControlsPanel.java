package gameoflife.javafrontend.impl;

import gameoflife.javafrontend.GameControlsPanel;
import gameoflife.javafrontend.impl.component.EvolveControlPanel;
import gameoflife.javafrontend.impl.component.GenerationCountLabel;

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

import org.gameoflife.backend.shared.dto.GameBoardDTO;
import org.gameoflife.controller.GameController;
import org.gameoflife.controller.listener.GameCreatedListener;
import org.gameoflife.controller.listener.GameStartedListener;

public class SimpleGameControlsPanel extends AbstractProvidesComponent implements GameControlsPanel, GameCreatedListener, GameStartedListener {

    private final GameController gameController;
    
    private final JPanel controlsPanel;
    
    private final Collection<JButton> controlButtons;
    private final Map<JButton, Boolean> buttonIsEnabledWhenGameHasStarted;

    private JButton newGameButton;
    private JButton startButton;
    private JButton nextGenerationButton;
    private EvolveControlPanel evolveControlPanel;

    private GenerationCountLabel generationCountLabel;
    
    public SimpleGameControlsPanel(GameController gameController) {
        this.gameController = gameController;
        
        controlsPanel = new JPanel(new FlowLayout());
        
        controlButtons = new HashSet<>();
        buttonIsEnabledWhenGameHasStarted = new HashMap<>();
        
        newGameButton = createNewGameButton();
        addAllwaysActivatedButton(newGameButton);
        
        startButton = createStartButton();
        addButtonWithChangingActivation(startButton, false);
        
        nextGenerationButton = createNextGenerationButton();
        addButtonWithChangingActivation(nextGenerationButton, true);
        EvolveControlPanel evolveControlPanel1 = new EvolveControlPanel("Evolve", this.gameController);
        
        evolveControlPanel = evolveControlPanel1;
        evolveControlPanel.setVisible(false);
        controlsPanel.add(evolveControlPanel.getComponent());
        
        generationCountLabel = new GenerationCountLabel("Generation -");
        generationCountLabel.setVisible(false);
        controlsPanel.add(generationCountLabel.getComponent());
        
        adjustActivatedButtons();
        gameController.registerListener(this);
    }

    private JButton createNewGameButton() {
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generationCountLabel.setVisible(false);
                evolveControlPanel.setVisible(false);
            }
        });
        return newGameButton;
    }

    private JButton createStartButton() {
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generationCountLabel.setGenerationCount(0);
                generationCountLabel.setVisible(true);
                
                evolveControlPanel.setVisible(true);
                
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
                doNextGeneration();
            }
        });
        return nextGenerationButton;
    }

    private void doNextGeneration() {
        generationCountLabel.incrementGenerationCount();
        SimpleGameControlsPanel.this.gameController.calculateNextGeneration();
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
    public void newGameHasBeenCreated(GameBoardDTO newBoardDTO) {
        adjustActivatedButtons();
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
