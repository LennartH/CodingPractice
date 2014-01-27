package org.gameoflife.controller.test.util.classes;

public class IsNoListener {

    private String name;

    public IsNoListener(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return IsNoListener.class.getSimpleName() + "[" + getName() + "]";
    }

}
