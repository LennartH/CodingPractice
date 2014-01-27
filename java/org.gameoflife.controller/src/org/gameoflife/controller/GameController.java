package org.gameoflife.controller;

import org.gameoflife.controller.listener.GameBoardChangedListener;
import org.gameoflife.controller.listener.GameStartedListener;

public interface GameController {

    public void createNewGame(int boardWidth, int boardHeight);
    public void startGame();
	public void calculateNextGeneration();

    public void addGameStartedListener(GameStartedListener listener);
    public void addGameBoardChangedListener(GameBoardChangedListener listener);
    public void registerListener(Object listener);

}
