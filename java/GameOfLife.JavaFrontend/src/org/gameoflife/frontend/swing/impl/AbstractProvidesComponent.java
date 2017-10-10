package org.gameoflife.frontend.swing.impl;

import org.gameoflife.frontend.swing.ProvidesComponent;

public abstract class AbstractProvidesComponent implements ProvidesComponent {

    @Override
    public void setVisible(boolean visible) {
        getComponent().setVisible(visible);
    }

    @Override
    public void isVisible() {
        getComponent().isVisible();
    }

}