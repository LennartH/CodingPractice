package org.gameoflife.controller.test.util.classes;

public class ConcreteType extends AbstractType implements NameChangedListener {

    private String name;

    public ConcreteType(String name) {
        this.name = name;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void nameChanged(String newName) {
        name = newName;
    }
    
    @Override
    public String toString() {
        return ConcreteType.class.getSimpleName() + "[" + getName() + "]";
    }

}
