package org.gameoflife.controller.impl;

import java.util.Collection;
import java.util.HashSet;

import org.gameoflife.backend.shared.dto.GameBoardDTO;
import org.gameoflife.controller.ControllerListenerRegistry;
import org.gameoflife.controller.listener.GameBoardChangedListener;
import org.gameoflife.controller.listener.GameCreatedListener;
import org.gameoflife.controller.listener.GameStartedListener;

public class SimpleControllerListenerRegistry extends DynamicListenerRegistry implements ControllerListenerRegistry {

    public SimpleControllerListenerRegistry() {
        super(getListenerTypes());
    }

    private static Collection<Class<?>> getListenerTypes() {
        Collection<Class<?>> listenerTypes = new HashSet<>();
        listenerTypes.add(GameBoardChangedListener.class);
        listenerTypes.add(GameStartedListener.class);
        listenerTypes.add(GameCreatedListener.class);
        return listenerTypes;
    }
    
    @Override
    public void notifyGameHasBeenCreated(GameBoardDTO newBoardDTO) {
        for (GameCreatedListener listener : getListenersOfType(GameCreatedListener.class)) {
            listener.newGameHasBeenCreated(newBoardDTO);
        }
    }
    
    @Override
    public void notifyGameHasStarted() {
        for (GameStartedListener listener : getListenersOfType(GameStartedListener.class)) {
            listener.gameHasStarted();
        }
    }
    
    @Override
    public void notifyGameBoardHasChanged(GameBoardDTO newBoardDTO) {
        for (GameBoardChangedListener listener : getListenersOfType(GameBoardChangedListener.class)) {
            listener.gameBoardHasChanged(newBoardDTO);
        }
    }

}
