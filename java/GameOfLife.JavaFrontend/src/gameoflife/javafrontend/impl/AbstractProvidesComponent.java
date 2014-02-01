package gameoflife.javafrontend.impl;

import gameoflife.javafrontend.ProvidesComponent;

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