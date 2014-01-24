package gameoflife.frontend.impl;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import gameoflife.controller.GameController;
import gameoflife.frontend.BoardRenderer;

public class PanelBoardRenderer implements BoardRenderer {
    
    private final JPanel component;
    
    public PanelBoardRenderer(GameController gameController) {
        component = new JPanel(new FlowLayout());
    }

    @Override
    public Component getComponent() {
        return component;
    }

}
