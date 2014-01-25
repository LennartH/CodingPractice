package org.gameoflife.controller;

import org.gameoflife.controller.listener.GameStartedListener;
import org.gameoflife.controller.listener.GameBoardChangedListener;

public interface GameController {

    public void createNewGame(int boardWidth, int boardHeight);
    public void startGame();
	public void calculateNextGeneration();

    public void addGameStartedListener(GameStartedListener listener);
    public void addGameBoardChangedListener(GameBoardChangedListener listener);

}
