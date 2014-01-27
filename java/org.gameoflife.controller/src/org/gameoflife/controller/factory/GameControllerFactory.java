package org.gameoflife.controller.factory;

import org.gameoflife.controller.GameController;
import org.gameoflife.controller.impl.SimpleControllerListenerRegistry;
import org.gameoflife.controller.impl.SimpleGameController;

public class GameControllerFactory {
    
    public static GameController createSimpleGameController() {
        return new SimpleGameController(new SimpleControllerListenerRegistry());
    }

}
