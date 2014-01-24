package org.gameoflife.controller;

import org.gameoflife.backend.GameBoard;
import org.gameoflife.controller.listener.GameStartedListener;


public interface GameController {

    public void startGame();

    public void addGameStartedListener(GameStartedListener listener);

    public GameBoard getBoard();

}
