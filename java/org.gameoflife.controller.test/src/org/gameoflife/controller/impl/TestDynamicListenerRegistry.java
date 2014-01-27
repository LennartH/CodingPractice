package org.gameoflife.controller.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.HashSet;

import org.gameoflife.controller.test.util.classes.AnotherListener;
import org.gameoflife.controller.test.util.classes.ConcreteType;
import org.gameoflife.controller.test.util.classes.IsNoListener;
import org.gameoflife.controller.test.util.classes.Listener;
import org.gameoflife.controller.test.util.classes.NameChangedListener;
import org.junit.Before;
import org.junit.Test;

public class TestDynamicListenerRegistry {
    
    private DynamicListenerRegistry registry;
    
    @Before
    public void initializeListenerRegistry() {
        Collection<Class<?>> listenerTypes = new HashSet<>();
        listenerTypes.add(Listener.class);
        listenerTypes.add(AnotherListener.class);
        listenerTypes.add(NameChangedListener.class);
        registry = new DynamicListenerRegistry(listenerTypes);
    }
    
    @Test
    public void testNonNullEmptyCollectionsAreReturnedIfRegistryIsEmpty() {
        assertRegistryIsEmpty();

        Iterable<IsNoListener> noListeners = registry.getListenersOfType(IsNoListener.class);
        assertThat(noListeners, notNullValue());
        assertThat("Listeners weren't empty.", noListeners.iterator().hasNext(), is(false));
    }

    private void assertRegistryIsEmpty() {
        Iterable<Listener> listeners = registry.getListenersOfType(Listener.class);
        assertThat(listeners, notNullValue());
        assertThat("Listeners weren't empty.", listeners.iterator().hasNext(), is(false));

        Iterable<AnotherListener> anotherListeners = registry.getListenersOfType(AnotherListener.class);
        assertThat(anotherListeners, notNullValue());
        assertThat("Listeners weren't empty.", anotherListeners.iterator().hasNext(), is(false));

        Iterable<NameChangedListener> nameChangedListeners = registry.getListenersOfType(NameChangedListener.class);
        assertThat(nameChangedListeners, notNullValue());
        assertThat("Listeners weren't empty.", nameChangedListeners.iterator().hasNext(), is(false));
    }

    @Test
    public void testDynamicRegistration() {
        ConcreteType instance = new ConcreteType("Ralf");
        registry.register(instance);
        
        Iterable<Listener> listeners = registry.getListenersOfType(Listener.class);
        Iterable<Listener> expectedListeners = createHashSet(instance);
        assertThat(listeners, is(expectedListeners));
        
        Iterable<AnotherListener> anotherListeners = registry.getListenersOfType(AnotherListener.class);
        Iterable<AnotherListener> expectedAnotherListeners = createHashSet(instance);
        assertThat(anotherListeners, is(expectedAnotherListeners));
        
        Iterable<NameChangedListener> nameChangedListeners = registry.getListenersOfType(NameChangedListener.class);
        Iterable<NameChangedListener> expectedNameChangedListeners = createHashSet(instance);
        assertThat(nameChangedListeners, is(expectedNameChangedListeners));
    }
    
    @Test
    public void testRegistrationOfAWrongInstance() {
        IsNoListener instance = new IsNoListener("Dieter");
        registry.register(instance);
        assertRegistryIsEmpty();
    }

    @SuppressWarnings("unchecked")
    private <T, I extends T> Iterable<T> createHashSet(I... instances) {
        HashSet<T> set = new HashSet<>();
        for (I instance : instances) {
            set.add(instance);
        }
        return set;
    }

}
