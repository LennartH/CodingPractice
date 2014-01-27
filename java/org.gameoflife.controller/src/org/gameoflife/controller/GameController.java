package org.gameoflife.controller;

import org.gameoflife.controller.listener.GameBoardChangedListener;

public interface GameController {

    public void createNewGame(int boardWidth, int boardHeight);
    public void startGame();
	public void calculateNextGeneration();

    public void addGameBoardChangedListener(GameBoardChangedListener listener);
    public void registerListener(Object listener);

}
