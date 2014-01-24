package org.gameoflife.controller.impl;

import java.util.HashSet;
import java.util.Set;

import org.gameoflife.backend.CellState;
import org.gameoflife.backend.GameBoard;
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
        board = new DeadEndGameBoard(new StandardRuleApplier(), 
                                     new FixStateInitialGenerationCreator(boardWidth, boardHeight, CellState.DEAD));
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
    public GameBoard getBoard() {
        // TODO Auto-generated method stub
        return null;
    }

}
