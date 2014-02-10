package org.gameoflife.controller.impl;

import org.gameoflife.backend.GameBoard;
import org.gameoflife.backend.GameBoardModifier;
import org.gameoflife.backend.factory.GameBoardFactory;
import org.gameoflife.backend.factory.GameBoardModifierFactory;
import org.gameoflife.backend.shared.dto.GameBoardDTO;
import org.gameoflife.controller.ControllerListenerRegistry;
import org.gameoflife.controller.GameController;

public class SimpleGameController implements GameController {

    private ControllerListenerRegistry listenerRegistry;
    
    private GameBoard board;
    private boolean gameStarted;
    
    private boolean gameWasRecentlyInitiated;
    private GameBoardDTO initialBoardDTO;

    public SimpleGameController(ControllerListenerRegistry listenerRegistry) {
        this.listenerRegistry = listenerRegistry;
    }
    
    @Override
    public void createNewGame(int boardWidth, int boardHeight) {
        board = GameBoardFactory.createStandardDeadEndGameBoard(boardWidth, boardHeight);
        gameStarted = false;
        listenerRegistry.notifyGameHasBeenCreated(getBoardDTO());
    }

    @Override
    public void startGame() {
        gameStarted = true;
        gameWasRecentlyInitiated = true;
        listenerRegistry.notifyGameHasStarted();
    }
    
    @Override
    public void applyGameBoardDTO(GameBoardDTO gameBoardDTO) {
        if (gameWasRecentlyInitiated) {
            initialBoardDTO = gameBoardDTO;
            gameWasRecentlyInitiated = false;
        }
        
        GameBoardModifier applyGameBoardDTO = GameBoardModifierFactory.createApplyDTOToBoardModifier(gameBoardDTO, board);
        applyGameBoardDTO.applyModifications();
        listenerRegistry.notifyGameBoardHasChanged(getBoardDTO());
    }
    
    @Override
    public void calculateNextGeneration() {
        board.evolve();
        listenerRegistry.notifyGameBoardHasChanged(getBoardDTO());
    }
    
    @Override
    public void resetGameBoardToInitialState() {
        if (hasInitialBoardDTO()) {
            gameStarted = false;
            applyGameBoardDTO(initialBoardDTO);
        }
    }

    private boolean hasInitialBoardDTO() {
        return initialBoardDTO != null;
    }
    
    @Override
    public void registerListener(Object listener) {
        listenerRegistry.register(listener);
    }
    
    @Override
    public boolean isGameStarted() {
        return gameStarted;
    }
    
    @Override
    public int getBoardWidth() {
        return board.getWidth();
    }
    
    @Override
    public int getBoardHeight() {
        return board.getHeight();
    }

    private GameBoardDTO getBoardDTO() {
        return board.asDTO();
    }

}
