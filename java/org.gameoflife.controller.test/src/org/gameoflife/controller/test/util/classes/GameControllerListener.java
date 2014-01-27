package org.gameoflife.controller.test.util.classes;

import org.gameoflife.backend.shared.dto.GameBoardDTO;
import org.gameoflife.controller.listener.GameBoardChangedListener;
import org.gameoflife.controller.listener.GameCreatedListener;
import org.gameoflife.controller.listener.GameStartedListener;

public class GameControllerListener implements GameBoardChangedListener, GameStartedListener, GameCreatedListener {
    
    private String name;
    private int gameCreatedCalls;
    private int gameStartedCalls;
    private int gameBoardChangedCalls;

    public GameControllerListener(String name) {
        this.name = name;
    }

    @Override
    public void newGameHasBeenCreated(GameBoardDTO newBoardDTO) {
        gameCreatedCalls++;
    }

    @Override
    public void gameHasStarted() {
        gameStartedCalls++;
    }

    @Override
    public void gameBoardHasChanged(GameBoardDTO newBoardDTO) {
        gameBoardChangedCalls++;
    }
    
    public String getName() {
        return name;
    }
    
    public int getGameCreatedCalls() {
        return gameCreatedCalls;
    }
    
    public int getGameStartedCalls() {
        return gameStartedCalls;
    }
    
    public int getGameBoardChangedCalls() {
        return gameBoardChangedCalls;
    }
    
    @Override
    public String toString() {
        return GameControllerListener.class.getSimpleName() + "[" + getName() + "]";
    }

}
