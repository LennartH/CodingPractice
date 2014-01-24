package org.gameoflife.controller;

import org.gameoflife.backend.shared.GameBoardDTO;
import org.gameoflife.controller.listener.GameStartedListener;


public interface GameController {

    public void startGame();

    public void addGameStartedListener(GameStartedListener listener);

    public GameBoardDTO getBoardDTO();

}
