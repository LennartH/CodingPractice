package org.gameoflife.controller.factory;

import java.util.Collection;

import org.gameoflife.controller.ListenerRegistry;
import org.gameoflife.controller.impl.DynamicListenerRegistry;

public class ListenerRegistryFactory {

    public static ListenerRegistry createDynamicListenerRegistry(Collection<Class<?>> listenerTypes) {
        return new DynamicListenerRegistry(listenerTypes);
    }

}
