package gameoflife.controller.impl;

import java.util.HashSet;
import java.util.Set;

import gameoflife.controller.GameController;
import gameoflife.controller.listener.GameStartedListener;

public class SimpleGameController implements GameController {
    
    private Set<GameStartedListener> gameStartedListeners;

    public SimpleGameController() {
        gameStartedListeners = new HashSet<>();
    }

    @Override
    public void startGame() {
        for (GameStartedListener listener : gameStartedListeners) {
            listener.gameHasStarted();
        }
    }

    @Override
    public void addGameStartedListener(GameStartedListener listener) {
        gameStartedListeners.add(listener);
    }

}
