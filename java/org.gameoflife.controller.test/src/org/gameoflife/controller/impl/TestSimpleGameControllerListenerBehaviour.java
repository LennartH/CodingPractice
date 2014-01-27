package org.gameoflife.controller.impl;

import static org.junit.Assert.*;

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
    public void test() {
        fail("Not yet implemented");
    }

}
