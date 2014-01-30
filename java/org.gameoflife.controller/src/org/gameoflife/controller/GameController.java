package org.gameoflife.controller;

import org.gameoflife.backend.shared.dto.GameBoardDTO;



public interface GameController {

    public void createNewGame(int boardWidth, int boardHeight);
    public void startGame();
    public void applyGameBoardDTO(GameBoardDTO gameBoardDTO);
	public void calculateNextGeneration();

    public void registerListener(Object listener);
    
    public boolean isGameStarted();

}
