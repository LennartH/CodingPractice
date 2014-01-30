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
        listenerRegistry.notifyGameHasStarted();
    }
    
    @Override
    public void applyGameBoardDTO(GameBoardDTO gameBoardDTO) {
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
    public void registerListener(Object listener) {
        listenerRegistry.register(listener);
    }
    
    @Override
    public boolean isGameStarted() {
        return gameStarted;
    }

    private GameBoardDTO getBoardDTO() {
        return board.asDTO();
    }

}
