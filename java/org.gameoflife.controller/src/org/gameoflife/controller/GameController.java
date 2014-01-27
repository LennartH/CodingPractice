package org.gameoflife.controller;


public interface GameController {

    public void createNewGame(int boardWidth, int boardHeight);
    public void startGame();
	public void calculateNextGeneration();

    public void registerListener(Object listener);

}
