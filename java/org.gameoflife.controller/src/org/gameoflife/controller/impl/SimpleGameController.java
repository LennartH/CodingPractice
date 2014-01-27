package org.gameoflife.controller.impl;

import org.gameoflife.backend.GameBoard;
import org.gameoflife.backend.factory.GameBoardFactory;
import org.gameoflife.backend.shared.dto.GameBoardDTO;
import org.gameoflife.controller.ControllerListenerRegistry;
import org.gameoflife.controller.GameController;

public class SimpleGameController implements GameController {

    private ControllerListenerRegistry listenerRegistry;
    private GameBoard board;

    public SimpleGameController(ControllerListenerRegistry listenerRegistry) {
        this.listenerRegistry = listenerRegistry;
    }
    
    @Override
    public void createNewGame(int boardWidth, int boardHeight) {
        board = GameBoardFactory.createStandardDeadEndGameBoard(boardWidth, boardHeight);
        listenerRegistry.informGameHasBeenCreated(getBoardDTO());
    }
    
    @Override
    public void calculateNextGeneration() {
        board.evolve();
        listenerRegistry.informGameBoardHasChanged(getBoardDTO());
    }

    @Override
    public void startGame() {
        listenerRegistry.informGameHasStarted();
    }
    
    @Override
    public void registerListener(Object listener) {
        listenerRegistry.register(listener);
    }

    private GameBoardDTO getBoardDTO() {
        return board.asDTO();
    }

}
