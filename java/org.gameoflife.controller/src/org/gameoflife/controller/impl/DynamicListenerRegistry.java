package org.gameoflife.controller.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.gameoflife.controller.ListenerRegistry;

public class DynamicListenerRegistry implements ListenerRegistry {

    private final Map<Class<?>, Collection<Object>> listenersMappedByListenerTypes;

    public DynamicListenerRegistry(Collection<Class<?>> listenerTypes) {
        listenersMappedByListenerTypes = new HashMap<>();
        for (Class<?> listenerType : listenerTypes) {
            listenersMappedByListenerTypes.put(listenerType, new HashSet<>());
        }
    }

    //It should be impossible, that a ClassCastException is thrown, if the listeners
    //have been registered correctly
    @SuppressWarnings("unchecked")
    @Override
    public <T> Iterable<T> getListenersOfType(Class<T> listenerType) {
        if (!listenersMappedByListenerTypes.containsKey(listenerType)) {
            return new HashSet<>();
        }
        return (Iterable<T>) listenersMappedByListenerTypes.get(listenerType);
    }

    @Override
    public void register(Object listenerInstance) {
        Iterable<Class<?>> listenerTypes = getListenerTypesOf(listenerInstance.getClass());
        for (Class<?> listenerType : listenerTypes) {
            register(listenerInstance, listenerType);
        }
    }

    private void register(Object listenerInstance, Class<?> listenerType) {
        if (!listenersMappedByListenerTypes.containsKey(listenerType)) {
            throw new IllegalArgumentException(listenerType.getName() + " isn't allowed as listener for this registry.");
        }
        
        listenersMappedByListenerTypes.get(listenerType).add(listenerInstance);
    }

    private Collection<Class<?>> getListenerTypesOf(Class<?> type) {
        Collection<Class<?>> classHierarchy = getSupertypesOf(type);
        classHierarchy.add(type);
        Collection<Class<?>> listenerTypes = new HashSet<>();
        
        for (Class<?> hierarchyMember : classHierarchy) {
            if (equalsAnyListenerType(hierarchyMember)) {
                listenerTypes.add(hierarchyMember);
            }
        }
        
        return listenerTypes;
    }

    private boolean equalsAnyListenerType(Class<?> type) {
        return listenersMappedByListenerTypes.containsKey(type);
    }

    private Collection<Class<?>> getSupertypesOf(Class<?> type) {
        Collection<Class<?>> supertypes = new HashSet<>();
        
        supertypes.addAll(getInterfacesOf(type));
        if (isSuperclassValid(type)) {
            supertypes.add(type.getSuperclass());
        }
        
        boolean supertypeAdded = false;
        do {
            Collection<Class<?>> supertypesToAdd = new HashSet<>();
            for (Class<?> supertype : supertypes) {
                supertypesToAdd.addAll(getSupertypesOf(supertype));
            }
            supertypeAdded = supertypes.addAll(supertypesToAdd);
        } while (supertypeAdded);
        
        return supertypes;
    }

    private Collection<Class<?>> getInterfacesOf(Class<?> type) {
        return Arrays.asList(type.getInterfaces());
    }

    private boolean isSuperclassValid(Class<?> type) {
        return type.getSuperclass() != null && !type.getSuperclass().equals(Object.class);
    }

}
