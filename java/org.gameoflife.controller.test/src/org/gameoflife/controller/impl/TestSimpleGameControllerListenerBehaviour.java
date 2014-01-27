package org.gameoflife.controller.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.gameoflife.controller.GameController;
import org.gameoflife.controller.factory.GameControllerFactory;
import org.gameoflife.controller.test.util.classes.GameControllerListener;
import org.junit.Before;
import org.junit.Test;

public class TestSimpleGameControllerListenerBehaviour {

    private GameController controller;
    private GameControllerListener listener;
    
    @Before
    public void setUpGameControllerAndRegisterListeners() {
        listener = new GameControllerListener("Herbert");
        controller = GameControllerFactory.createSimpleGameController();
        controller.registerListener(listener);
    }
    
    @Test
    public void testGameCreatedListenersAreCalled() {
        controller.createNewGame(25, 25);
        assertThatGameCreatedListenersAreCalled();
    }

    private void assertThatGameCreatedListenersAreCalled() {
        int gameCreatedCalls = listener.getGameCreatedCalls();
        assertThat("The game created listeners haven't been called.", gameCreatedCalls > 0, is(true));
    }
    
    @Test
    public void testGameStartedListenersAreCalled() {
        controller.startGame();
        assertThatGameStartedListenersAreCalled();
    }

    private void assertThatGameStartedListenersAreCalled() {
        int gameStartedCalls = listener.getGameStartedCalls();
        assertThat("The game started listeners haven't been called.", gameStartedCalls > 0, is(true));
    }
    
    @Test
    public void testGameBoardChangedListenersAreCalled() {
        controller.createNewGame(25, 25);
        controller.calculateNextGeneration();
        assertThatGameBoardChangedListenersAreCalled();
    }

    private void assertThatGameBoardChangedListenersAreCalled() {
        int gameBoardChangedCalls = listener.getGameBoardChangedCalls();
        assertThat("The game board changed listeners haven't been called.", gameBoardChangedCalls > 0, is(true));
    }

}
