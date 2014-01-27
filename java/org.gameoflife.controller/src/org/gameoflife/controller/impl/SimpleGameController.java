package org.gameoflife.controller.impl;

import java.util.HashSet;
import java.util.Set;

import org.gameoflife.backend.GameBoard;
import org.gameoflife.backend.factory.GameBoardFactory;
import org.gameoflife.backend.shared.dto.GameBoardDTO;
import org.gameoflife.controller.GameController;
import org.gameoflife.controller.listener.GameBoardChangedListener;
import org.gameoflife.controller.listener.GameStartedListener;

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
        board = GameBoardFactory.createStandardDeadEndGameBoard(boardWidth, boardHeight);
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
