package org.gameoflife.controller;

public interface ListenerRegistry {

    public void register(Object listenerInstance);

    public <T> Iterable<T> getListenersOfType(Class<T> listenerType);

}
