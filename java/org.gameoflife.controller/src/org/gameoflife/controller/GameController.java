package org.gameoflife.controller;

import org.gameoflife.backend.shared.dto.GameBoardDTO;


public interface GameController {

    public void createNewGame(int boardWidth, int boardHeight);
    public void startGame();
    public void startGame(GameBoardDTO initialGeneration);
	public void calculateNextGeneration();

    public void registerListener(Object listener);
    
    public boolean isGameStarted();

}
