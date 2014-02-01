package gameoflife.javafrontend.impl;

import java.awt.Component;
import java.awt.Container;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.RepaintManager;

public class FreezableRepaintManager extends RepaintManager {
    
    private final Set<Component> frozen = new HashSet<Component>();

    public void freeze(Container c) {
        frozen.add(c);
        for (Component child : c.getComponents()) {
            if (child instanceof Container) {
                freeze((Container) child);
            } else {
                frozen.add(child);
            }
        }
    }

    public void thaw(final Container c) {
        frozen.remove(c);
        for (Component child : c.getComponents()) {
            if (child instanceof Container) {
                thaw((Container) child);
            } else {
                frozen.remove(child);
            }
        }
        c.repaint();
    }


    @Override
    public void addDirtyRegion(JComponent c, int x, int y, int w, int h) {
        if (!frozen.contains(c)) {
            super.addDirtyRegion(c, x, y, w, h);
        }
    }
}
