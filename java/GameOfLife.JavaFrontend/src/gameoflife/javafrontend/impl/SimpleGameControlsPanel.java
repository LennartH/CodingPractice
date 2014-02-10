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
import org.gameoflife.backend.shared.factories.GameBoardDTOFactory;
import org.gameoflife.controller.GameController;
import org.gameoflife.controller.listener.GameBoardChangedListener;
import org.gameoflife.controller.listener.GameCreatedListener;
import org.gameoflife.controller.listener.GameStartedListener;

public class SimpleGameControlsPanel extends AbstractProvidesComponent implements
                                                                       GameControlsPanel,
                                                                       GameCreatedListener,
                                                                       GameStartedListener,
                                                                       GameBoardChangedListener {

    private final GameController gameController;
    
    private final Collection<Component> controls;
    private final Map<Component, Boolean> buttonVisibilityAfterGameStartMap;
    
    private final JPanel controlsPanel;

    private JButton newGameButton;
    private JButton nextGenerationButton;
    private GenerationCountLabel generationCountLabel;

    public SimpleGameControlsPanel(GameController gameController) {
        this.gameController = gameController;
        controls = new HashSet<>();
        buttonVisibilityAfterGameStartMap = new HashMap<>();

        controlsPanel = new JPanel(new FlowLayout());
        
        newGameButton = createNewGameButton();
        addAllwaysVisibleControl(newGameButton);
        
        JButton startButton = createStartButton();
        addControlWithChangingVisibility(startButton, false);
        
        JButton clearButton = createClearButton();
        addControlWithChangingVisibility(clearButton, false);
        
        JButton resetButton = createResetButton();
        addControlWithChangingVisibility(resetButton, true);
        
        nextGenerationButton = createNextGenerationButton();
        addControlWithChangingVisibility(nextGenerationButton, true);
        
        EvolveControlPanel evolveControlPanel = new EvolveControlPanel("Evolve", this.gameController);
        addControlWithChangingVisibility(evolveControlPanel.getComponent(), true);
        
        generationCountLabel = new GenerationCountLabel("Generation -");
        addControlWithChangingVisibility(generationCountLabel.getComponent(), true);
        
        adjustControlsVisibility();
        gameController.registerListener(this);
    }

    private JButton createClearButton() {
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.applyGameBoardDTO(getStandardBoardDTO());
            }
        });
        return clearButton;
    }

    private JButton createResetButton() {
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.resetGameBoardToInitialState();
            }
        });
        return resetButton;
    }

    private GameBoardDTO getStandardBoardDTO() {
        return GameBoardDTOFactory.createDeadGameBoardDTO(gameController.getBoardWidth(), gameController.getBoardHeight());
    }

    private JButton createNewGameButton() {
        JButton newGameButton = new JButton("New Game");
        return newGameButton;
    }

    private JButton createStartButton() {
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generationCountLabel.setGenerationCount(0);
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

    private void addControlWithChangingVisibility(Component control, boolean visibleWhenGameHasStarted) {
        buttonVisibilityAfterGameStartMap.put(control, visibleWhenGameHasStarted);
        addControl(control);
    }

    private void addAllwaysVisibleControl(Component control) {
        addControl(control);
    }

    private void addControl(Component control) {
        controls.add(control);
        controlsPanel.add(control);
    }
    
    @Override
    public void addActionListenerToNewGameControl(ActionListener listener) {
        newGameButton.addActionListener(listener);
    }
    
    @Override
    public void newGameHasBeenCreated(GameBoardDTO newBoardDTO) {
        adjustControlsVisibility();
    }

    @Override
    public void gameHasStarted() {
        adjustControlsVisibility();
    }
    
    @Override
    public void gameBoardHasChanged(GameBoardDTO newBoardDTO) {
        adjustControlsVisibility();
        if (gameController.isGameStarted()) {
            generationCountLabel.incrementGenerationCount();
        }
    }

    private void adjustControlsVisibility() {
        for (Component control : controls) {
            control.setVisible(isControlVisible(control));
        }
    }

    private boolean isControlVisible(Component control) {
        Boolean controlIsVisibleAfterGameStart = buttonVisibilityAfterGameStartMap.get(control);
        if (controlIsVisibleAfterGameStart == null) {
            return true;
        }
        return gameController.isGameStarted() ? controlIsVisibleAfterGameStart : !controlIsVisibleAfterGameStart;
    }

    @Override
    public Component getComponent() {
        return controlsPanel;
    }

}
