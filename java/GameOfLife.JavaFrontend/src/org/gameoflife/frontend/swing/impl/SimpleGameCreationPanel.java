package org.gameoflife.frontend.swing.impl;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.gameoflife.controller.GameController;
import org.gameoflife.frontend.swing.GameCreationPanel;
import org.gameoflife.frontend.swing.impl.component.PositiveIntegerWithMaximumTextField;

public class SimpleGameCreationPanel extends AbstractProvidesComponent implements GameCreationPanel {

    private static final int STANDARD_WIDTH = 25;
    private static final int STANDARD_HEIGHT = 25;
    
    private final JPanel controlsPanel;
    private final PositiveIntegerWithMaximumTextField widthField;
    private final PositiveIntegerWithMaximumTextField heightField;

    public SimpleGameCreationPanel(final GameController gameController) {
        controlsPanel = new JPanel(new FlowLayout());
        
        controlsPanel.add(createLabel("Width: "));
        widthField = new PositiveIntegerWithMaximumTextField(STANDARD_WIDTH, 3, 50);
        controlsPanel.add(widthField.getComponent());
        
        controlsPanel.add(createLabel("Height: "));
        heightField = new PositiveIntegerWithMaximumTextField(STANDARD_HEIGHT, 3, 50);
        controlsPanel.add(heightField.getComponent());
        
        JButton createNewGameButton = new JButton("Create");
        createNewGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.createNewGame(getWidth(), getHeight());
            }
        });
        controlsPanel.add(createNewGameButton);
    }

    private JLabel createLabel(String text) {
        return new JLabel(text);
    }

    private int getHeight() {
        return heightField.getValue();
    }

    private int getWidth() {
        return widthField.getValue();
    }

    @Override
    public Component getComponent() {
        return controlsPanel;
    }

}
