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
import org.gameoflife.backend.shared.dto.GameBoardDTO;
import org.gameoflife.controller.GameController;
import org.gameoflife.controller.listener.GameStartedListener;
import org.gameoflife.controller.listener.GameBoardChangedListener;

public class SimpleGameController implements GameController {

    private final Set<GameStartedListener> gameStartedListeners;
    private final Set<GameBoardChangedListener> gameCreatedListeners;
    
    private GameBoard board;

    public SimpleGameController() {
        gameStartedListeners = new HashSet<>();
        gameCreatedListeners = new HashSet<>();
    }
    
    @Override
    public void createNewGame(int boardWidth, int boardHeight) {
        RuleApplier ruleApplier = RuleFactory.createStandardRuleSet();
        InitialGenerationCreator initialGenerationCreator = GameBoardModifierFactory.createFixStateInitialGenerationCreator(boardWidth, boardHeight, CellState.DEAD);
        board = GameBoardFactory.createDeadEndGameBoard(ruleApplier, initialGenerationCreator);
        
        informGameBoardHasChanged();
    }
    
    @Override
    public void calculateNextGeneration() {
        board.evolve();
        informGameBoardHasChanged();
    }

    private void informGameBoardHasChanged() {
        GameBoardDTO boardDTO = getBoardDTO();
        for (GameBoardChangedListener listener : gameCreatedListeners) {
            listener.gameBoardHasChanged(boardDTO);
        }
    }

    @Override
    public void startGame() {
        for (GameStartedListener listener : gameStartedListeners) {
            listener.gameHasStarted();
        }
    }
    
    @Override
    public void addGameBoardChangedListener(GameBoardChangedListener listener) {
        gameCreatedListeners.add(listener);
    }

    @Override
    public void addGameStartedListener(GameStartedListener listener) {
        gameStartedListeners.add(listener);
    }

    private GameBoardDTO getBoardDTO() {
        return board.asDTO();
    }

}
