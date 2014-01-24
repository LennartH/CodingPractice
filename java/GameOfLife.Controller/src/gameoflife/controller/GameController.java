package gameoflife.controller;

import gameoflife.controller.listener.GameStartedListener;

public interface GameController {

    public void startGame();

    public void addGameStartedListener(GameStartedListener listener);

}
