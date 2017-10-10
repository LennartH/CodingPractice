package org.gameoflife.frontend.swing;

import java.awt.Component;

public interface ProvidesComponent {
    
    public Component getComponent();
    
    public void setVisible(boolean visible);
    public void isVisible();

}
