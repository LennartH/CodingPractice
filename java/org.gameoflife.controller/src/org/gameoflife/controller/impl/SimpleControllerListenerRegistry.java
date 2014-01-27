package org.gameoflife.controller.impl;

import java.util.Collection;
import java.util.HashSet;

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
    public void informGameHasStarted() {
        for (GameStartedListener listener : getListenersOfType(GameStartedListener.class)) {
            listener.gameHasStarted();
        }
    }

}
