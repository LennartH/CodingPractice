package org.gameoflife.controller.impl;

import java.util.HashSet;
import java.util.Set;

import org.gameoflife.backend.GameBoard;
import org.gameoflife.backend.InitialGenerationCreator;
import org.gameoflife.backend.RuleApplier;
import org.gameoflife.backend.factory.GameBoardFactory;
import org.gameoflife.backend.factory.GameBoardModifierFactory;
import org.gameoflife.backend.factory.RuleFactory;
import org.gameoflife.backend.shared.CellState;
import org.gameoflife.backend.shared.GameBoardDTO;
import org.gameoflife.controller.GameController;
import org.gameoflife.controller.listener.GameStartedListener;

public class SimpleGameController implements GameController {
    
    private static final int STANDARD_BOARD_HEIGHT = 25;
    private static final int STANDARD_BOARD_WIDTH = 25;
    
    private Set<GameStartedListener> gameStartedListeners;
    private GameBoard board;

    public SimpleGameController() {
        this(STANDARD_BOARD_WIDTH, STANDARD_BOARD_HEIGHT);
    }

    public SimpleGameController(int boardWidth, int boardHeight) {
        gameStartedListeners = new HashSet<>();
        RuleApplier ruleApplier = RuleFactory.createStandardRuleSet();
        InitialGenerationCreator initialGenerationCreator = GameBoardModifierFactory.createFixStateInitialGenerationCreator(boardWidth, boardHeight, CellState.DEAD);
        board = GameBoardFactory.createDeadEndGameBoard(ruleApplier, initialGenerationCreator);
    }

    @Override
    public void startGame() {
        for (GameStartedListener listener : gameStartedListeners) {
            listener.gameHasStarted();
        }
    }

    @Override
    public void addGameStartedListener(GameStartedListener listener) {
        gameStartedListeners.add(listener);
    }

    @Override
    public GameBoardDTO getBoardDTO() {
        // TODO Auto-generated method stub
        return null;
    }

}
